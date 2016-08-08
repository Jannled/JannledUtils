package com.github.jannled.lib;

import java.util.ArrayList;
import java.util.Vector;

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
	
	/**
	 * Convert an ArrayList to a simple Array
	 * @param input A String ArrayList
	 * @return A simple String Array
	 */
	public static String[] toStringArray(Vector<String> input)
	{
		int len = input.size();
		String[] output = new String[len];
		for(int i=0; i<len; i++)
		{
			output[i] = input.get(i);
		}
		return output;
	}
	
	/**
	 * Turns a 2Dimensional array in a 1Dimensional array.
	 * @param data A 2-Dimensional float array.
	 * @return A 1-Dimensional float array.
	 */
	public static float[] to1DArray(float[][] data)
	{
		int length = (data.length + 1) * data[0].length;
		float[] output = new float[length];
		
		for(int x=0; x<data.length; x++)
		{
			for(int y=0; y<data[0].length; y++)
			{
				output[x*data[0].length + y] = data[x][y];
			}
		}
		return output;
	}
	
	/**
	 * Turns a 2Dimensional array in a 1Dimensional array.
	 * @param data A 2-Dimensional double array.
	 * @return A 1-Dimensional double array.
	 */
	public static double[] to1DArray(double[][] data)
	{
		int length = (data.length + 1) * data[0].length;
		Print.m("Converting Mesh to 1D-Array. Length: " + data.length + "|" + data[0].length);
		double[] output = new double[length];
		
		for(int x=0; x<data.length; x++)
		{
			for(int y=0; y<data[0].length; y++)
			{
				output[x*data[0].length + y] = data[x][y];
			}
		}
		return output;
	}
}
