package com.github.jannled.lib;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Usefull stuff for console output
 * @author Jannled
 * @version v0.1
 */
public class Print
{
	static ArrayList<String> comleteLog = new ArrayList<String>();
	static ArrayList<String> infoLog = new ArrayList<String>();
	static ArrayList<String> errorLog = new ArrayList<String>();
	
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
		System.err.println(out);
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
	 * This returns <b>error</b> log file written so far.
	 * @return The log file of this session, every index of the array contains one Print.e("Message") call
	 */
	public static String[] getErrorLog()
	{
		return ArrayUtils.toStringArray(errorLog);
	}
}
