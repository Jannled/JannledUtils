package com.github.jannled.lib;

import java.util.ArrayList;

/**
 * Util class for arrays.
 * @author Jannled
 * @version v0.2
 */
public class ArrayUtils
{
	/**
	 * Convert an Array to a comma seperated String
	 * @param array The array to be processed
	 * @return The Array with comma seperated elements
	 */
	public static String arrayToString(String[] array)
	{
		String output = "";
		for(int i=0; i<array.length; i++)
		{
			if(output.equals(""))
			{
				output = output + array[i];
			}
			else
			{
				output = output + ", " + array[i];
			}
		}
		return output;
	}
	
	/**
	 * Convert an Array to a comma seperated String
	 * @param array The array to be processed
	 * @return The Array with comma seperated elements
	 */
	public static String arrayToString(double[] array)
	{
		String output = "";
		for(int i=0; i<array.length; i++)
		{
			if(output.equals(""))
			{
				output = output + array[i];
			}
			else
			{
				output = output + ", " + array[i];
			}
		}
		return output;
	}
	
	/**
	 * Convert an ArrayList to a simple Array
	 * @param input An Integer ArrayList
	 * @return A simple Int Array
	 */
	public static int[] toIntArray(ArrayList<Integer> input)
	{
		int len = input.size();
		int[] output = new int[len];
		for(int i=0; i<len; i++)
		{
			output[i] = input.get(i);
		}
		return output;
	}
	
	/**
	 * Convert an ArrayList to a simple Array
	 * @param input A String ArrayList
	 * @return A simple String Array
	 */
	public static String[] toStringArray(ArrayList<String> input)
	{
		int len = input.size();
		String[] output = new String[len];
		for(int i=0; i<len; i++)
		{
			output[i] = input.get(i);
		}
		return output;
	}
}
