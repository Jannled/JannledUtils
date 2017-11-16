package com.github.jannled.window.filedrop;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.TransferHandler;

/**
 * Use this on any component by <code> .setTransferHandler(FileDropHandler).
 * Any registered FiledropEvents will be notified.
 * @author Jannled
 * @author RustyX
 * Inspired by https://stackoverflow.com/questions/811248/how-can-i-use-drag-and-drop-in-swing-to-get-file-path
 */
public class FiledropHandler extends TransferHandler
{ 
	private static final long serialVersionUID = 7211603178079883080L;
	private LinkedList<FiledropEvent> listener = new LinkedList<FiledropEvent>();

	@Override
    public boolean canImport(TransferHandler.TransferSupport support) {
        for (DataFlavor flavor : support.getDataFlavors()) {
            if (flavor.isFlavorJavaFileListType()) {
                return true;
            }
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean importData(TransferHandler.TransferSupport support) {
        if (!this.canImport(support))
            return false;

        List<File> files;
        try {
            files = (List<File>) support.getTransferable()
                    .getTransferData(DataFlavor.javaFileListFlavor);
        } catch (UnsupportedFlavorException | IOException ex) {
            // should never happen (or JDK is buggy)
            return false;
        }

        for (FiledropEvent fe : listener) 
        {
            fe.fileDropped(files);
        }
        return true;
    }
    
    public void registerListener(FiledropEvent e)
    {
    	listener.add(e);
    }
}
