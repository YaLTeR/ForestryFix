package yalter.forestryfix.asm.visitors

import org.objectweb.asm._
import org.objectweb.asm.Opcodes._

class HaltRemoverAdapter(mv: MethodVisitor) extends PatternMethodAdapter(ASM4, mv) {

  private val SeenGetRuntime = 1
  private val SeenGetRuntimeIConst1 = 2

  override def visitMethodInsn(opc: Int, owner: String, name: String, desc: String): Unit = {
    state match {
      case SeenNothing => {
        if ((opc == INVOKESTATIC) && (owner equals "java/lang/Runtime") && (name equals "getRuntime") && (desc equals "()Ljava/lang/Runtime;")) {
          state = SeenGetRuntime
          return
        }
      }
      case SeenGetRuntimeIConst1 => {
        if ((opc == INVOKEVIRTUAL) && (owner equals "java/lang/Runtime") && (name equals "halt") && (desc equals "(I)V")) {
          state = SeenNothing
          System.out.println("[ForestryFix] Stripping Runtime.getRuntime().halt(1).")
          return
        }
      }
      case _ => // Do nothing
    }

    visitInsn()
    mv.visitMethodInsn(opc, owner, name, desc)
  }

  override def visitInsn(opc: Int): Unit = {
    if (state == SeenGetRuntime) {
      if (opc == ICONST_1) {
        state = SeenGetRuntimeIConst1
        return
      }
    }

    visitInsn()
    mv.visitInsn(opc)
  }

  def visitInsn(): Unit = {
    state match {
      case SeenGetRuntime => mv.visitMethodInsn(INVOKESTATIC, "java/lang/Runtime", "getRuntime", "()Ljava/lang/Runtime;")
      case SeenGetRuntimeIConst1 => {
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Runtime", "getRuntime", "()Ljava/lang/Runtime;")
        mv.visitInsn(ICONST_1)
      }
      case _ => // Do nothing
    }

    state = SeenNothing
  }

}