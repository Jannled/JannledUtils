package com.github.jannled.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Vector;

/**
 * Util class for everything that has to do with files
 * @author Jannled
 * @version 0.1
 */
public class FileUtils
{
	static Charset charset = Charset.forName("UTF-8");
	
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
	
	/**
	 * Writes the specified text-file to the specified location. It will create the file if it doesn't exist.
	 * @param path Path where the text-file should be created. Note: Overwrites existing files!
	 * @param text The text to write, every array entry is a new line.
	 */
	public static void writeTextFile(String path, String[] text)
	{
		writeTextFile(new File(path), text);
	}
	
	/**
	 * Writes the specified text-file to the specified location. It will create the file if it doesn't exist.
	 * @param path Path where the text-file should be created. Note: Overwrites existing files!
	 * @param text The text to write, every array entry is a new line.
	 * @return The operations done: <br> 2: If the file already exist and is being overwritten <br> 3: If it needed to create the file and/or parent directorys <br>-1: If it couldn't create the file and/or parent directorys <br>-2: If it couldn't write text in the specified file
	 */
	public static int writeTextFile(File path, String[] text)
	{
		int writeSuccessfull = 0;
		if(!path.exists())
		{
			try
			{
				path.getParentFile().mkdirs();
				path.createNewFile();
				writeSuccessfull = 1;
			} catch (IOException e)
			{
				writeSuccessfull = -1;
				Print.e("IOException while creating file " + path.getAbsolutePath());
				e.printStackTrace();
			}
		}
		try
		{
			FileOutputStream stream = new FileOutputStream(path);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream, charset));
			for(String s : text)
			{
				writer.write(s);
				writer.newLine();
			}
			writeSuccessfull = writeSuccessfull+2;
			writer.flush();
			writer.close();
		} catch (IOException e)
		{
			writeSuccessfull = -2;
			Print.e("IOExcption while reading file " + path.getAbsolutePath());
			e.printStackTrace();
		}
		return writeSuccessfull;
	}

	/**
	 * Gets the charset used by the write method.
	 * @return Charset used by the read method, by default, UTF-8.
	 */
	public static Charset getCharset()
	{
		return charset;
	}

	/**
	 * Sets the charset used by the write method.
	 * @param charset Charset to be set.
	 */
	public static void setCharset(Charset charset)
	{
		FileUtils.charset = charset;
	}
	
	/**
	 * Sets the charset used by the write method.
	 * @param charset Charset to be set.
	 */
	public static void setCharset(String charset)
	{
		FileUtils.charset = Charset.forName(charset);
	}
}
