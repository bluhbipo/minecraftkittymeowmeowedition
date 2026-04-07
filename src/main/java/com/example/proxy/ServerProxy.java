package com.example.proxy;

import com.example.gui.GuiRegister;
import com.example.gui.anvil.ContainerAnvil;
import com.example.gui.anvil.GuiAnvil;
import com.example.gui.anvil.InventoryAnvil;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.src.*;

import java.lang.reflect.Constructor;

public class ServerProxy extends CommonProxy implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int j, int k, int l)
	{
		for (GuiRegister g : GuiRegister.values())
		{
			if(g.guiID!=ID) return null;
			try
			{
				Constructor<? extends Container> containerConstructor = g.guiContainer.getConstructor(InventoryPlayer.class, IInventory.class);
				return containerConstructor.newInstance(player.inventory, g.guiInventory.newInstance());
			} catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int j, int k, int l)
	{
		for (GuiRegister g : GuiRegister.values())
		{
			if(g.guiID!=ID) return null;
			try
			{
				Constructor<? extends GuiContainer> guiConstructor = g.guiClass.getConstructor(Container.class);
				Constructor<? extends Container> containerConstructor = g.guiContainer.getConstructor(InventoryPlayer.class, IInventory.class);
				return guiConstructor.newInstance(
					containerConstructor.newInstance(
						player.inventory,
						g.guiInventory.newInstance()
					)
				);
			} catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
}
