// File: SelectedPanel.java
// Originally written by: Dr. Watts
// Modified by:
// Contents: a JPanel to display the "On Deck" design element

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class SelectedPanel extends JPanel
{
	Data data;

	public SelectedPanel (Data D)
	{
		data = D;
		data.selected = this;
		setBorder (BorderFactory.createRaisedBevelBorder());
		setBackground (Color.WHITE);
		repaint();
	}

	public void paintComponent (Graphics g)
	{
		super.paintComponent (g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		data.onDeckElement.moveTo (getWidth()/2, getHeight()/2);
		data.onDeckElement.paintElement (g2);
	}
}
