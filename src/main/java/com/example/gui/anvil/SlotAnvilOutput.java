package com.example.gui.anvil;

import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

public class SlotAnvilOutput extends Slot
{
	public final int index;
	public SlotAnvilOutput(IInventory inventory, IInventory inventoryRules, int index, int x, int y)
	{
		super(inventoryRules, index, x, y);
		this.index = index;
	}
	@Override
	//we never want people touching the output slot besides retrieving an item
	public boolean isItemValid(ItemStack par1ItemStack) {
		return false;
	}
	public int getSlotStackLimit() {
		return 1;
	}
}
