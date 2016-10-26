package com.github.jannled.lib;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
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
		comleteLog.add(out);
		infoLog.add(out);
		
		if(outLevel>1)
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
		comleteLog.add(out);
		errorLog.add(out);
		if(outLevel>2)
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
		debugLog.add(out);
		if(outLevel>0)
		System.out.println(out);
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
	 * 
	 * @param verbose
	 */
	public static void setOutputLevel(int verbose)
	{
		outLevel = verbose;
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
	 * [NORMAL]
	 */
	public static int ERROR = 2;
	
	/**
	 * NO CONSOLE OUTPUT
	 */
	public static int NONE = 3;
}
