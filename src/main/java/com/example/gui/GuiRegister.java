package com.example.gui;

import com.example.gui.anvil.ContainerAnvil;
import com.example.gui.anvil.GuiAnvil;
import com.example.gui.anvil.InventoryAnvil;
import net.minecraft.src.Container;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.IInventory;

public enum GuiRegister
{
	ANVIL(100, GuiAnvil.class, ContainerAnvil.class, InventoryAnvil.class)
	;
	public final int guiID;
	public final Class<? extends GuiContainer> guiClass;
	public final Class<? extends Container> guiContainer;
	public final Class<? extends IInventory> guiInventory;
	GuiRegister(int id, Class<? extends GuiContainer> g, Class<? extends Container> c, Class<? extends IInventory> i)
	{
		guiID = id;
		guiClass = g;
		guiContainer = c;
		guiInventory = i;
	}
}
