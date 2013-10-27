package yalter.forestryfix.asm

import net.minecraft.launchwrapper.IClassTransformer

import org.objectweb.asm._

import yalter.forestryfix.asm.visitors._

class ForestryFixTransformer extends IClassTransformer {

  def getPluginCoreAdapter(cw: ClassWriter): ClassVisitor =
    new HaltRemoverClassAdapter(cw)

  def transformWithClassVisitor(name: String, bytes: Array[Byte],
      getVisitor: ClassWriter => ClassVisitor): Array[Byte] = {
    println("[ForestryFix] Looking through " + name + "...")

    val cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES)
    val cr = new ClassReader(bytes)
    cr.accept(getVisitor(cw), 0)

    cw.toByteArray()
  }

  override def transform(name: String, tname: String, bytes: Array[Byte]): Array[Byte] = {
    if (name contains "forestry.plugins.PluginCore") {
      transformWithClassVisitor(name, bytes, getPluginCoreAdapter)
    } else {
      bytes
    }
  }

}