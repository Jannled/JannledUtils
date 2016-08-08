package com.github.jannled.lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 * Util class for everything that has to do with files
 * @author Jannled
 * @version 0.1
 */
public class FileUtils
{
	/**
	 * Reads the specified text-file in a String array.
	 * @param file The text file to be read.
	 * @return The text file where each element in the array contains one line of the file.
	 */
	public static String[] readTextFile(File file)
	{
		Vector<String> string = new Vector<String>();
		try
		{
			FileInputStream fileStream = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));
			String line;
			
			while((line = reader.readLine()) != null) //Read until end of file
			{
				string.add(line);
			}
	
			reader.close();
		}
		catch(FileNotFoundException e)
		{
			Print.e("File " + file.getPath() + " not found!");
			e.printStackTrace();
		} 
		catch(IOException e)
		{
			Print.e("IOException!");
			e.printStackTrace();
		}
		return ArrayUtils.toStringArray(string);
	}
	
	/**
	 * Reads the specified text-file into a single String with Newline-Charackters at the end of each line.
	 * @param file The text file to be read.
	 * @return The text file where each element in the array contains one line of the file.
	 */
	public static String readTextFileN(File file)
	{
		StringBuilder string = new StringBuilder();
		try
		{
			FileInputStream fileStream = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));
			String line = "";
			
			while((line = reader.readLine()) != null) //Read until end of file
			{
				string.append(line);
				string.append(System.lineSeparator());
			}
	
			reader.close();
		}
		catch(FileNotFoundException e)
		{
			Print.e("File " + file.getPath() + " not found!");
			e.printStackTrace();
		} 
		catch(IOException e)
		{
			Print.e("IOException!");
			e.printStackTrace();
		}
		return string.toString();
	}
}
