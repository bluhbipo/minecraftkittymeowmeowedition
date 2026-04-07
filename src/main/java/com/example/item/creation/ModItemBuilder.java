package com.example.item.creation;

import com.example.ItemOrBlock;
import com.example.OwnerCode;
import com.example.item.handler.ModItem;
import com.example.item.handler.ModItemArmour;
import com.example.item.handler.ModItemFood;
import com.example.item.ModItemList;
import com.example.item.ModifiedItem;
import com.example.item.handler.ModItemTool;
import org.jetbrains.annotations.NotNull;

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
	public ItemOrBlock cookedFrom;
	public Integer armourType;
	public ItemTypeAG autogenItemType;
	public float mineSpeed;

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
	public ModItemBuilder cookedFrom(ItemOrBlock c)
	{
		cookedFrom = c;
		return this;
	}
	public ModItemBuilder setArmourType(int a)
	{
		armourType = a;
		return this;
	}
	public ModItemBuilder setMiningSpeed(float s)
	{
		mineSpeed = s;
		return this;
	}
	@NotNull
	public ModifiedItem buildAndRegister()
	{
		if(isEdible) return new ModItemFood(this);

		ModifiedItem toReturn;
		if(armourType!=null)
		{
			toReturn = new ModItemArmour(this);
		}else{
			if(autogenItemType != null && autogenItemType.getSuperType() == ItemTypeAG.SuperTypes.TOOL)
			{
				toReturn = new ModItemTool(this);
			}else{
				toReturn = new ModItem(this);
			}
		}
		if(autogenMaterial != null)
		{
			MaterialAG.modItemMap.put(autogenMaterial, autogenItemType, toReturn);
		}
		return toReturn;

	}

	public ModItemBuilder setAutogenType(ItemTypeAG t)
	{
		this.autogenItemType = t;
		return this;
	}

	public MaterialAG autogenMaterial;
	public ModItemBuilder setAutogenMaterial(MaterialAG m)
	{
		this.autogenMaterial = m;
		return this;
	}
}
