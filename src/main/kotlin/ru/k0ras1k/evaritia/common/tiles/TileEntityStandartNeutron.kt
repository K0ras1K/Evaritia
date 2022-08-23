package ru.k0ras1k.evaritia.common.tiles

import fox.spiteful.avaritia.items.LudicrousItems
import fox.spiteful.avaritia.tile.TileLudicrous
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

class TileEntityStandartNeutron(generatedTime: Int): TileLudicrous(), IInventory {
    private val GENERATED_TIME: Int = generatedTime
    private var neutrons: ItemStack? = null
    private var facing = 2
    private var progress = 0

    override fun updateEntity() {
        if (++progress >= GENERATED_TIME) {
            if (neutrons == null) {
                neutrons = ItemStack(LudicrousItems.resource, 1, 2)
            } else if (neutrons!!.item === LudicrousItems.resource && neutrons!!.itemDamage == 2 && neutrons!!.stackSize < 64) {
                ++neutrons!!.stackSize
            }
            progress = 0
            markDirty()
        }
    }

    fun getFacing(): Int {
        return facing
    }

    fun setFacing(dir: Int) {
        facing = dir
    }

    override fun readCustomNBT(tag: NBTTagCompound) {
        neutrons = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Neutrons"))
        progress = tag.getInteger("Progress")
        facing = tag.getShort("Facing").toInt()
    }

    override fun writeCustomNBT(tag: NBTTagCompound) {
        tag.setInteger("Progress", progress)
        tag.setShort("Facing", facing.toShort())
        if (neutrons != null) {
            val produce = NBTTagCompound()
            neutrons!!.writeToNBT(produce)
            tag.setTag("Neutrons", produce)
        } else {
            tag.removeTag("Neutrons")
        }
    }

    override fun getSizeInventory(): Int {
        return 1
    }

    override fun getStackInSlot(slot: Int): ItemStack? {
        return neutrons
    }

    override fun decrStackSize(slot: Int, decrement: Int): ItemStack? {
        return if (neutrons == null) {
            null
        } else {
            val take: ItemStack?
            if (decrement < neutrons!!.stackSize) {
                take = neutrons!!.splitStack(decrement)
                if (neutrons!!.stackSize <= 0) {
                    neutrons = null
                }
                take
            } else {
                take = neutrons
                neutrons = null
                take
            }
        }
    }

    override fun openInventory() {}

    override fun closeInventory() {}

    override fun isUseableByPlayer(player: EntityPlayer): Boolean {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) === this && player.getDistanceSq(
            xCoord.toDouble() + 0.5,
            yCoord.toDouble() + 0.5,
            zCoord.toDouble() + 0.5
        ) <= 64.0
    }

    override fun isItemValidForSlot(slot: Int, stack: ItemStack?): Boolean {
        return false
    }

    override fun getInventoryStackLimit(): Int {
        return 64
    }

    override fun setInventorySlotContents(slot: Int, stack: ItemStack?) {
        neutrons = stack
    }

    override fun getStackInSlotOnClosing(slot: Int): ItemStack? {
        return null
    }

    override fun getInventoryName(): String? {
        return "container.neutron"
    }

    override fun hasCustomInventoryName(): Boolean {
        return false
    }
}