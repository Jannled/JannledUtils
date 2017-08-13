package com.github.jannled.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class DropdownButton extends JPanel implements MouseListener
{
	private static final long serialVersionUID = 3240565806751089781L;
	
	private static BufferedImage image = loadImage();
	
	private Color defaultC;
	private Color hoveredC;
	private Color clickedC;
	
	private int width;
	private int height;
	boolean extended;

	public DropdownButton()
	{
		super();
		width = 16;
		height = 16;
		defaultC = UIManager.getColor("Panel.background");
		hoveredC = new Color(0.8F, 0.8F, 1.0F);
		clickedC = new Color(0.6F, 0.6F, 1.0F);
		
		setup();
	}
	
	public DropdownButton(int width, int height)
	{
		super();
		this.width = width;
		this.height = height;
		defaultC = UIManager.getColor("Panel.background");
		hoveredC = new Color(0.8F, 0.8F, 1.0F);
		clickedC = new Color(0.6F, 0.6F, 1.0F);
		
		setup();
	}
	
	public DropdownButton(int width, int height, Color defaultColor, Color hoveredColor, Color clickedColor)
	{
		super();
		this.width = width;
		this.height = height;
		this.defaultC = defaultColor;
		this.hoveredC = hoveredColor;
		this.clickedC = clickedColor;
		
		setup();
	}
	
	private void setup()
	{
		setLayout(null);
		setSize(width, height);
		this.addMouseListener(this);
		if(image!=null && getGraphics()!=null)
		{
			getGraphics().drawImage(image, 0, 0, width, height, 0, 0, image.getWidth(), image.getHeight(), null);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		setBackground(hoveredC);
		extended = !extended;
		getParent().repaint();
		repaint();
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		setBackground(clickedC);
		repaint();
	}
	
	@Override
	public void mouseExited(MouseEvent e)
	{
		setBackground(defaultC);
		repaint();
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		setBackground(hoveredC);
		repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		repaint();
	}
	
	private static final BufferedImage loadImage()
	{
		BufferedImage img = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
		try
		{
			img = ImageIO.read(DropdownButton.class.getResourceAsStream("/assets/triangle.png"));
		} catch (IOException | NullPointerException e)
		{
			e.printStackTrace();
			return null;
		}
		return img;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(extended)
		{
			AffineTransform tx = new AffineTransform();
			tx.rotate(Math.toRadians(90), image.getWidth()/2, image.getHeight()/2);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			g.drawImage(op.filter(image, null), 0, 0, width, height, null);
		}
		else
		{
			g.drawImage(image, 0, 0, width, height, null);
		}
	}
	
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(width, height);
	}
	
	/**
	 * Check if the button is toggled
	 * @return True, when the dropdown is open, false if not
	 */
	public boolean getStatus()
	{
		return extended;
	}
}
