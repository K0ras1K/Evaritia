package ru.k0ras1k.evaritia.common.blocks

import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import fox.spiteful.avaritia.tile.TileEntityNeutron
import net.minecraft.block.Block
import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.IIcon
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import ru.k0ras1k.evaritia.Evaritia
import ru.k0ras1k.evaritia.client.tabs.EvaritiaCreativeTab
import ru.k0ras1k.evaritia.common.tiles.TileEntityStandartNeutron
import java.util.*

class BlockStandartNeutron(generateTime: Int, blockName: String): BlockContainer(Material.iron) {
    val GENERATED_TIME: Int = generateTime
    var top: IIcon? = null
    var sides: IIcon? = null
    var front: IIcon? = null
    private val randy = Random()

    init {
        setBlockName(blockName)
        setCreativeTab(EvaritiaCreativeTab)
    }

    @SideOnly(Side.CLIENT)
    override fun registerBlockIcons(iconRegister: IIconRegister) {
        top = iconRegister.registerIcon("avaritia:collector_top")
        sides = iconRegister.registerIcon("avaritia:collector_side")
        front = iconRegister.registerIcon("avaritia:collector_front")
    }

    @SideOnly(Side.CLIENT)
    override fun getIcon(world: IBlockAccess?, x: Int, y: Int, z: Int, side: Int): IIcon? {
        return if (side == 1) {
            top
        } else {
            var facing = 2
            val machine = world!!.getTileEntity(x, y, z) as TileEntityStandartNeutron
            if (machine != null) {
                facing = machine.getFacing()
            }
            if (side == facing) front else sides
        }
    }

    @SideOnly(Side.CLIENT)
    override fun getIcon(side: Int, metadata: Int): IIcon? {
        if (side == 1) {
            return top
        }
        if (side == 3) {
            return front
        }
        return sides
    }

    override fun createNewTileEntity(world: World, meta: Int): TileEntity {
        return TileEntityStandartNeutron(GENERATED_TIME)
    }

    override fun onBlockActivated(world: World, x: Int, y: Int, z: Int, player: EntityPlayer, par6: Int, par7: Float, par8: Float, par9: Float): Boolean {
        return if (world.isRemote) {
            true
        } else {
            player.openGui(Evaritia.instance(), 1, world, x, y, z)
            true
        }
    }

    override fun breakBlock(world: World, x: Int, y: Int, z: Int, block: Block?, wut: Int) {
        val collector = world.getTileEntity(x, y, z) as TileEntityStandartNeutron
        if (collector != null) {
            val itemstack = collector.getStackInSlot(0)
            if (itemstack != null) {
                val f: Float = randy.nextFloat() * 0.8f + 0.1f
                val f1: Float = randy.nextFloat() * 0.8f + 0.1f
                val f2: Float = randy.nextFloat() * 0.8f + 0.1f
                while (itemstack.stackSize > 0) {
                    var j1: Int = randy.nextInt(21) + 10
                    if (j1 > itemstack.stackSize) {
                        j1 = itemstack.stackSize
                    }
                    itemstack.stackSize -= j1
                    val entityitem = EntityItem(
                        world,
                        (x.toFloat() + f).toDouble(),
                        (y.toFloat() + f1).toDouble(),
                        (z.toFloat() + f2).toDouble(),
                        ItemStack(itemstack.item, j1, itemstack.itemDamage)
                    )
                    if (itemstack.hasTagCompound()) {
                        entityitem.entityItem.tagCompound = itemstack.tagCompound.copy() as NBTTagCompound
                    }
                    val f3 = 0.05f
                    entityitem.motionX = (randy.nextGaussian().toFloat() * f3).toDouble()
                    entityitem.motionY = (randy.nextGaussian().toFloat() * f3 + 0.2f).toDouble()
                    entityitem.motionZ = (randy.nextGaussian().toFloat() * f3).toDouble()
                    world.spawnEntityInWorld(entityitem)
                }
            }
            world.func_147453_f(x, y, z, block)
        }
        super.breakBlock(world, x, y, z, block, wut)
    }
}