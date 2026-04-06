package com.example.gui.anvil;

import net.minecraft.src.*;

public class ContainerAnvil extends Container
{

	private final Slot input1;
	private final Slot input2;
	private final Slot output;

	ContainerBrewingStand b;

	public ContainerAnvil(InventoryPlayer inventoryPlayer, IInventory inventoryRules) {

		int slotHeight = 34;
		int leftSlotX = 35;
		input1 = this.addSlotToContainer(new SlotAnvilInput(inventoryPlayer, inventoryRules, 0, leftSlotX, slotHeight));
		input2 = this.addSlotToContainer(new SlotAnvilInput(inventoryPlayer, inventoryRules, 1, leftSlotX+36, slotHeight));
		output = this.addSlotToContainer(new SlotAnvilOutput(inventoryPlayer, inventoryRules, 2, leftSlotX+100, slotHeight));


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

	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return true;
	}

	public ItemStack transferStackInSlot(int slotIndex) {
		ItemStack var2 = null;
		Slot targetSlot = (Slot)this.inventorySlots.get(slotIndex);
		if (targetSlot != null && targetSlot.getHasStack()) {
			ItemStack stackInSlot = targetSlot.getStack();
			var2 = stackInSlot.copy();
			if ((slotIndex < 0 || slotIndex > 2) && slotIndex != 3) {
				if (!this.mergeItemStack(stackInSlot, 3, 4, false))
				{
					return null;
				}
			} else {
				if (!this.mergeItemStack(stackInSlot, 4, 39, true)) {
					return null;
				}

				targetSlot.onSlotChange(stackInSlot, var2);
			}

			if (stackInSlot.stackSize == 0) {
				targetSlot.putStack((ItemStack)null);
			} else {
				targetSlot.onSlotChanged();
			}

			if (stackInSlot.stackSize == var2.stackSize) {
				return null;
			}

			targetSlot.onPickupFromSlot(stackInSlot);
		}

		return var2;
	}
}
