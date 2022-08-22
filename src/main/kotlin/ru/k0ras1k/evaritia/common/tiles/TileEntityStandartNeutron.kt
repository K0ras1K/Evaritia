package ru.k0ras1k.evaritia.common.tiles

import fox.spiteful.avaritia.items.LudicrousItems
import fox.spiteful.avaritia.tile.TileEntityNeutron
import net.minecraft.item.ItemStack

class TileEntityStandartNeutron(generatedTime: Int): TileEntityNeutron() {
    val GENERATED_TIME: Int = generatedTime
    var neutrons: ItemStack? = null
    var progress = 0

    override fun updateEntity() {
        if (++progress >= GENERATED_TIME) {
            if (neutrons == null) {
                neutrons = ItemStack(LudicrousItems.resource, 1, 2)
            } else if (neutrons!!.item === LudicrousItems.resource && neutrons!!.itemDamage == 2 && neutrons!!.stackSize < 64) {
                ++neutrons!!.stackSize
            }
            progress = 0
            markDirty()
        }
    }
}