package ru.k0ras1k.evaritia

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.SidedProxy
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import ru.k0ras1k.evaritia.client.tabs.EvaritiaCreativeTab
import ru.k0ras1k.evaritia.common.core.proxy.CommonProxy


@Mod(modid = "evaritia", name = "Evaritia", version = "0.1")
object Evaritia {

    init {
        EvaritiaCreativeTab
    }

    @JvmStatic
    @SidedProxy(clientSide = "ru.k0ras1k.evaritia.common.core.proxy.ClientProxy", serverSide = "ru.k0ras1k.evaritia.common.core.proxy.CommonProxy")
    lateinit var proxy: CommonProxy

    @JvmStatic
    @Mod.InstanceFactory
    fun instance() = Evaritia

    @Mod.EventHandler
    fun pre(e: FMLPreInitializationEvent) {
        proxy.pre(e)
    }

    @Mod.EventHandler
    fun init(e: FMLInitializationEvent) {
        proxy.init(e)
    }

    @Mod.EventHandler
    fun post(e: FMLPostInitializationEvent) {
        proxy.post(e)
    }
}