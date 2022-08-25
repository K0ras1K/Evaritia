package ru.k0ras1k.evaritia.client.gui

import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.util.ResourceLocation
import net.minecraft.util.StatCollector
import org.lwjgl.opengl.GL11
import ru.k0ras1k.evaritia.common.containers.ContainerImprovedNeutron
import ru.k0ras1k.evaritia.common.tiles.TileEntityImprovedNeutron

class GuiImprovedNeutron(player: InventoryPlayer, machine: TileEntityImprovedNeutron): GuiContainer(ContainerImprovedNeutron(player, machine)) {

    val guiTexture: ResourceLocation = ResourceLocation("evaritia", "textures/gui/gui_improved_neutron.png")
    val TILE_ENTITY: TileEntityImprovedNeutron = machine

    override fun drawGuiContainerBackgroundLayer(p_146976_1_: Float, p_146976_2_: Int, p_146976_3_: Int) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.textureManager.bindTexture(guiTexture)
        var k: Int = (width - xSize) / 2
        var l: Int = (height - ySize) / 2
        drawTexturedModalRect(k, l, 0, 0, xSize, ySize)
        if (this.TILE_ENTITY.gaugeProgressScaled() > 0) {
            val h: Int = this.TILE_ENTITY.gaugeProgressScaled()
            drawTexturedModalRect(k + 8, l + 16, 176, 0, 16, h)
        }
    }

    override fun drawGuiContainerForegroundLayer(p_146979_1_: Int, p_146979_2_: Int) {
        var s: String = StatCollector.translateToLocal("container.standart_neutron")
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 13487565)
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 13487565);
    }

}