package com.example;

import java.lang.reflect.Array;

public class ArrayAppend<Type>
{
	public Type[] append(Type[] input, Type obj)
	{
		@SuppressWarnings("unchecked")
		Type[] result = (Type[]) Array.newInstance(input.getClass().getComponentType(), input.length + 1);
		System.arraycopy(input, 0, result, 0, input.length);
		result[input.length] = obj;
		return result;
	}
}
