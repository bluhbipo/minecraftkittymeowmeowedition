package com.example;

public class TextUtils
{
	public static String toTitleCase(String word)
	{
		return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
	}
}
