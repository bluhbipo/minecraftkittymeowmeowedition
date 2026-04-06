package com.example.gui.anvil;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;

public class InventoryAnvil implements IInventory
{
	private ItemStack[] slots = new ItemStack[3];

	@Override
	public int getSizeInventory()
	{
		return slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return slot >= 0 && slot < this.slots.length ? this.slots[slot] : null;
	}

	@Override
	public ItemStack decrStackSize(int slot, int par2) {
		if (slot >= 0 && slot < this.slots.length) {
			ItemStack itemInSlot = this.slots[slot];
			this.slots[slot] = null;
			return itemInSlot;
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (slot >= 0 && slot < this.slots.length) {
			ItemStack itemInSlot = this.slots[slot];
			this.slots[slot] = null;
			return itemInSlot;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack item) {
		if (slot >= 0 && slot < this.slots.length) {
			this.slots[slot] = item;
		}
	}

	@Override
	public String getInvName()
	{
		return "container.anvil";
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public void onInventoryChanged()
	{

	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return true;
	}

	@Override
	public void openChest()
	{

	}

	@Override
	public void closeChest()
	{

	}
}
