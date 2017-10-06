package com.github.jannled.lib.archive;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;

public class Zipfile implements Archive
{
	protected HashMap<String, String> env = new HashMap<String, String>();
	protected URI path;
	protected FileSystem zipfs;
	
	
	public static final int STORE = 0;
	
	public static final int DEFLATE = 1;
	
	public Zipfile(File zipfile, boolean create)
	{
		try
		{
			if(zipfs!=null)
				zipfs.close();
			
			final Path path = zipfile.toPath();
			final URI uri = URI.create("jar:file:" + path.toUri().getPath());
			//DEBUG Print.d("URI of opened Zip file: " + uri.toString());
			
			env.put("create", create ? "true" : "false");
			
			zipfs = FileSystems.newFileSystem(uri, env);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Copy a file into the archive
	 * @param source The path of the file to copy to the archive. 
	 * @param pathInArchive The path the file should have inside the archive.
	 * @return If the file was moved successfully.
	 */
	@Override
	public boolean addFile(Path source, Path pathInArchive, int storeOptions, boolean replace)
	{
		try
		{
			Files.copy(source, pathInArchive, StandardCopyOption.REPLACE_EXISTING);
			return true;
		} catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public InputStream getFile(String name)
	{
		try
		{
			return Files.newInputStream(zipfs.getPath(name));
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (InvalidPathException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public FileSystem getFilesystem()
	{
		return zipfs;
	}

	@Override
	public String[] getFiles(String path)
	{
		if(path.equals(""))
			path = "/";
		
		ArrayList<String> names = new ArrayList<String>();
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(getFilesystem().getPath(path)))
		{
			for(Path p : directoryStream)
			{
				names.add(p.getFileName().toString());
			}
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		String[] s = new String[names.size()];
		return names.toArray(s);
	}
}
