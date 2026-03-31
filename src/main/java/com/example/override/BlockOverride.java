package com.example.override;

import com.example.ArrayAppend;
import net.minecraft.src.*;
import sun.misc.Unsafe;
import com.example.block.Paths;
import com.example.override.blockoverrides.CustomObsidian;

import java.lang.reflect.Field;

public class BlockOverride
{
	public static void inject() {

		replaceBlockInstance(Block.obsidian, CustomObsidian.class);
		new ArrayAppend<Block>().append(ItemPickaxe.blocksEffectiveAgainst, Block.obsidian);



		Block.cobblestone.setTextureFile(Paths.block1);
		Block.cobblestone.blockIndexInTexture = 1;

		Block.stairCompactCobblestone.setTextureFile(Paths.block1);
		Block.stairCompactCobblestone.blockIndexInTexture = 1;

		Block.cobblestoneMossy.setTextureFile(Paths.block1);
		Block.cobblestoneMossy.blockIndexInTexture = 3;

		Block.gravel.setTextureFile(Paths.block1);
		Block.gravel.blockIndexInTexture = 2;

		Block.blockSteel.setTextureFile(Paths.block1);
		Block.blockSteel.blockIndexInTexture = 6;


	}

	private static Unsafe getUnsafe() throws Exception {
		Field f = Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		return (Unsafe) f.get(null);
	}

	public static void replaceBlockInstance(Block oldBlock, Class<? extends Block> newBlockClass) {
		try {

			Block.blocksList[oldBlock.blockID] = null;
			Block newBlock = newBlockClass.newInstance();

			for (Field field : Block.class.getDeclaredFields()) {
				if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())) continue;
				field.setAccessible(true);
				Object value = field.get(null);
				if (value == oldBlock) {

					Unsafe unsafe = getUnsafe();
					long offset = unsafe.staticFieldOffset(field);
					unsafe.putObject(field.getDeclaringClass(), offset, newBlock);
					Block.blocksList[newBlock.blockID] = newBlock;



					return;
				}
			}

			throw new IllegalArgumentException("Old block instance not found in Block class");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
