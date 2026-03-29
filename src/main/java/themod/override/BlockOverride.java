package themod.override;

import net.minecraft.src.*;
import sun.misc.Unsafe;
import themod.block.Paths;
import themod.override.blockoverrides.CustomObsidian;

import java.lang.reflect.Field;

public class BlockOverride
{
	public static void inject() {

		replaceBlockInstance(Block.obsidian, CustomObsidian.class);

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

	/**
	 * Replaces a static final block field with a new instance safely.
	 * Assumes newBlockClass has an empty constructor.
	 */
	public static void replaceBlockInstance(Block oldBlock, Class<? extends Block> newBlockClass) {
		try {
			// 1️⃣ Null the blocksList slot first
			Block.blocksList[oldBlock.blockID] = null;

			// 2️⃣ Create new instance
			Block newBlock = newBlockClass.newInstance();

			// 3️⃣ Find the static field pointing to oldBlock
			for (Field field : Block.class.getDeclaredFields()) {
				if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())) continue;
				field.setAccessible(true);
				Object value = field.get(null);
				if (value == oldBlock) {
					// 4️⃣ Overwrite the static final field using Unsafe
					Unsafe unsafe = getUnsafe();
					long offset = unsafe.staticFieldOffset(field);
					unsafe.putObject(field.getDeclaringClass(), offset, newBlock);

					// 5️⃣ Replace blocksList with new instance
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
