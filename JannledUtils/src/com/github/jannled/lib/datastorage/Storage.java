package com.github.jannled.lib.datastorage;

/**
 * This class keeps track of all Storage Keys, you can get Keys by using their path 
 * @author Jannled
 * @version 0.0.2
 */
public class Storage extends Data
{	
	/**
	 * Gets the key for the given path 
	 * @param path The path, seperated by dots <b>.</b>
	 * @return The storage key, represented by that path
	 */
	public StorageKey getKey(String path)
	{
		return null;
	}
	
	/**
	 * Gets the key for the given value 
	 * @param path The path, seperated by dots <b>.</b>
	 * @return The storage value, represented by that path
	 */
	public String getValue(String path)
	{
		return getKey(path).getValue();
	}
}
