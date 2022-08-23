package ru.k0ras1k.evaritia.common.containers

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Container
import ru.k0ras1k.evaritia.common.tiles.TileEntityImprovedNeutron

class ContainerImprovedNeutron(player: InventoryPlayer, machine: TileEntityImprovedNeutron): Container() {
    override fun canInteractWith(p_75145_1_: EntityPlayer?): Boolean {
        TODO("Not yet implemented")
    }
}