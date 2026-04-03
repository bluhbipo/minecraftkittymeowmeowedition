package com.example.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.*;

public class Map2<K1,K2,V> {

	private Map<K1,Map<K2,V>> internal;
	public Map2()
	{
		internal = new HashMap<>();
	}
	public void put(K1 k1, K2 k2, V v)
	{
		if(internal.containsKey(k1))
		{
			internal.get(k1).put(k2,v);
			return;
		}
		Map<K2, V> toPut = new HashMap<>();
		toPut.put(k2, v);
		internal.put(k1, toPut);
		System.out.println("adding new key:"+k1);
	}

	public V get(K1 k1, K2 k2)
	{
		if(!internal.containsKey(k1))
		{
			return null;
		}else{
			return internal.get(k1).get(k2);
		}
	}
}

