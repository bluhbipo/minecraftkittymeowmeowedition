package com.example.gui.anvil;

import net.minecraft.src.*;

public class ContainerAnvil extends Container
{

	public final Slot input1;
	public final Slot input2;
	public final Slot output;

	ContainerBrewingStand b;

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

	public ItemStack transferStackInSlot(int par1) {
		Slot slot = (Slot)this.inventorySlots.get(par1);
		if (slot == null || !slot.getHasStack()) return null;

		ItemStack stack = slot.getStack();
		ItemStack result = stack.copy();

		if (par1 == 0 || par1 == 1 || par1 == 2) {
			if (!mergeItemStack(stack, 3, 39, false)) return null;
		} else {
			boolean moved = false;
			for (int i = 0; i <= 1; i++) {
				Slot target = (Slot)this.inventorySlots.get(i);
				if (target.getHasStack() || !target.isItemValid(stack)) continue;
				if (mergeItemStack(stack, i, i + 1, false)) {
					moved = true;
					break;
				}

			}
			if (!moved && !mergeItemStack(stack, 3, 39, false)) return null;
		}

		if (stack.stackSize == 0) {
			slot.putStack(null);
		} else {
			slot.onSlotChanged();
		}

		if (stack.stackSize == result.stackSize) return null;

		slot.onPickupFromSlot(stack);
		return result;
	}

	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return true;
	}
}
