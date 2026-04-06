package com.example.proxy;

import com.example.gui.anvil.ContainerAnvil;
import com.example.gui.anvil.GuiAnvil;
import com.example.gui.anvil.InventoryAnvil;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends ServerProxy
{
	@SideOnly(Side.CLIENT)
	public static void registerRenderers() {
		MinecraftForgeClient.preloadTexture("/mods/themod/textures/items.png");
		MinecraftForgeClient.preloadTexture("/mods/themod/textures/blocks.png");
	}

}
