package com.github.jannled.lib.archive;

import java.io.InputStream;
import java.nio.file.Path;

public interface Archive
{
	/**
	 * Add a file to the current archive.
	 * @param f The file to copy in the archive.
	 * @param pathInArchive The path of the file in the archive.
	 */
	public abstract boolean addFile(Path file, Path pathInArchive, int storeOptions, boolean replace);
	
	public abstract InputStream getFile(String name);
}