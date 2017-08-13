package com.github.jannled.window;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

/**
 * This provides a textfield, which supports a hint, that dissaperars as the user clicks in the textfield. 
 * It extends the JTextField.
 * @author Jannled
 * @version 0.0.1
 */
public class Textfield extends JTextField
{
	private static final long serialVersionUID = 3205734512420934689L;

	Color colorUnactive = new Color(100, 100, 100);
	Color colorActive = new Color(0, 0, 0);
	
	/**
	 * The textfield without a hint
	 */
	public Textfield()
	{
		
	}
	
	/**
	 * The textfield with a hint
	 * @param hint A textfield which displays a short description what input is expected
	 */
	public Textfield(String hint)
	{
		setForeground(colorUnactive);
		setText(hint);
		watchSearchHint(hint);
	}
	
	public void watchSearchHint(String hint)
	{
		setToolTipText(hint);
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(getText().equals(hint))
				{
					setForeground(colorActive);
					setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(getText().equals(""))
				{
					setForeground(colorUnactive);
					setText(hint);
				}
			}
		});
	}
	
	public void setColor(Color colorUnactive, Color colorActive)
	{
		this.colorUnactive = colorUnactive;
		this.colorActive = colorActive;
	}
}
