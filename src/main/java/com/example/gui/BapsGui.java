package com.example.gui;

import net.minecraft.src.Container;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.IInventory;

public abstract class BapsGui extends GuiContainer
{
	public BapsGui(Container container)
	{
		super(container);
	}
	public abstract Class<? extends IInventory> getInventoryClass();
	public abstract Class<? extends  Container> getContainerClass();
	public abstract int getGuiID();
}
