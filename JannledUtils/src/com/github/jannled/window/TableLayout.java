package com.github.jannled.window;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;

/**
 * A more flexible version of the GridLayout manager. The cells adjust it's size to the preferedSize() of the subcomponents.
 * <a href="http://www.javaworld.com/article/2077486/core-java/java-tip-121--flex-your-grid-layout.html">Source</a>
 * @author Bogdan Dorohonceanu
 * @author Jannled
 * @version 0.0.1
 */
public class TableLayout extends GridLayout
{
	private static final long serialVersionUID = -6697400482171265522L;

	public TableLayout(int rows, int colums)
	{
		super(rows, colums);
	}
	
	public TableLayout(int rows, int colums, int hGap, int vGap)
	{
		super(rows, colums, hGap, vGap);
	}
	
	@Override
	public Dimension preferredLayoutSize(Container parent) 
	{
		synchronized (parent.getTreeLock()) 
		{
			Insets insets = parent.getInsets();
			int ncomponents = parent.getComponentCount();
			int nrows = getRows();
			int ncols = getColumns();
			if (nrows > 0) 
			{
				ncols = (ncomponents + nrows - 1) / nrows;
			}
			else 
			{
			nrows = (ncomponents + ncols - 1) / ncols;
			}
			
			int[] w = new int[ncols];
			int[] h = new int[nrows];
			for (int i = 0; i < ncomponents; i ++) 
			{
				int r = i / ncols;
				int c = i % ncols;
				Component comp = parent.getComponent(i);
				Dimension d = comp.getPreferredSize();
				if (w[c] < d.width) 
				{
					w[c] = d.width;
				}
				if (h[r] < d.height) 
				{
					h[r] = d.height;
				}
			}
			
			int nw = 0;
			for (int j = 0; j < ncols; j ++) 
			{
				nw += w[j];
			}
			int nh = 0;
			for (int i = 0; i < nrows; i ++) 
			{
				nh += h[i];
			}
			return new Dimension(insets.left + insets.right +
						 nw + (ncols-1) * getHgap(),
						 insets.top + insets.bottom +
						 nh + (nrows-1)*getVgap());
		}
	}
	
	@Override
	public void layoutContainer(Container parent) 
	{
		synchronized (parent.getTreeLock()) 
		{
			Insets insets = parent.getInsets();
			int ncomponents = parent.getComponentCount();
			int nrows = getRows();
			int ncols = getColumns();
			if (ncomponents == 0) 
			{
				return;
			}
			if (nrows > 0) 
			{
				ncols = (ncomponents + nrows - 1) / nrows;
			}
			else 
			{
				nrows = (ncomponents + ncols - 1) / ncols;
			}
			int hgap = getHgap();
			int vgap = getVgap();
			
			 // scaling factors
			Dimension pd = preferredLayoutSize(parent);
			double sw = (1.0 * parent.getWidth()) / pd.width;
			double sh = (1.0 * parent.getHeight()) / pd.height;
			
			// scale
			int[] w = new int[ncols];
			int[] h = new int[nrows];
			for (int i = 0; i < ncomponents; i ++) 
			{
				int r = i / ncols;
				int c = i % ncols;
				Component comp = parent.getComponent(i);
				Dimension d = comp.getPreferredSize();
				d.width = (int) (sw * d.width);
				d.height = (int) (sh * d.height);
				if (w[c] < d.width) 
				{
					w[c] = d.width;
				}
				if (h[r] < d.height) 
				{
					h[r] = d.height;
				}
			}
			
			for (int c = 0, x = insets.left; c < ncols; c ++) 
			{
				for (int r = 0, y = insets.top; r < nrows; r ++) 
				{
					int i = r * ncols + c;
					if (i < ncomponents) 
					{
						parent.getComponent(i).setBounds(x, y, w[c], h[r]);
					}
					y += h[r] + vgap;
				}
				x += w[c] + hgap;
			}
		}
	}
}
