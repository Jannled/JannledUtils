package com.github.jannled.lib;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Vector;

/**
 * Usefull stuff for console output
 * @author Jannled
 * @version v0.1
 */
public class Print
{
	private static Vector<String> comleteLog = new Vector<String>();
	private static Vector<String> infoLog = new Vector<String>();
	private static Vector<String> errorLog = new Vector<String>();
	private static Vector<String> debugLog = new Vector<String>();
	
	private static LinkedList<LogListener> listeners = new LinkedList<LogListener>();

	/**
	 * 0 is everything, 1 is normal and error log, 2 is only errors and 3 disables all console output
	 */
	private static int outLevel = 1;
	
	/**
	 * Prints the given message to the console with the date prefixed
	 * @param message The message you want do display on the console
	 */
	public static void m(String message)
	{
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		Date date = new Date(timeStamp.getTime());
		Time time = new Time(timeStamp.getTime());
		
		String out = "[" + date + "] [" + time + "] [INFO] " + message;
		notifyListeners(out, Print.NORMAL);
		comleteLog.add(out);
		infoLog.add(out);
		
		if(outLevel<2)
			System.out.println(out);
	}
	
	/**
	 * Prints the given error message to the console with the date prefixed
	 * @param message The error message you want do display on the console
	 */
	public static void e(String message)
	{
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		Date date = new Date(timeStamp.getTime());
		Time time = new Time(timeStamp.getTime());

		String out = "[" + date + "] [" + time + "] [ERROR] " + message;
		notifyListeners(out, Print.ERROR);
		comleteLog.add(out);
		errorLog.add(out);
		if(outLevel<3)
			System.err.println(out);
	}
	
	/**
	 * Prints the given message to the console with the date prefixed
	 * @param message The message you want do display on the console
	 */
	public static void d(String message)
	{
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		Date date = new Date(timeStamp.getTime());
		Time time = new Time(timeStamp.getTime());
		
		String out = "[" + date + "] [" + time + "] [DEBUG] " + message;
		notifyListeners(out, Print.ALL);
		debugLog.add(out);
		if(outLevel<1)
		System.out.println(out);
	}
	
	public static void registerListener(LogListener ll)
	{
		listeners.add(ll);
	}
	
	private static void notifyListeners(String message, int level)
	{
		for(LogListener ll : listeners)
		{
			ll.notifyLog(message, level);
		}
	}
	
	/**
	 * This returns the complete log file written so far.
	 * @return The log file of this session, every index of the array contains one Print call
	 */
	public static String[] getLog()
	{
		return ArrayUtils.toStringArray(comleteLog);
	}
	
	/**
	 * This returns the <b>info</b> log written so far.
	 * @return The log file of this session, every index of the array contains one Print.m("Message") call
	 */
	public static String[] getInfoLog()
	{
		return ArrayUtils.toStringArray(infoLog);
	}
	
	/**
	 * This returns the <b>error</b> log file written so far.
	 * @return The log file of this session, every index of the array contains one Print.e("Message") call
	 */
	public static String[] getErrorLog()
	{
		return ArrayUtils.toStringArray(errorLog);
	}
	
	/**
	 * This returns the <b>debug</b> log file written so far.
	 * @return The log file of this session, every index of the array contains one Print.d("Message") call
	 */
	public static String[] getDebugLog()
	{
		return ArrayUtils.toStringArray(debugLog);
	}
	
	/**
	 * Decide what channels shall be printed to the console.
	 * @param verbose Use the static fields of this class to set the output channels. <br> For e.g. <code>setOutputLevel(<b>Print.ALL</b>)</code>
	 */
	public static void setOutputLevel(int verbose)
	{
		outLevel = verbose;
	}
	
	/**
	 * Get output level.
	 * @return The current output level.
	 */
	public static int getOutputLevel()
	{
		return outLevel;
	}
	
	/**
	 * [DEBUG] [ERROR] [NORMAL]
	 */
	public static int ALL = 0;
	
	/**
	 * [ERROR] [NORMAL]
	 */
	public static int NORMAL = 1;
	
	/**
	 * [ERROR]
	 */
	public static int ERROR = 2;
	
	/**
	 * NO CONSOLE OUTPUT
	 */
	public static int NONE = 3;
}
