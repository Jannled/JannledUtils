package com.github.jannled.window.filedrop;

import java.io.File;
import java.util.List;

public interface FiledropEvent
{
	/**
	 * Called when a file is dropped to the registered window component. 
	 * @param f A list of all files dragged onto the window component. 
	 */
	public abstract void fileDropped(List<File> f);
}
