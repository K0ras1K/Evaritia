package ru.k0ras1k.evaritia.common.core.handlers

import cpw.mods.fml.common.network.IGuiHandler
import fox.spiteful.avaritia.gui.ContainerNeutron
import fox.spiteful.avaritia.gui.GUINeutron
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World
import ru.k0ras1k.evaritia.common.tiles.TileEntityStandartNeutron

class GuiHandler: IGuiHandler {
    override fun getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        var tile_entity = world.getTileEntity(x, y, z)
        when (ID) {
            1 ->
                if (tile_entity is TileEntityStandartNeutron) {
                    return ContainerNeutron(player.inventory, world.getTileEntity(x, y, z) as TileEntityStandartNeutron)
                }
        }
        return null
    }

    override fun getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        var tile_entity = world.getTileEntity(x, y, z)
        when (ID) {
            1 ->
                if (tile_entity is TileEntityStandartNeutron) {
                    return GUINeutron(player.inventory, world.getTileEntity(x, y, z) as TileEntityStandartNeutron)
                }
        }
        return null
    }
}