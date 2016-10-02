package com.github.jannled.lib.datastorage;

import java.util.Vector;

public class StorageKey
{
	private String name;
	private String value;
	private StorageKey parentKey;
	private Vector<StorageKey> keys = new Vector<StorageKey>();
	
	public StorageKey(String name, String value, StorageKey parentKey)
	{
		this.name = name;
		this.value = value;
		this.parentKey = parentKey;
	}
	
	public void addStorageKey(StorageKey storageKey)
	{
		keys.add(storageKey);
	}

	public StorageKey[] getStorageKeys()
	{
		return (StorageKey[]) keys.toArray();
	}
	
	public String getName()
	{
		return name;
	}
	
	public StorageKey getParentKey()
	{
		return parentKey;
	}
	
	public String getValue()
	{
		return value;
	}
}
