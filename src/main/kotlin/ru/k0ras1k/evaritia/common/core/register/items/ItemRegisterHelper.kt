package ru.k0ras1k.evaritia.common.core.register.items

import cpw.mods.fml.common.registry.GameRegistry
import ru.k0ras1k.evaritia.common.items.ItemNeutronUpgrade

open class ItemRegisterHelper {

    fun registerNeutronUpgrade(name: String) {
        GameRegistry.registerItem(ItemNeutronUpgrade(name), name)
    }

}