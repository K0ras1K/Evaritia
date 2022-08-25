package ru.k0ras1k.evaritia.common.core.handlers

import cpw.mods.fml.common.network.IGuiHandler
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World
import ru.k0ras1k.evaritia.client.gui.GuiImprovedNeutron
import ru.k0ras1k.evaritia.client.gui.GuiStandartNeutron
import ru.k0ras1k.evaritia.common.containers.ContainerImprovedNeutron
import ru.k0ras1k.evaritia.common.containers.ContainerStandartNeutron
import ru.k0ras1k.evaritia.common.tiles.TileEntityImprovedNeutron
import ru.k0ras1k.evaritia.common.tiles.TileEntityStandartNeutron

class GuiHandler: IGuiHandler {
    override fun getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        var tile_entity = world.getTileEntity(x, y, z)
        when (ID) {
            1 ->
                if (tile_entity is TileEntityStandartNeutron) {
                    return ContainerStandartNeutron(player.inventory, world.getTileEntity(x, y, z) as TileEntityStandartNeutron)
                }
            2 ->
                if (tile_entity is TileEntityImprovedNeutron) {
                    return ContainerImprovedNeutron(player.inventory, world.getTileEntity(x, y, z) as TileEntityImprovedNeutron)
                }
        }
        return null
    }

    override fun getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        var tile_entity = world.getTileEntity(x, y, z)
        when (ID) {
            1 ->
                if (tile_entity is TileEntityStandartNeutron) {
                    return GuiStandartNeutron(player.inventory, world.getTileEntity(x, y, z) as TileEntityStandartNeutron)
                }
            2 ->
                if (tile_entity is TileEntityImprovedNeutron) {
                    return GuiImprovedNeutron(player.inventory, world.getTileEntity(x, y, z) as TileEntityImprovedNeutron)
                }
        }
        return null
    }
}