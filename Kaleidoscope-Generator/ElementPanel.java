// File: ElementPanel.java
// Originally written by: Dr. Watts
// Modified by:
// Contents:

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class ElementPanel extends JPanel
{
	Data data;

	public ElementPanel (Data D)
	{
		data = D;
		setLayout (new GridLayout(6,2));
		OneElement rectangle = new OneElement (data.newRectangle (0, 0, 60, 60, Color.BLACK, Color.WHITE));
		add (rectangle);
		OneElement heart = new OneElement (data.newHeart (0, 0, 60, 60, Color.BLACK, Color.WHITE));
		add (heart);
		OneElement lightning = new OneElement (data.lightning(0, 0, 80, 80, Color.BLACK, Color.WHITE));
		add (lightning);
		OneElement Triangle = new OneElement (data.Triangle(20, 20, 60, 60, Color.BLACK, Color.WHITE));
		add (Triangle);
		OneElement Moon = new OneElement (data.Moon(20, 20, 60, 60, Color.BLACK, Color.WHITE));
		add (Moon);
		OneElement Star = new OneElement (data.Star(20, 20, 60, 60, Color.BLACK, Color.WHITE));
		add (Star);
		OneElement EQTriangle = new OneElement (data.EQTriangle(20, 20, 60, 60, Color.BLACK, Color.WHITE));
		add (EQTriangle);

		for (int i = 0; i < 5; i++)
		{
			OneElement another = new OneElement (data.Star (0, 0, 60, 60, Color.BLACK, Color.WHITE));
			add (another);
		}
	}

	class OneElement extends JPanel implements MouseListener
	{
		private DesignElement element;

		public OneElement (DesignElement E)
		{
			setBorder (BorderFactory.createRaisedBevelBorder());
			setPreferredSize(new Dimension(80, 94));
			element = E;
			element.moveTo (39, 47);
			addMouseListener(this);
			repaint();
		}

		public void paintComponent (Graphics g)
		{
			super.paintComponent (g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			element.paintElement (g2);
		}
	
		public void mousePressed (MouseEvent e)
		{
			if (e.getButton() == e.BUTTON1) // Left mouse button
			{
				data.onDeckElement = element.clone();
				if (data.onDeckElement != null)
				{
					data.onDeckElement.scale (1.5, 1.5);
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
