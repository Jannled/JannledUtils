package com.github.jannled.lib;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Jannled
 * @version v0.3
 */
public class TimeUtils 
{
	/**
	 * Method for getting the Systems time in seconds
	 * @return The seconds between the current time and midnight, January 1, 1970 UTC.
	 */
	public static int timeInSeconds()
	{
		long time = System.currentTimeMillis();
		time = TimeUnit.MILLISECONDS.toSeconds(time);
		return (int) time;
	}
	
	/**
	 * 
	 * @param Zeit Time in seconds to be converted into digital time.
	 * @return Returns a string with with the time seperated by a : like you would see it on a digital clock
	 */
	public static String toDigiTime(int Zeit)
	{
		String Digi;
		Digi = "" + Zeit/60 + ":" + ((Zeit-Zeit/60*60) < 10 ? "0" + (Zeit-Zeit/60*60) : (Zeit-Zeit/60*60));
		return Digi;
	}
}
