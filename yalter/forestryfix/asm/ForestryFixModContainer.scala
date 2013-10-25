package yalter.forestryfix.asm

import java.util.Arrays;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.ModMetadata;

class ForestryFixModContainer extends DummyModContainer(new ModMetadata) {
  val meta = this.getMetadata()
  
  meta.modId = "ForestryFix"
  meta.name = "ForestryFix"
  meta.authorList = Arrays asList "YaLTeR"
  meta.description = "A mod that disables Forestry's mod protection on the client."
  meta.version = "0.1"
}