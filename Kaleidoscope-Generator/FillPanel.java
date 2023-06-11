// File: FillPanel.java 
// Originally written by: Dr. Watts
// Modified by:
// Contents:

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class FillPanel extends JPanel
{
	Color [] colors = {Color.RED, Color.GREEN, Color.BLUE,
					   Color.YELLOW, new Color (93, 66, 245), new Color (66, 245, 236),
					   new Color (129, 245, 66), new Color (130, 0, 0), Color.BLACK};
	Data data;

	public FillPanel (Data D)
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

		public OneColor (Color C)
		{
			color = C;
			setBackground (color);
			setBorder (BorderFactory.createRaisedBevelBorder());
			addMouseListener(this);
			repaint();
		}
	
		public void mousePressed (MouseEvent e)
		{
			if (e.getButton() == e.BUTTON1) // Left mouse button
			{
				data.currentFill = color;
				if (data.selectedElement != null)
				{
					data.selectedElement.setFill (color);
					data.canvas.repaint();
				}
				if (data.onDeckElement != null)
				{
					data.onDeckElement.setFill (color);
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
