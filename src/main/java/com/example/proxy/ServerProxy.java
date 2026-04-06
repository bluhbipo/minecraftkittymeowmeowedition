package com.example.proxy;

import com.example.gui.anvil.ContainerAnvil;
import com.example.gui.anvil.GuiAnvil;
import com.example.gui.anvil.InventoryAnvil;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;

public class ServerProxy extends CommonProxy implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int j, int k, int l)
	{
		switch (ID)
		{
			case 100: return new ContainerAnvil(player.inventory, new InventoryAnvil());
			default: return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int j, int k, int l)
	{
		switch (ID)
		{
			case 100: return new GuiAnvil(new ContainerAnvil(player.inventory, new InventoryAnvil()));
			default: return null;
		}
	}
}
