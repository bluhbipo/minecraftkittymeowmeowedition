package com.example.gui.anvil;

import cpw.mods.fml.common.MinecraftDummyContainer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

public class SlotAnvilInput extends Slot
{
	public final int index;
	public SlotAnvilInput(IInventory inventory, IInventory inventoryRules, int index, int x, int y)
	{
		super(inventoryRules, index, x, y);
		this.index = index;
	}
	@Override
	public boolean isItemValid(ItemStack par1ItemStack) {
		return true;
	}
	public int getSlotStackLimit() {
		return index == 0 ? 1 : 64;
	}
}
