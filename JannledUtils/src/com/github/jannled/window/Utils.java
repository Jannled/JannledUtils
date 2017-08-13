package com.github.jannled.window;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.github.jannled.lib.Print;

/**
 * Util class for everything that has to do with windows in Java.
 * @author Jannled
 * @version 0.0.1
 */
public class Utils
{
	/**
	 * Requests the layout for the window from the current operating system
	 */
	public static void setOSLook()
	{
		try {
			Print.m("Setting look and feel to OS design.");
			JFrame.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			Print.e("Class not found!");
			e.printStackTrace();
		} catch (InstantiationException e) {
			Print.e("Failed to Instantiate!");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Print.e("Illegal Acces!");
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			Print.e("Unsuported Look and feel!");
			e.printStackTrace();
		}
	}
}
