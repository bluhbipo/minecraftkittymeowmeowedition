package com.example.player;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.src.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyBinds
{
	static KeyBinding[] keybinds = Minecraft.getMinecraft().gameSettings.keyBindings;
	public enum CustomBinds
	{
		SPRINT(new KeyBinding("Sprint", Keyboard.KEY_LCONTROL))
		;
		final KeyBinding key;
		CustomBinds(KeyBinding b)
		{
			this.key = b;
		}
	}
	@SideOnly(Side.CLIENT)
	public static void inject()
	{
		int moddedLength = keybinds.length + CustomBinds.values().length;
		KeyBinding[] updatedReference = new KeyBinding[moddedLength];
		for(int i = 0; i < moddedLength; i++)
		{
			if (i >= keybinds.length)
			{
				updatedReference[i] = CustomBinds.values()[i - keybinds.length].key;
			} else
			{
				updatedReference[i] = keybinds[i];
			}
		}
		Minecraft.getMinecraft().gameSettings.keyBindings = updatedReference;
	}

	public static void keyLogic(CustomBinds b)
	{
		switch (b)
		{
			case SPRINT:
				Minecraft.getMinecraft().thePlayer.setSprinting(true);
				break;
			default:
				break;
		}
	}
}
