package com.github.jannled.lib;

import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * Util class for everything that has to do with Processes
 * @author Jannled
 * @version 0.0.2
 * @see java.lang.Process
 */
public class ProcessUtils 
{
	/**
	 * This gets the procces ID for the specified process
	 * @param process The process to get the process ID from.
	 * @return The Process ID of the specified process.
	 * @author Jared Rummler
	 */
	public static int getProcessID(Process process)
	{
	   try {
	        Class<?> cProcessImpl = process.getClass();
	        java.lang.reflect.Field fPid = cProcessImpl.getDeclaredField("pid");
	        		
	        if (!fPid.isAccessible()) 
	        {
	            fPid.setAccessible(true);
	        }
	        
	        return fPid.getInt(process);
	        
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return -1;
	    }
	}
	
	/**
	 * This gets the procces ID for the current Java Virtual Machine
	 * @return The Process ID of the current JVM.
	 */
	public static int getProcessID()
	{
		int ID = -1;
		String processName = ManagementFactory.getRuntimeMXBean().getName();
		String processID = processName.split("@")[0];
		try {
		ID = Integer.parseInt(processID);
		} catch(NumberFormatException e) {
			Print.e("Failed to get Process ID. Not a valid number found before regex @.");
		}
		return ID;
	}
	
	/**
	 * Kills the specified process with the system console.
	 * @param process A Java created Process which should be terminated.
	 */
	public static void killProcess(Process process)
	{
		int ID = getProcessID(process);
		killProcess(ID);
	}
	
	/**
	 * Kills the specified process with the system console.
	 * @param ID The process ID of the program which should be terminated.
	 */
	public static void killProcess(int ID)
	{
		String OS = System.getProperty("os.name"); 
		if(OS.contains("Windows"))
		{
			try {
				Runtime.getRuntime().exec("taskkill /PID " + ID);
			} catch (IOException e) {
				Print.e("Failed to execute code on Windows.");
				e.printStackTrace();
			}
		}
		else
		{
			Print.e(OS + " is currently not supported. Sorry :/");
		}
	}
}
