package themod.override;

import net.minecraft.src.*;
import net.minecraftforge.common.IShearable;
import themod.item.ModItemFood;
import themod.item.ModItemList;
import themod.override.entityoverrides.CustomSheep;

import java.lang.reflect.Field;
import java.util.List;

public class MobDropOverride
{

	public static void inject()
	{
		EntityList.addMapping(CustomSheep.class, "Sheep", 91, 15198183, 16758197);
		for(int i = 0; i < BiomeGenBase.biomeList.length; i++)
		{
			if(BiomeGenBase.biomeList[i] == null) continue;
			updateEntitySpawns(BiomeGenBase.biomeList[i], EntitySheep.class, CustomSheep.class, 12, 4, 4);
		}

	}
	public static void updateEntitySpawns(BiomeGenBase target,
										  Class<? extends Entity> old,
										  Class<? extends Entity> updated,
										  int w1, int w2, int w3)
	{
		try {
			Field field;

			// Handle deobf + obf names
			try {
				field = BiomeGenBase.class.getDeclaredField("spawnableCreatureList");
				field.setAccessible(true);
			} catch (NoSuchFieldException e) {
				field = findCreatureListField(); // fallback by type
			}

			field.setAccessible(true);

			@SuppressWarnings("unchecked")
			List list = (List) field.get(target);

			for (int i = 0; i < list.size(); i++) {
				SpawnListEntry entry = (SpawnListEntry) list.get(i);

				if (entry.entityClass == old) {
					list.set(i, new SpawnListEntry(updated, w1, w2, w3));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static Field findCreatureListField() {
		for (Field f : BiomeGenBase.class.getDeclaredFields()) {
			if (List.class.isAssignableFrom(f.getType())) {
				f.setAccessible(true);
				return f;
			}
		}
		throw new RuntimeException("Could not find spawnableCreatureList field");
	}



}
