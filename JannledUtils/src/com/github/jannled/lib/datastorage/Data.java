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
	 * Removes the child key from this key
	 * @param key The pointer to the key object you want to remove, must be the same stored in this key as a child key
	 * @return True if the child key could get removed, false if not
	 */
	public boolean removeKey(StorageKey key)
	{
		if(keys.contains(key))
		{
			keys.remove(key);
			return true;
		}
		else
			return false;
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
