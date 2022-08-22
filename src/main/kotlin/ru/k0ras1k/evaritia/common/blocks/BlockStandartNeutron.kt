package ru.k0ras1k.evaritia.common.blocks

import fox.spiteful.avaritia.Avaritia
import fox.spiteful.avaritia.blocks.BlockNeutronCollector
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World
import ru.k0ras1k.evaritia.Evaritia
import ru.k0ras1k.evaritia.client.tabs.EvaritiaCreativeTab
import ru.k0ras1k.evaritia.common.tiles.TileEntityStandartNeutron

class BlockStandartNeutron(generateTime: Int, blockName: String): BlockNeutronCollector() {
    val GENERATED_TIME: Int = generateTime

    init {
        setBlockName(blockName)
        setCreativeTab(EvaritiaCreativeTab)
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
}