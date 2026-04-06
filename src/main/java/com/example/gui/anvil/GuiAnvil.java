package com.example.gui.anvil;

import com.example.gui.BapsGui;
import com.example.gui.GuiHelper;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;

public class GuiAnvil extends BapsGui
{
	public final ContainerAnvil theContainer;
	public GuiAnvil(Container container)
	{
		super(container);
		theContainer = (ContainerAnvil) container;
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
	public int getGuiID()
	{
		return 100;
	}

	@Override
	public void initGui() {
		super.initGui();
		this.mc.thePlayer.craftingInventory = this.inventorySlots;
		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
	}

	public void drawGuiContainerForegroundLayer() {
		this.fontRenderer.drawString("Anvil", 8, 6, GuiHelper.Colour.FONT.hex);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, GuiHelper.Colour.FONT.hex);

		if(isValidCombo(theContainer))
		{
			this.fontRenderer.drawString("Faggot Detected!", 37, 59, 0x0000000);
			this.fontRenderer.drawString("Faggot Detected!", 35, 59, 0x0000000);
			this.fontRenderer.drawString("Faggot Detected!", 36, 58, 0x0000000);
			this.fontRenderer.drawString("Faggot Detected!", 36, 60, 0x0000000);
			this.fontRenderer.drawString("Faggot Detected!", 36, 59, GuiHelper.Colour.XPGREEN.hex);
		}

	}

	@Override
	public void updateScreen()
	{

		drawGuiContainerForegroundLayer();
	}

	private boolean isValidCombo(ContainerAnvil theContainer)
	{
		return theContainer.input1.getHasStack();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		int textureID = this.mc.renderEngine.getTexture("mods/themod/textures/gui/anvil.png");

		GL11.glColor4f(1F, 1F, 1F, 1F);

		this.mc.renderEngine.bindTexture(textureID);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
	}

}
