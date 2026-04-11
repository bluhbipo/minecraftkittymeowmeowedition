package com.example.gui;

public class GuiHelper
{
	public static enum Colour
	{
		//colours are in ARBG because fuck you
		FONT(0x00404040),
		XPGREEN(0x0084f721),
		INVALID(0x00e05b5c);
		public final int hex;
		Colour(int hex)
		{
			this.hex = hex;
		}
	}
}
