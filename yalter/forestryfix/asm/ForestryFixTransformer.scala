package yalter.forestryfix.asm

import net.minecraft.launchwrapper.IClassTransformer

import org.objectweb.asm._

import yalter.forestryfix.asm.visitors._

class ForestryFixTransformer extends IClassTransformer {

  def removeForestryHalt(name: String, bytes: Array[Byte]): Array[Byte] = {
    System.out.println("[ForestryFix] Modifying the " + name + " class...")

    val cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES)
    val ca = new HaltRemoverClassAdapter(cw)
    val cr = new ClassReader(bytes)
    cr.accept(ca, 0)

    cw.toByteArray()
  }

  override def transform(name: String, tname: String, bytes: Array[Byte]): Array[Byte] = {
    var returnBytes = bytes

    if (name contains "forestry.plugins.PluginCore") {
      returnBytes = removeForestryHalt(name, returnBytes)
    }

    returnBytes
  }

}