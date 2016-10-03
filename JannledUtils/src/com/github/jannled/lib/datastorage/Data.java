package com.github.jannled.lib.datastorage;

import java.util.Vector;

/**
 * Basic class for the Storage and StorageKey
 * @author Jannled
 * @version 0.0.2
 */
public abstract class Data
{
	Vector<StorageKey> keys = new Vector<StorageKey>();
	
	/**
	 * Adds child key to the current storage Key
	 * @param storageKey The storage Key to add
	 */
	public void addStorageKey(StorageKey storageKey)
	{
		keys.add(storageKey);
	}
	
	/**
	 * Gets all child keys for this key, the returned keys can also have child keys
	 * @return All child keys that this class manages
	 */
	public Vector<StorageKey> getKeys()
	{
		return keys;
	}
	
	/**
	 * Gets all child keys for this key, the returned keys can also have child keys
	 * @return All child keys that this class manages
	 */
	public StorageKey[] getKeysArray()
	{
		return (StorageKey[]) keys.toArray();
	}
}
