package ru.k0ras1k.evaritia.common.core.register.tiles

import cpw.mods.fml.common.registry.GameRegistry
import ru.k0ras1k.evaritia.common.tiles.TileEntityImprovedNeutron
import ru.k0ras1k.evaritia.common.tiles.TileEntityStandartNeutron

class TilesRegister {
    fun register() {
        GameRegistry.registerTileEntity(TileEntityStandartNeutron::class.java, "tile_entity_standart_neutron")
        GameRegistry.registerTileEntity(TileEntityImprovedNeutron::class.java, "tile_entity_improved_neutron")
    }
}