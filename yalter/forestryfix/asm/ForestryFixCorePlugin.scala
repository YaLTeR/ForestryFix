package yalter.forestryfix.asm

import java.util.Map

import cpw.mods.fml.relauncher.IFMLLoadingPlugin
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions

@TransformerExclusions( Array("yalter.forestryfix.asm") )
class ForestryFixCorePlugin extends IFMLLoadingPlugin {

  override def getLibraryRequestClass(): Array[String] = null

  override def getASMTransformerClass(): Array[String] =
    Array("yalter.forestryfix.asm.ForestryFixTransformer")

  override def getModContainerClass(): String =
    "yalter.forestryfix.asm.ForestryFixModContainer"

  override def getSetupClass(): String = null
  override def injectData(data: Map[String, Object]): Unit = {}

}