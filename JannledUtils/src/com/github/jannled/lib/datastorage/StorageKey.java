package com.github.jannled.lib.datastorage;

import java.util.Vector;

/**
 * A single key, manages its own child keys
 * @author Jannled
 * @version 0.0.2
 */
public class StorageKey extends Data
{
	private String name;
	private String value;
	private StorageKey parentKey;
	private Data parentStorage;
	
	/**
	 * Creates a new Key
	 * @param name The name of the key
	 * @param value The value of the key, or null if it is a parent key (has child keys keys)
	 * @param parentKey The parent key
	 */
	public StorageKey(String name, String value, StorageKey parentKey)
	{
		this.name = name;
		this.value = value;
		this.parentKey = parentKey;
	}
	
	/**
	 * Creates a new Key
	 * @param name The name of the key
	 * @param parentKey The parent key
	 */
	public StorageKey(String name, StorageKey parentKey)
	{
		this.name = name;
		this.value = null;
		this.parentKey = parentKey;
	}
	
	/**
	 * Creates a new Key
	 * @param name The name of the key
	 * @param value The value of the key, or null if it is a parent key (has child keys keys)
	 * @param parent The parent storage object, which keeps track of managing all the keys
	 */
	public StorageKey(String name, String value, Storage parent)
	{
		this.name = name;
		this.value = value;
		this.parentStorage = parent;
	}
	
	/**
	 * Creates a new Key with preset child keys
	 * @param name The name of the key
	 * @param value The value of the key, or null if it is a parent key (has child keys keys)
	 * @param parent The parent storage object, which keeps track of managing all the keys
	 * @param keys Predefined keys
	 */
	public StorageKey(String name, String value, Storage parent, Vector<StorageKey> keys)
	{
		this.name = name;
		this.value = value;
		this.parentStorage = parent;
		this.keys = keys;
	}
	
	/**
	 * Gets the name of this
	 * @return The name of this key
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Gets the value of this key
	 * @return The value for this key, or null if it is a parent key (has child keys keys)
	 */
	public String getValue()
	{
		return value;
	}
	
	/**
	 * Gets the parent key
	 * @return It can either return a StorageKey or a Storage. You can check this with instanceof
	 */
	public Data getParent()
	{
		if(parentKey != null && parentStorage == null)
			return parentKey;
		else
			return parentStorage;
	}
	
	/**
	 * Checks if the key has a value, or if it is a parent key (has child keys keys)
	 * @return True if the key has a value
	 */
	public boolean isValue()
	{
		if(value!=null)
			return true;
		else
			return false;
	}
	
	@Override
	public String toString()
	{
		String output = "";
		output += name;
		if(value != null)
			output += ": " + value;
		
		return output;
	}
}
