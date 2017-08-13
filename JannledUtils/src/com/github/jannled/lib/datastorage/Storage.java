package com.github.jannled.lib.datastorage;

import java.util.Vector;

/**
 * This class keeps track of all Storage Keys, you can get Keys by using their path 
 * @author Jannled
 * @version 0.0.2
 */
public class Storage extends Data
{	
	public Storage()
	{
		
	}
	
	public Storage(Vector<StorageKey> keys)
	{
		this.keys = keys;
	}
	
	/**
	 * Gets the key for the given path 
	 * @param path The path, seperated by dots <b>.</b>
	 * @return The storage key, represented by that path or null, if it doesn't exist. 
	 */
	public StorageKey getKey(String path)
	{
		String[] sPath = path.split("\\.");
		StorageKey currentLayer = new StorageKey("ROOT", null, null, keys);
		
		for(int i=0; i<sPath.length; i++)
		{	
			currentLayer = findKey(currentLayer, sPath[i]);
		}
		return currentLayer;
	}
	
	/**
	 * Gets all child keys for the given path
	 * @param path The path, seperated by dots <b>.</b>
	 * @return The storage keys, represented by that path or null, if it doesn't exist. 
	 */
	public StorageKey[] getKeys(String path)
	{
		StorageKey parentKey = getKey(path);
		if(parentKey != null && parentKey.getKeys() != null)
		{
			StorageKey[] storageKeys = new StorageKey[parentKey.getKeys().size()];
			
			for(int i=0; i<parentKey.getKeys().size(); i++)
			{
				storageKeys[i] = parentKey.getKeys().get(i);
			}
			return storageKeys;
		}
		return null;
	}
	
	/**
	 * Gets the key for the given value 
	 * @param path The path, seperated by dots <b>.</b>
	 * @return The storage value, represented by that path
	 */
	public String getValue(String path)
	{
		StorageKey s = getKey(path);
		if(s != null)
			return s.getValue();
		else return null;
	}
	
	private StorageKey findKey(StorageKey currentLayer, String sPath)
	{
		StorageKey buffer = null;
		
		for(StorageKey child : currentLayer.getKeys())
		{
			if(child.getName().equals(sPath))
			{
				buffer = child;
			}
		}
		
		return buffer;
	}
}