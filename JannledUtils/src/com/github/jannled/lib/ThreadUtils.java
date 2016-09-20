package com.github.jannled.lib;

/**
 * Utils class for everything that has to do with Threads
 * @author Jannled
 * @version 0.0.1
 */
public class ThreadUtils 
{
	/**
	 * Freezes this thread. Note: this can also be the main Thread. Use with care!
	 * @param duration The time how long the Thread should sleep.
	 */
	public static void freeze(long duration)
	{
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			Print.e("Threads sleep time of " + duration + " milliseconds has been interrupted.");
			e.printStackTrace();
		}
	}
}
