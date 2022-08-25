package ru.k0ras1k.evaritia.common.items

import net.minecraft.item.Item
import ru.k0ras1k.evaritia.Evaritia
import ru.k0ras1k.evaritia.client.tabs.EvaritiaCreativeTab

class ItemNeutronUpgrade(name: String): Item() {

    init {
        setUnlocalizedName(name)
        setMaxStackSize(1)
        setCreativeTab(EvaritiaCreativeTab)
        setTextureName("evaritia:" + name)
    }

}