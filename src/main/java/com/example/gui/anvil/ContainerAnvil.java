package com.example.gui.anvil;

import net.minecraft.src.*;

public class ContainerAnvil extends Container
{

	public final Slot input1;
	public final Slot input2;
	public final Slot output;

	ContainerFurnace b;

	public ContainerAnvil(InventoryPlayer inventoryPlayer, IInventory inventoryRules) {

		int slotHeight = 33;
		int leftSlotX = 34;
		input1 = this.addSlotToContainer(new SlotAnvilInput(inventoryPlayer, inventoryRules, 0, leftSlotX, slotHeight));
		input2 = this.addSlotToContainer(new SlotAnvilInput(inventoryPlayer, inventoryRules, 1, leftSlotX+37, slotHeight));
		output = this.addSlotToContainer(new SlotAnvilOutput(inventoryPlayer, inventoryRules, 2, leftSlotX+91, slotHeight));


		//the players inventory?

		for(int rows = 0; rows < 3; ++rows) {
			for(int columns = 0; columns < 9; ++columns) {
				this.addSlotToContainer(new Slot(inventoryPlayer, columns + rows * 9 + 9, 8 + columns * 18, 84 + rows * 18));
			}
		}

		for(int slots = 0; slots < 9; ++slots) {
			this.addSlotToContainer(new Slot(inventoryPlayer, slots, 8 + slots * 18, 142));
		}

	}

	public ItemStack transferStackInSlot(int slot) {
		ItemStack var2 = null;
		Slot var3 = (Slot)this.inventorySlots.get(slot);
		if (var3 != null && var3.getHasStack()) {
			ItemStack var4 = var3.getStack();
			var2 = var4.copy();
			if (slot == 2) {
				if (!this.mergeItemStack(var4, 3, 39, true)) {
					return null;
				}

				var3.onSlotChange(var4, var2);
			} else if (slot != 1 && slot != 0) {
				if (!this.mergeItemStack(var4, 1, 2, false))
				{
					return null;
				}
			} else if (!this.mergeItemStack(var4, 3, 39, false)) {
				return null;
			}

			if (var4.stackSize == 0) {
				var3.putStack(null);
			} else {
				var3.onSlotChanged();
			}

			if (var4.stackSize == var2.stackSize) {
				return null;
			}

			var3.onPickupFromSlot(var4);
		}

		return var2;
	}

	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return true;
	}
}
