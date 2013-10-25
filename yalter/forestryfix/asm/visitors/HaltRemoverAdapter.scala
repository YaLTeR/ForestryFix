package yalter.forestryfix.asm.visitors

import org.objectweb.asm._
import org.objectweb.asm.Opcodes._

class HaltRemoverAdapter(mv: MethodVisitor) extends PatternMethodAdapter(ASM4, mv) {

  private val SeenGetRuntime = 1
  private val SeenGetRuntimeIConst1 = 2

  override def visitMethodInsn(opc: Int, owner: String, name: String, desc: String) {
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
          System.out.println("[ForestryFix] Stripping halt.")
          return
        }
      }
      case _ =>
    }

    visitInsn()
    mv.visitMethodInsn(opc, owner, name, desc)
  }

  override def visitInsn(opc: Int) {
    if (state == SeenGetRuntime) {
      if (opc == ICONST_1) {
        state = SeenGetRuntimeIConst1
        return
      }
    }

    visitInsn()
    mv.visitInsn(opc)
  }

  def visitInsn() {
    state match {
      case SeenGetRuntime => mv.visitMethodInsn(INVOKESTATIC, "java/lang/Runtime", "getRuntime", "()Ljava/lang/Runtime;")
      case SeenGetRuntimeIConst1 => {
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Runtime", "getRuntime", "()Ljava/lang/Runtime;")
        mv.visitInsn(ICONST_1)
      }
      case _ =>
    }

    state = SeenNothing
  }

}