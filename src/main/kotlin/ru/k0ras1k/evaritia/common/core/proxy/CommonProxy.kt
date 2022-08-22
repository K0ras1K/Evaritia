package ru.k0ras1k.evaritia.common.core.proxy

import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import ru.k0ras1k.evaritia.common.core.register.blocks.BlockRegister
import ru.k0ras1k.evaritia.common.core.register.items.ItemRegister
import ru.k0ras1k.evaritia.common.core.register.tiles.TilesRegister

open class CommonProxy {
    fun pre(e: FMLPreInitializationEvent) {
        BlockRegister().register()
        ItemRegister().register()
        TilesRegister().register()
    }

    fun init(e: FMLInitializationEvent) {

    }

    fun post(e: FMLPostInitializationEvent) {

    }
}