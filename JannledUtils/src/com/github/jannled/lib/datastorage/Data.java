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
	 * Sets the child keys of this key
	 * @param storageKeys A Vector containing all storageKeys
	 */
	public void setStorageKeys(Vector<StorageKey> storageKeys)
	{
		this.keys = storageKeys;
	}
	

	/**
	 * Sets the child keys of this key
	 * @param storageKeys A Array containing all storageKeys
	 */
	public void setStorageKeys(StorageKey[] storageKeys)
	{
		for(StorageKey s : storageKeys)
		{
			keys.add(s);
		}
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
		return keys.toArray(new StorageKey[keys.size()]);
	}
}
