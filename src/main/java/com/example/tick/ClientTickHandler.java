package com.example.tick;

import com.example.player.PlayerTick;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.Minecraft;

import java.util.EnumSet;

public class ClientTickHandler implements ITickHandler
{
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		if (type.contains(TickType.CLIENT))
		{
			PlayerTick.tick(Minecraft.getMinecraft().thePlayer);
		}
	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel()
	{
		return "ClientTick";
	}
}