package com.github.jannled.lib.math;

public class Maths
{
	/**
	 * Map a number to another range of numbers.
	 * @param value The value to map.
	 * @param min1 The minimum of the input value. 
	 * @param max1 The maximum of the input value.
	 * @param min2 The minimum of the output value.
	 * @param max2 The maximum of the output value.
	 * @return The number mapped to the specified range.
	 */
	public static int map(int value, int min1, int max1, int min2, int max2)
	{
		return (value-min1) * (max2-min2) / (max1-min1) + min2;
	}
	
	/**
	 * Round the result to the number of decimal places.
	 * @param value The value to round.
	 * @param decimals The amount of decimal places.
	 * @return The rounded value.
	 */
	public static double round(double value, int decimals)
	{
		int shift = (int) Math.pow(10, decimals);
		value = value * shift;
		value = Math.round(value);
		value = value / shift;
		return value;
	}
}
