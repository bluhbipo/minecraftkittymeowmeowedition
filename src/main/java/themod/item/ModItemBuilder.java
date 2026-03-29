package themod.item;

import net.minecraft.src.ItemAppleGold;
import themod.OwnerCode;
import themod.block.ModBlockBuilder;

public class ModItemBuilder extends ModItemList
{
	OwnerCode<Object> rightClick;
	int tx = 0;
	int ty = 0;
	String name;
	boolean isEdible = false;
	boolean meatness = false;
	float saturationModifier; //usually 0.3 or 0.8
	int hungerPoints;
	ModifiedItem cookedFrom;

	public ModItemBuilder setRightClick(OwnerCode<Object> rc)
	{
		rightClick = rc;
		return this;
	}
	public ModItemBuilder setTextureCoords(int x, int y)
	{
		tx = x;
		ty = y;
		return this;
	}
	public ModItemBuilder setName(String n)
	{
		this.name = n;
		return this;
	}
	public ModItemBuilder isEdible()
	{
		this.isEdible = true;
		return this;
	}
	public ModItemBuilder isMeat()
	{
		this.meatness = true;
		return this;
	}
	public ModItemBuilder setHungerPoints(int h)
	{
		hungerPoints = h;
		return this;
	}
	public ModItemBuilder setSaturation(float s)
	{
		saturationModifier = s;
		return this;
	}
	public ModItemBuilder cookedFrom(ModifiedItem c)
	{
		cookedFrom = c;
		return this;
	}
	public ModifiedItem buildAndRegister()
	{
		if(isEdible) return new ModItemFood(this);
		return new ModItem(this);
	}
}
