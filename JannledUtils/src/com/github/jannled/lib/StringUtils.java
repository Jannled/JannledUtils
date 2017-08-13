package com.github.jannled.lib;

public class StringUtils 
{
	public String getLineBreak()
	{
		return System.getProperty("line.seperator");
	}
	
	/**
	 * Checks if the String is a valid number.
	 * @param number A number from the decimal system, can be negative
	 * @return If the String is a number (<b>true</b>) or not (<b>false</b>).
	 * @author CraigTP
	 */
	public static boolean isNumber(String number)
	{
		 return number.matches("-?\\d+(\\.\\d+)?");
	}
}
