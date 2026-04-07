package com.example.gui.anvil;

import com.example.gui.GuiHelper;
import com.example.util.Pair;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GuiAnvil extends GuiContainer
{
	public final ContainerAnvil theContainer;
	public GuiAnvil(Container container)
	{
		super(container);
		theContainer = (ContainerAnvil) container;
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

		State guiState = getState();
		if(guiState != State.IDLE)
		{
			int colour = guiState == State.INVALID ? 0x00EE0000 : GuiHelper.Colour.XPGREEN.hex;
			drawInfoText(getText(guiState), colour);
		}
	}
	public void drawInfoText(String str, int colour)
	{
		this.fontRenderer.drawString(str, 37, 59, 0x0000000);
		this.fontRenderer.drawString(str, 35, 59, 0x0000000);
		this.fontRenderer.drawString(str, 36, 58, 0x0000000);
		this.fontRenderer.drawString(str, 36, 60, 0x0000000);
		this.fontRenderer.drawString(str, 36, 59, colour);
	}

	@Override
	public void updateScreen()
	{
		current = getState();
		if(prev!=current)
		{
			drawGuiContainerForegroundLayer();
		}
		prev = current;
	}
	private State prev = State.IDLE;
	private State current = State.IDLE;


	public enum State{
		IDLE,
		INVALID,
		COMBINE,
		REPAIR;
	}

	public String getText(State state)
	{
		switch (state)
		{
			case IDLE: return "";
			case INVALID: return "Invalid Combination!";
			case REPAIR: return "Repair cost: "+getCost()+" levels";
			case COMBINE: return "Combine cost: "+getCost()+" levels";

		}
		return "null";
	}



	private State getState()
	{
		if(!(theContainer.input1.getHasStack()&&theContainer.input2.getHasStack())) return State.IDLE;
		ItemStack i1 = theContainer.input1.getStack();
		ItemStack i2 = theContainer.input2.getStack();
		if(isToolOrWeapon(i1)&&(i1.getItem() == i2.getItem())) return State.COMBINE;
		if(isToolOrWeapon(i1)&&isMaterial(i2)) return State.REPAIR;
		return State.INVALID;
	}
	private boolean isToolOrWeapon(ItemStack s)
	{
		//TODO: needs robuster
		return s.getMaxDamage()>2;
	}
	public static Set<Integer> materialItemIDs = new HashSet<>(Arrays.asList(
		Item.leather.shiftedIndex+256,
		Item.ingotIron.shiftedIndex+256,
		Block.cobblestone.blockID,
		Item.ingotGold.shiftedIndex+256,
		Item.diamond.shiftedIndex+256
		//autogen materials automatically get added here
	));
	private boolean isMaterial(ItemStack s)
	{
		if(s.getItem() instanceof ItemBlock) return materialItemIDs.contains(((ItemBlock) s.getItem()).getBlockID());
		return materialItemIDs.contains(s.getItem().shiftedIndex+256);
	}
	private String getCost()
	{
		return "999";
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
