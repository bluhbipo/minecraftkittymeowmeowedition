package com.example.gui.anvil;

import com.example.gui.BapsGui;
import com.example.gui.GuiHelper;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;

public class GuiAnvil extends BapsGui
{
	public GuiAnvil(Container par1Container)
	{
		super(par1Container);
	}

	@Override
	public Class<? extends IInventory> getInventoryClass()
	{
		return InventoryAnvil.class;
	}

	@Override
	public Class<? extends Container> getContainerClass()
	{
		return ContainerAnvil.class;
	}

	@Override
	public void initGui() {
		super.initGui();
		this.mc.thePlayer.craftingInventory = this.inventorySlots;
		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
	}

	protected void drawGuiContainerForegroundLayer() {
		this.fontRenderer.drawString("Anvil", 8, 6, GuiHelper.Colour.FONT.hex);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, GuiHelper.Colour.FONT.hex);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		int textureID = this.mc.renderEngine.getTexture("mods/themod/textures/gui/anvil.png");
		this.mc.renderEngine.bindTexture(textureID);

		// Save OpenGL state
		GL11.glPushMatrix();

		// Draw the background texture with **lighting OFF**
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);

		// Restore lighting for slots/items
		GL11.glEnable(GL11.GL_LIGHTING);

		// Pop OpenGL state
		GL11.glPopMatrix();
	}

}
