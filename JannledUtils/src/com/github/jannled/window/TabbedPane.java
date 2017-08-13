package com.github.jannled.window;

import java.awt.Component;

import javax.swing.JTabbedPane;

public class TabbedPane extends JTabbedPane
{
	private static final long serialVersionUID = 8561356341115599970L;
	Component[] components = new Component[0];
	
	public TabbedPane()
	{
		super();
	}
	
	public TabbedPane(int arg)
	{
		super(arg);
	}
	
	public TabbedPane(int arg1, int arg2)
	{
		super(arg1, arg2);
	}

	@Override
	public Component add(Component comp)
	{
		super.add(comp);
		components = incrementArray(components, 1);
		return comp;
	}
	
	public Component getActivePane()
	{
		return getSelectedComponent();
	}
	
	private Component[] incrementArray(Component[] array, int amount)
	{
		Component[] buffer = new Component[array.length+amount];
		for(int i=0; i<array.length; i++)
		{
			if(i<array.length)
			{
				buffer[i] = array[i];
			}
		}
		return buffer;
	}
}
