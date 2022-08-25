package ru.k0ras1k.evaritia.common.items

import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import fox.spiteful.avaritia.entity.EntityImmortalItem
import fox.spiteful.avaritia.items.LudicrousItems
import fox.spiteful.avaritia.items.tools.ToolHelper
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.material.MaterialLogic
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.EnumRarity
import net.minecraft.item.ItemPickaxe
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.IIcon
import net.minecraft.util.MathHelper
import net.minecraft.world.World
import net.minecraftforge.common.ForgeHooks
import net.minecraftforge.common.util.EnumHelper
import net.minecraftforge.common.util.ForgeDirection
import ru.k0ras1k.evaritia.client.tabs.EvaritiaCreativeTab

class ItemStandartWorldBreaker: ItemPickaxe(MaterialHelper().opPickaxe) {
    val opPickaxe = EnumHelper.addToolMaterial("INFINITY_PICKAXE", 32, 9999, 9999.0f, 6.0f, 200)
    private var hammer: IIcon? = null
    val MATERIALS = arrayOf(MaterialLogic.rock)

    init {
        unlocalizedName = "standart_infinity_pickaxe"
        creativeTab = EvaritiaCreativeTab
    }

    override fun setDamage(stack: ItemStack?, damage: Int) {
        super.setDamage(stack, 0)
    }

    override fun getRarity(stack: ItemStack?): EnumRarity? {
        return LudicrousItems.cosmic
    }

    override fun getDigSpeed(stack: ItemStack, block: Block?, meta: Int): Float {
        return if (stack.tagCompound != null && stack.tagCompound.getBoolean("hammer")) {
            5.0f
        } else {
            if (ForgeHooks.isToolEffective(stack, block, meta)) efficiencyOnProperMaterial else Math.max(
                func_150893_a(stack, block), 6.0f
            )
        }
    }

    @SideOnly(Side.CLIENT)
    override fun registerIcons(ir: IIconRegister) {
        itemIcon = ir.registerIcon("avaritia:infinity_pickaxe")
        hammer = ir.registerIcon("avaritia:infinity_hammer")
    }

    override fun getIcon(stack: ItemStack, pass: Int): IIcon? {
        val tags = stack.tagCompound
        return if (tags != null && tags.getBoolean("hammer")) hammer else itemIcon
    }

    @SideOnly(Side.CLIENT)
    override fun getIconIndex(stack: ItemStack): IIcon? {
        return this.getIcon(stack, 0)
    }

    override fun onItemRightClick(stack: ItemStack, world: World?, player: EntityPlayer): ItemStack? {
        if (player.isSneaking) {
            var tags = stack.tagCompound
            if (tags == null) {
                tags = NBTTagCompound()
                stack.tagCompound = tags
            }
            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack) < 10) {
                stack.addEnchantment(Enchantment.fortune, 10)
            }
            tags.setBoolean("hammer", !tags.getBoolean("hammer"))
            player.swingItem()
        }
        return stack
    }

    override fun hitEntity(stack: ItemStack, victim: EntityLivingBase, player: EntityLivingBase): Boolean {
        if (stack.tagCompound != null && stack.tagCompound.getBoolean("hammer") && (victim !is EntityPlayer || !LudicrousItems.isInfinite(
                victim
            ))
        ) {
            val i = 10
            victim.addVelocity(
                (-MathHelper.sin(player.rotationYaw * 3.1415927f / 180.0f) * i.toFloat() * 0.5f).toDouble(),
                2.0,
                (MathHelper.cos(player.rotationYaw * 3.1415927f / 180.0f) * i.toFloat() * 0.5f).toDouble()
            )
        }
        return true
    }

    override fun onBlockStartBreak(stack: ItemStack, x: Int, y: Int, z: Int, player: EntityPlayer): Boolean {
        if (stack.tagCompound != null && stack.tagCompound.getBoolean("hammer")) {
            val raycast = ToolHelper.raytraceFromEntity(player.worldObj, player, true, 10.0)
            if (raycast != null) {
                breakOtherBlock(player, stack, x, y, z, x, y, z, raycast.sideHit)
            }
        }
        return false
    }

    fun breakOtherBlock(
        player: EntityPlayer,
        stack: ItemStack?,
        x: Int,
        y: Int,
        z: Int,
        originX: Int,
        originY: Int,
        originZ: Int,
        side: Int
    ) {
        val world = player.worldObj
        val mat = world.getBlock(x, y, z).material
            if (!world.isAirBlock(x, y, z)) {
                val direction = ForgeDirection.getOrientation(side)
                val fortune = EnchantmentHelper.getFortuneModifier(player)
                val silk = EnchantmentHelper.getSilkTouchModifier(player)
                val doY = direction.offsetY == 0
                val range = 16
                ToolHelper.removeBlocksInIteration(
                    player,
                    stack,
                    world,
                    x,
                    y,
                    z,
                    -range,
                    if (doY) -1 else -range,
                    -range,
                    range,
                    if (doY) range * 2 - 2 else range,
                    range,
                    null as Block?,
                    MATERIALS,
                    silk,
                    fortune,
                    false
                )
            }

    }

    override fun hasCustomEntity(stack: ItemStack?): Boolean {
        return true
    }

    override fun createEntity(world: World?, location: Entity?, itemstack: ItemStack?): Entity? {
        return EntityImmortalItem(world, location, itemstack)
    }

    @SideOnly(Side.CLIENT)
    override fun hasEffect(par1ItemStack: ItemStack?, pass: Int): Boolean {
        return false
    }

}