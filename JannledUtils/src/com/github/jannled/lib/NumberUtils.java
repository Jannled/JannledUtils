package com.github.jannled.lib;

/**
 * Util class for everything with numbers
 * @author Jannled
 * @version v0.1
 */
public class NumberUtils 
{
	/**
	 * Map the input so the number is in between the min and max value
	 * @param input The number to be converted
	 * @param min The minumum number (included)
	 * @param max The maximum number (included)
	 * @return The converted number
	 */
	public int intRange(int input, int min, int max)
	{
		input = min + (input * ((max - min) + 1));
		return input;
	}
}
