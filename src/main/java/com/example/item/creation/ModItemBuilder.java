package com.example.item.creation;

import com.example.OwnerCode;
import com.example.item.handler.ModItem;
import com.example.item.handler.ModItemArmour;
import com.example.item.handler.ModItemFood;
import com.example.item.ModItemList;
import com.example.item.ModifiedItem;
import com.example.item.handler.ModItemTool;

public class ModItemBuilder extends ModItemList
{
	public OwnerCode<Object> rightClick;
	public int tx = 0;
	public int ty = 0;
	public String name;
	public boolean isEdible = false;
	public boolean meatness = false;
	public float saturationModifier; //usually 0.3 or 0.8
	public int hungerPoints;
	public ModifiedItem cookedFrom;
	public Integer armourType;
	public AutogenMaterialItem toolType;

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
	public ModItemBuilder setArmourType(int a)
	{
		armourType = a;
		return this;
	}
	public ModifiedItem buildAndRegister()
	{
		if(isEdible) return new ModItemFood(this);
		if(armourType!=null) return new ModItemArmour(this);
		if(toolType!=null && toolType.isTool()) return new ModItemTool(this);
		return new ModItem(this);


	}

	public int enchantability;
	public ModItemBuilder setEnchantability(int e)
	{
		enchantability = e;
		return this;
	}

	public int durabilityFactor;
	public ModItemBuilder setDurabilityFactor(int d)
	{
		this.durabilityFactor=d;
		return this;
	}

	public int armourRenderIndex;
	public ModItemBuilder setArmourRenderIndex(int a)
	{
		this.armourRenderIndex = a;
		return this;
	}

	public ModItemBuilder setToolType(AutogenMaterialItem t)
	{
		this.toolType = t;
		return this;
	}
}
