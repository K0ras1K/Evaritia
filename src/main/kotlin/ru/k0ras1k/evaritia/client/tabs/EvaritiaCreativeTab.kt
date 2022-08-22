package ru.k0ras1k.evaritia.client.tabs

import fox.spiteful.avaritia.blocks.BlockNeutronCollector
import fox.spiteful.avaritia.blocks.LudicrousBlocks
import net.minecraft.item.Item
import net.minecraft.item.ItemBow
import net.minecraft.creativetab.CreativeTabs as CreativeTabs

object EvaritiaCreativeTab: CreativeTabs("EvaritiaCreativeTab") {
    override fun getTabIconItem(): Item {
        return Item.getItemFromBlock(LudicrousBlocks.crystal_matrix)
    }
}