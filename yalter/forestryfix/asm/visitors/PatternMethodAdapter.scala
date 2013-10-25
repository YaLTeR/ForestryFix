package yalter.forestryfix.asm.visitors

import org.objectweb.asm._

abstract class PatternMethodAdapter(api: Int, mv: MethodVisitor) extends MethodVisitor(api, mv) {

  protected val SeenNothing = 0
  protected var state = SeenNothing
  
  override def visitInsn(opc: Int) {
    visitInsn()
	mv.visitInsn(opc)
  }
  
  override def visitIntInsn(opc: Int, operand: Int) {
    visitInsn()
	mv.visitIntInsn(opc, operand)
  }
  
  override def visitVarInsn(opc: Int, variable: Int) {
    visitInsn()
	mv.visitVarInsn(opc, variable)
  }
  
  override def visitTypeInsn(opc: Int, desc: String) {
    visitInsn()
	mv.visitTypeInsn(opc, desc)
  }
  
  override def visitFieldInsn(opc: Int, owner: String, name: String, desc: String) {
    visitInsn()
	mv.visitFieldInsn(opc, owner, name, desc)
  }
  
  override def visitMethodInsn(opc: Int, owner: String, name: String, desc: String) {
    visitInsn()
	mv.visitMethodInsn(opc, owner, name, desc)
  }
  
  override def visitInvokeDynamicInsn(name: String, desc: String, bsm: Handle, bsmArgs: Object*) {
    visitInsn()
	mv.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs)
  }
  
  override def visitJumpInsn(opc: Int, label: Label) {
    visitInsn()
	mv.visitJumpInsn(opc, label)
  }
  
  override def visitLdcInsn(cst: Object) {
    visitInsn()
	mv.visitLdcInsn(cst)
  }
  
  override def visitIincInsn(variable: Int, increment: Int) {
    visitInsn()
	mv.visitIincInsn(variable, increment)
  }
  
  override def visitTableSwitchInsn(min: Int, max: Int, dflt: Label, labels: Label*) {
    visitInsn()
	mv.visitTableSwitchInsn(min, max, dflt, labels:_*)
  }
  
  override def visitLookupSwitchInsn(dflt: Label, keys: Array[Int], labels: Array[Label]) {
    visitInsn()
	mv.visitLookupSwitchInsn(dflt, keys, labels)
  }
  
  override def visitMultiANewArrayInsn(desc: String, dims: Int) {
    visitInsn()
	mv.visitMultiANewArrayInsn(desc, dims)
  }
  
  override def visitFrame(frameType: Int, nLocal: Int, local: Array[Object], nStack: Int, stack: Array[Object]) {
    visitInsn()
	mv.visitFrame(frameType, nLocal, local, nStack, stack)
  }
  
  override def visitLabel(label: Label) {
    visitInsn()
	mv.visitLabel(label)
  }
  
  override def visitMaxs(maxStack: Int, maxLocals: Int) {
    visitInsn()
	mv.visitMaxs(maxStack, maxLocals)
  }
  
  protected def visitInsn()
  
}