package yalter.forestryfix.asm.visitors

import org.objectweb.asm._
import org.objectweb.asm.Opcodes._

class HaltRemoverClassAdapter(cv: ClassVisitor) extends ClassVisitor(ASM4, cv) {

  override def visitMethod(access: Int, name: String, desc: String, signature: String, exceptions: Array[String]): MethodVisitor = {
    val mv = cv.visitMethod(access, name, desc, signature, exceptions)
    if (mv != null) {
      return new HaltRemoverAdapter(mv)
    } else {
      null
    }
  }

}