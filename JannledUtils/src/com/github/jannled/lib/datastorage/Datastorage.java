package com.github.jannled.lib.datastorage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import com.github.jannled.lib.Print;

/**
 * This reads and writes <b>J</b>annled <b>S</b>torage <b>F</b>iles
 * @author Jannled
 * @version 0.0.2
 * @see com.github.jannled.lib.datastorage.Storage
 * @see com.github.jannled.lib.datastorage.StorageKey
 */
public class Datastorage
{
	Vector<StorageKey> storageKeys = new Vector<StorageKey>();
	
	public static void main(String[] args)
	{
		Storage storage = parseFile(new File("Organizer.jsf"));
		
		Print.m("" + storage.getValue("Videospiel.Name"));
	}
	
	/**
	 * Reads in the given file and creates a storage object, which holds every
	 * @param file The .jsf file to read in
	 * @return A storage object containing all keys
	 */
	public static Storage parseFile(File file)
	{
		Storage storage = new Storage();
		Data currentKey = storage;
		Data newKey = storage;
		int tabs = 0;
		int lineNumber = 0;
		
		if(!file.getName().endsWith(".jsf"))
		{
			Print.e("File " + file.getName() + "might not be supported, it doesn't ends with .jsf");
		}
		
		try
		{
			FileInputStream fileStream = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));
			String line;
			
			while((line = reader.readLine()) != null) //Read until end of file
			{
				int errorStat = 0;
				lineNumber++;
				
				//Check if awaiting a subKey
				if(currentKey != newKey)
				{
					currentKey.addStorageKey((StorageKey) newKey);
					currentKey = newKey;
					errorStat++;
				}
				
				//Check if line is a valid subKey
				int lineTabs = getNumberOfTabs(line);
				if(lineTabs != tabs)
				{
					if(lineTabs < tabs)
					{
						for(int j=0; j<tabs-lineTabs; j++)
						{
							StorageKey buffer = (StorageKey) currentKey;
							Data data = buffer.getParent();
							currentKey = data;
						}
					}
					else
					{
						errorStat--;
					}
					tabs = lineTabs;
				}
				
				//Check if line is valid
				if(errorStat!=0)
				{
					Print.e("Line " + lineNumber + " is not valid. Errorcode " + errorStat + "!");
					continue;
				}
				
				//Line contains a value
				if(line.contains(":"))
				{
					String key = line.split(":")[0].trim();
					String value = line.replace(key + ":", "").trim();
					StorageKey sKey = new StorageKey(key, value, (StorageKey) currentKey);
					currentKey.addStorageKey(sKey);
				}
				
				//Line contains a key
				else
				{
					if(currentKey instanceof StorageKey)
						newKey = new StorageKey(line.trim(), null, (StorageKey) currentKey);
					else
						newKey = new StorageKey(line.trim(), null, (Storage) currentKey);
				}
			}
			reader.close();
		}
		catch(FileNotFoundException e)
		{
			Print.e("File " + file.getAbsolutePath() + " not found!");
			e.printStackTrace();
		} 
		catch(IOException e)
		{
			Print.e("IOException while reading file " + file.getAbsolutePath() + "!");
			e.printStackTrace();
		}
		return storage;	
	}
	
	private static int getNumberOfTabs(String line)
	{
		int count = 0;
		for(char c : line.toCharArray())
		{
			if(c == '\t')
			{
				count++;
			}
		}
		return count;
	}
	
	public void writeFile(File file, Storage storage)
	{
		
	}
	
	public String[] generateLine(String[] text, StorageKey key)
	{
		return null;
	}
}
