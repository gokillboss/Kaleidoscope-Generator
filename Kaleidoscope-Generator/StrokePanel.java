// File: StrokPanel.java 
// Originally written by: Dr. Watts
// Modified by:
// Contents:

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.geom.GeneralPath;

public class StrokePanel extends JPanel
{
	Color [] colors = {Color.RED, Color.GREEN ,Color.BLUE,
			    Color.YELLOW, new Color(191,245,66), new Color(66,215,245),
				Color.ORANGE, new Color(245, 66, 200), Color.BLACK};
	Data data;

	public StrokePanel (Data D)
	{
		data = D;
		setLayout (new GridLayout(3,3));
		for (int i = 0; i < 9; i++)
		{
			OneColor one = new OneColor (colors[i]);
			add (one);
		}
	}

	class OneColor extends JPanel implements MouseListener
	{
		Color color;
		GeneralPath stroke;

		public OneColor (Color C)
		{
			color = C;
			setBorder (BorderFactory.createRaisedBevelBorder());
			addMouseListener(this);
			repaint();
		}
	
		public void paintComponent (Graphics g) 
		{ 
			super.paintComponent (g); 
			Graphics2D g2 = (Graphics2D) g; 
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
			RenderingHints.VALUE_ANTIALIAS_ON); 
        	g2.setPaint(color);
			Dimension dimension = new Dimension ();
			getSize(dimension);
			g2.drawLine (0, 0, dimension.width, dimension.height);
			stroke = new GeneralPath ();
			stroke.moveTo (0, 0);
			stroke.lineTo (0, 10);
			stroke.lineTo (dimension.width, dimension.height);
			stroke.lineTo (dimension.width, dimension.height-10);
			stroke.closePath ();
			g2.fill (stroke);
			
		} 

		public void mousePressed (MouseEvent e)
		{
			if (e.getButton() == e.BUTTON1) // Left mouse button
			{
				data.currentFill = color;
				if (data.selectedElement != null)
				{
					data.selectedElement.setStroke (color);
					data.canvas.repaint();
				}
				if (data.onDeckElement != null)
				{
					data.onDeckElement.setStroke (color);
					data.selected.repaint();
				}
			}
		}
		public void mouseReleased (MouseEvent e) { }
		public void mouseEntered (MouseEvent e) { }
		public void mouseExited (MouseEvent e) { }
		public void mouseClicked (MouseEvent e) { }
	}
}
