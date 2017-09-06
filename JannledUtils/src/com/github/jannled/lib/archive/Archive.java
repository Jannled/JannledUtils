package com.github.jannled.lib.archive;

import java.io.InputStream;
import java.nio.file.Path;

public interface Archive
{
	/**
	 * Add a file to the current archive.
	 * @param file The file to copy in the archive.
	 * @param pathInArchive The path of the file in the archive.
	 * @param storeOptions Options for storing the file.
	 * @param replace If the file should be overwritten.
	 * @return If the file was added successfully.
	 */
	public abstract boolean addFile(Path file, Path pathInArchive, int storeOptions, boolean replace);
	
	/**
	 * Get a file by the specified path name.
	 * @param name A file path, separated with <b>slashes</b>. Example: <code>this/is/afile.ext</code>
	 * @return The input stream for the specified path if existant, or null if not.
	 */
	public abstract InputStream getFile(String name);
}