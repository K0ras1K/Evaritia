package ru.k0ras1k.evaritia.common.core.register.blocks

import cpw.mods.fml.common.registry.GameRegistry
import ru.k0ras1k.evaritia.common.blocks.BlockImprovedNeutron
import ru.k0ras1k.evaritia.common.blocks.BlockStandartNeutron

open class BlockRegisterHelper {
    fun registerStandartNeutron(generated_time: Int, block_name: String) {
        GameRegistry.registerBlock(BlockStandartNeutron(generated_time, block_name), block_name)
    }

    fun registerImprovedNeutron(block_name: String) {
        GameRegistry.registerBlock(BlockImprovedNeutron(block_name), block_name)
    }
}