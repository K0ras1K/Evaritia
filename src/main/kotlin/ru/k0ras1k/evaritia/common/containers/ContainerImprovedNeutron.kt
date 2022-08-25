package ru.k0ras1k.evaritia.common.containers

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Container
import net.minecraft.inventory.Slot
import net.minecraft.inventory.SlotFurnace
import net.minecraft.item.ItemStack
import ru.k0ras1k.evaritia.common.tiles.TileEntityImprovedNeutron
import ru.k0ras1k.evaritia.common.utils.SlotNeutronUpgrade

class ContainerImprovedNeutron(player: InventoryPlayer, machine: TileEntityImprovedNeutron): Container() {
    val tileEntityImprovedNeutron = machine

    init {
        addSlotToContainer(SlotFurnace(player.player, machine, 2, 80, 35))
        addSlotToContainer(SlotNeutronUpgrade(tileEntityImprovedNeutron, 0, 152, 22))
        addSlotToContainer(SlotNeutronUpgrade(tileEntityImprovedNeutron, 1, 152, 43))
        addSlotToContainer(SlotNeutronUpgrade(tileEntityImprovedNeutron, 3, 152, 64))

        var i: Int
        i = 0
        while (i < 3) {
            for (j in 0..8) {
                addSlotToContainer(Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18))
            }
            ++i
        }
        i = 0
        while (i < 9) {
            addSlotToContainer(Slot(player, i, 8 + i * 18, 142))
            ++i
        }
    }

    override fun canInteractWith(entityPlayer: EntityPlayer): Boolean {
        return tileEntityImprovedNeutron.isUseableByPlayer(entityPlayer)
    }

    override fun transferStackInSlot(entityPlayer: EntityPlayer, slotNumber: Int): ItemStack? {
        var itemstack: ItemStack? = null
        val slot = inventorySlots[slotNumber] as Slot?
        if (slot != null && slot.hasStack) {
            val itemstack1 = slot.stack
            itemstack = itemstack1.copy()
            if (slotNumber == 0) {
                if (!mergeItemStack(itemstack1, 1, 37, true)) {
                    return null
                }
                slot.onSlotChange(itemstack1, itemstack)
            } else if (slotNumber >= 1 && slotNumber < 28) {
                if (!mergeItemStack(itemstack1, 28, 37, false)) {
                    return null
                }
            } else if (slotNumber >= 28 && slotNumber < 37 && !mergeItemStack(itemstack1, 1, 28, false)) {
                return null
            }
            if (itemstack1.stackSize == 0) {
                slot.putStack(null as ItemStack?)
            } else {
                slot.onSlotChanged()
            }
            if (itemstack1.stackSize == itemstack.stackSize) {
                return null
            }
            slot.onPickupFromSlot(entityPlayer, itemstack1)
        }

        return itemstack
    }
}