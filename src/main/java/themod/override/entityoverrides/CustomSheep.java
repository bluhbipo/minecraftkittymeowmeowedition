package themod.override.entityoverrides;

import net.minecraft.src.*;
import net.minecraftforge.common.IShearable;
import themod.item.ModItemList;

public class CustomSheep extends EntitySheep implements IShearable
{
	public CustomSheep(World par1World)
	{
		super(par1World);
	}

	protected void dropFewItems(boolean par1, int par2)
	{
		int var3 = this.rand.nextInt(3) + this.rand.nextInt(1 + par2);
		int var4;
		if (!this.getSheared())
		{
			for (var4 = 0; var4 < var3; ++var4)
			{
				this.entityDropItem(new ItemStack(Block.cloth.blockID, 1, this.getFleeceColor()), 0.0F);
			}
		}
		var3 = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + par2);

		for (var4 = 0; var4 < var3; ++var4)
		{
			if (this.isBurning())
			{
				this.dropItem(((ItemFood) ModItemList.cookedMutton).shiftedIndex, 1);
			} else
			{
				this.dropItem(((ItemFood) ModItemList.mutton).shiftedIndex, 1);
			}
		}
	}
}
