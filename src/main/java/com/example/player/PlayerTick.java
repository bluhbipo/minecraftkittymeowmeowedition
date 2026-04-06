package com.example.player;

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
	}
}
