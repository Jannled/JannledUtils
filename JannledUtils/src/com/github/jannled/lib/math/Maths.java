package com.github.jannled.lib.math;

public class Maths
{
	public static int map(int value, int min1, int max1, int min2, int max2)
	{
		return (value-min1) * (max2-min2) / (max1-min1) + min2;
	}
}
