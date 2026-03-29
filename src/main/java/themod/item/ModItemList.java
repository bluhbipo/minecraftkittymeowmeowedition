package themod.item;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import themod.OwnerCode;
import themod.block.ModBlockBuilder;
import themod.block.ModifiedBlock;

import java.util.HashSet;
import java.util.Set;

public class ModItemList
{

	protected static Set<ModItem> allItems = new HashSet<>();
	public static Set<ModItem> get()
	{
		if(allItems.isEmpty()) generate();
		return allItems;
	}
	public static ModifiedItem mutton;
	public static ModifiedItem cookedMutton;
	public static void generate()
	{
		mutton = new ModItemBuilder()
			.setTextureCoords(0, 0)
			.setName("Raw Mutton")
			.isEdible()
			.setHungerPoints(3)
			.setSaturation(0.3f)
			.buildAndRegister();
		cookedMutton = new ModItemBuilder()
			.setTextureCoords(0, 1)
			.setName("Cooked Mutton")
			.isEdible()
			.setHungerPoints(8)
			.setSaturation(0.8f)
			.cookedFrom(mutton)
			.buildAndRegister();

	}
}
