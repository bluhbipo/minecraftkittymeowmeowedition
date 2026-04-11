package com.example.player;

import com.example.gui.hud.GuiHud;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import org.lwjgl.input.Keyboard;

public class PlayerTick
{
	public static void tick(EntityPlayer p) {
		for(KeyBinds.CustomBinds k : KeyBinds.CustomBinds.values())
		{
			if(!Keyboard.isKeyDown(k.key.keyCode)) continue;
			KeyBinds.keyLogic(k);
		}
		if(p instanceof EntityPlayerSP)
		{
			//GuiMana.getInstance(Minecraft.getMinecraft()).renderManaBar(10);
		}
		if(!(Minecraft.getMinecraft().ingameGUI instanceof GuiHud))
		{
			System.out.println("resetting");
			Minecraft.getMinecraft().ingameGUI = new GuiHud(Minecraft.getMinecraft());
		}

	}
}
