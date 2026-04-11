package com.example.override;

import com.example.gui.hud.GuiHud;
import net.minecraft.client.Minecraft;

public class GuiOverride
{
	public static void inject(Minecraft minecraft)
	{
		minecraft.ingameGUI = new GuiHud(minecraft);
	}
}
