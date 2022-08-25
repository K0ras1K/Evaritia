package ru.k0ras1k.evaritia.common.tiles

import fox.spiteful.avaritia.items.LudicrousItems
import fox.spiteful.avaritia.tile.TileLudicrous
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

class TileEntityImprovedNeutron() : TileLudicrous(), IInventory {
    private var neutrons: ItemStack? = null
    var upgrade_1: ItemStack? = null
    var upgrade_2: ItemStack? = null
    var upgrade_3: ItemStack? = null
    private var facing = 2
    private var progress = 0

    override fun updateEntity() {
        if (++progress >= getProductionTime()) {
            if (neutrons == null) {
                neutrons = ItemStack(LudicrousItems.resource, getProductionStackSize(), getProductionId())
            } else if (neutrons!!.item === LudicrousItems.resource && neutrons!!.stackSize < 64) {
                addNeutronsStackSize()
            }
            progress = 0
            markDirty()
        }
    }

    private fun addNeutronsStackSize() {
        if (neutrons!!.itemDamage != getProductionId()) {
            neutrons = ItemStack(LudicrousItems.resource, getProductionStackSize(), getProductionId())
        } else {
            neutrons!!.stackSize = neutrons!!.stackSize + getProductionStackSize()
        }
    }

    private fun getProductionTime(): Int {
        for (i in 0..3) {
            if (i != 2) {
                if (getStackInSlot(i) != null) {
                    if (getStackInSlot(i)!!.item.unlocalizedName == "item.upgrade_neutron_time") {
                        return 20
                    }
                }
            }
        }
        return 80
    }

    private fun getProductionStackSize(): Int {
        for (i in 0..3) {
            if (i != 2) {
                if (getStackInSlot(i) != null) {
                    if (getStackInSlot(i)!!.item.unlocalizedName == "item.upgrade_neutron_stack") {
                        return 4
                    }
                }
            }
        }
        return 1
    }

    private fun getProductionId(): Int {
        for (i in 0..3) {
            if (i != 2) {
                if (getStackInSlot(i) != null) {
                    if (getStackInSlot(i)!!.item.unlocalizedName == "item.upgrade_neutron_meta") {
                        return 3
                    }
                }
            }
        }
        return 2
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
        upgrade_1 = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("upgrade_1"));
        upgrade_2 = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("upgrade_2"));
        upgrade_3 = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("upgrade_3"));
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

        if (upgrade_1 != null) {
            val upgrade_slot_1 = NBTTagCompound()
            upgrade_1!!.writeToNBT(upgrade_slot_1)
            tag.setTag("upgrade_1", upgrade_slot_1)
        } else {
            tag.removeTag("upgrade_1")
        }

        if (upgrade_2 != null) {
            val upgrade_slot_2 = NBTTagCompound()
            upgrade_2!!.writeToNBT(upgrade_slot_2)
            tag.setTag("upgrade_2", upgrade_slot_2)
        } else {
            tag.removeTag("upgrade_2")
        }

        if (upgrade_3 != null) {
            val upgrade_slot_3 = NBTTagCompound()
            upgrade_3!!.writeToNBT(upgrade_slot_3)
            tag.setTag("upgrade_3", upgrade_slot_3)
        } else {
            tag.removeTag("upgrade_3")
        }
    }

    override fun getSizeInventory(): Int {
        return 1
    }

    override fun getStackInSlot(slot: Int): ItemStack? {
        if (slot == 2) {
            return neutrons;
        } else if (slot == 0) {
            return upgrade_1;
        } else if (slot == 1) {
            return upgrade_2;
        } else if (slot == 3) {
            return upgrade_3;
        } else {
            return null;
        }

    }

    override fun decrStackSize(slot: Int, decrement: Int): ItemStack? {
        return if (slot === 2) {
            if (neutrons == null) {
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
        } else if (slot === 0) {
            if (upgrade_1 == null) {
                null
            } else {
                val take: ItemStack
                if (decrement < upgrade_1!!.stackSize) {
                    take = upgrade_1!!.splitStack(decrement)
                    if (upgrade_1!!.stackSize <= 0) {
                        upgrade_1 = null
                    }
                    take
                } else {
                    take = upgrade_1!!
                    upgrade_1 = null
                    take
                }
            }
        } else if (slot === 1) {
            if (upgrade_2 == null) {
                null
            } else {
                val take: ItemStack
                if (decrement < upgrade_2!!.stackSize) {
                    take = upgrade_2!!.splitStack(decrement)
                    if (upgrade_2!!.stackSize <= 0) {
                        upgrade_2 = null
                    }
                    take
                } else {
                    take = upgrade_2!!
                    upgrade_2 = null
                    take
                }
            }
        } else {
            if (upgrade_3 == null) {
                null
            } else {
                val take: ItemStack
                if (decrement < upgrade_3!!.stackSize) {
                    take = upgrade_3!!.splitStack(decrement)
                    if (upgrade_3!!.stackSize <= 0) {
                        upgrade_3 = null
                    }
                    take
                } else {
                    take = upgrade_3!!
                    upgrade_3 = null
                    take
                }
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
        if (slot == 2) {
            neutrons = stack;
        } else if (slot == 0) {
            upgrade_1 = stack;
        } else if (slot == 1) {
            upgrade_2 = stack;
        } else {
            upgrade_3 = stack;
        }
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

    fun gaugeProgressScaled(): Int {
        for (i in 0..3) {
            if (i != 2) {
                if (getStackInSlot(i) != null) {
                    if (getStackInSlot(i)!!.item.unlocalizedName == "item.upgrade_neutron_time") {
                        return (progress * 2.6).toInt()
                    }
                }
            }
        }
        return (progress * 0.65).toInt()
    }

}