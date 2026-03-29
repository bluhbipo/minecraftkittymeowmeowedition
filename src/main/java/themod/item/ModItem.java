package themod.item;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import themod.OwnerCode;

import java.util.HashMap;
import java.util.Map;

public class ModItem extends Item implements ModifiedItem
{
	public static Map<Integer, ModifiedItem> getItemByID = new HashMap<>();
	public OwnerCode<Object> rightClickLogic;
	public boolean alwaysEdible;
	String name = "";
	public ModItem(ModItemBuilder struct)
	{
		super(ModItemDefaults.id);
		rightClickLogic = struct.rightClick;
		name=struct.name;
		ModItemDefaults.init(this, struct);

		getItemByID.put(256+ModItemDefaults.id, this);
		ModItemDefaults.id++;
	}

	@Override
	public String getTextureFile(){
		return "/mods/themod/textures/items.png";
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {

		if (par3EntityPlayer.canEat(this.alwaysEdible)) {
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		}

		return par1ItemStack;
	}


	@Override
	public String getItemDisplayName(ItemStack par1ItemStack)
	{
		return name;
	}

	public static ModifiedItem getModItemFromItemStack(ItemStack i)
	{
		return ModItem.getItemByID.get(i.itemID);
	}

}
