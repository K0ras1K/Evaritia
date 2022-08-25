package ru.k0ras1k.evaritia.common.utils

import net.minecraft.inventory.IInventory
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack
import ru.k0ras1k.evaritia.common.items.ItemNeutronUpgrade

class SlotNeutronUpgrade(inventory: IInventory, id: Int, x: Int, y: Int): Slot(inventory, id, x, y) {
    override fun isItemValid(itemStack: ItemStack): Boolean {
        if (itemStack.item is ItemNeutronUpgrade) {
            return true
        }
        return false
    }
}