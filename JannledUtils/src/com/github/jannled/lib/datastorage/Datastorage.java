package com.github.jannled.lib.datastorage;

import java.io.File;
import java.util.Vector;

import com.github.jannled.lib.Print;

public class Datastorage
{
	Vector<StorageKey> storageKeys = new Vector<StorageKey>();
	
	public void readFile(File file)
	{
		parseFile(new String[100]);
	}
	
	public StorageKey parseFile(String[] file)
	{
		StorageKey storageRoot = new StorageKey("ROOT", null, null);
		StorageKey currentKey = storageRoot;
		StorageKey newKey = storageRoot;
		int tabs = 0;
		
		for(int i=0; i<file.length; i++)
		{
			String line = file[i];
			int errorStat = 0;
			
			//Check if awaiting a subKey
			if(currentKey != newKey)
			{
				currentKey.addStorageKey(newKey);
				currentKey = newKey;
				errorStat++;
			}
			
			//Check if line is a valid subKey
			int lineTabs = getNumberOfTabs(line);
			if(lineTabs != tabs)
			{
				if(lineTabs < tabs)
				{
					StorageKey buffer = currentKey;
					for(int j=0; j<tabs-lineTabs; j++)
					{
						buffer = buffer.getParentKey();
					}
				}
				else
					errorStat--;
			}
			
			//Check if line is valid
			if(errorStat!=0)
			{
				Print.e("Line " + i + " is not valid. Errorcode " + errorStat + "!");
				continue;
			}
			
			//Line contains a value
			if(line.contains(":"))
			{
				String key = line.split(":")[0];
				String value = line.replaceFirst(key, "");
				currentKey.addStorageKey(new StorageKey(key, value, currentKey));
			}
			
			//Line contains a key
			else
			{
				newKey = new StorageKey(line, null, currentKey);
			}
		}
		
		return storageRoot;
	}
	
	private int getNumberOfTabs(String line)
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
}
