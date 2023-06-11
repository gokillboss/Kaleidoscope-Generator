// File: Palette.java
// Originally written by: Dr. Watts
// Modified by:
// Contents: a Palette Panel class for a small GUI application.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.net.URL;
import java.awt.Toolkit;

public class Palette extends JPanel
{
	Data data;

	public Palette (Data D)
	{
		data = D;
		setBorder (BorderFactory.createRaisedBevelBorder());
		setLayout (new GridLayout(1,2));
		ElementPanel ep = new ElementPanel (data);
		add (ep);

		JPanel right = new JPanel ();
		right.setLayout (new GridLayout(4,1));
		SelectedPanel sp = new SelectedPanel (data);
		right.add (sp);
		StrokePanel scp = new StrokePanel (data);
		right.add (scp);
		FillPanel fcp = new FillPanel (data);
		right.add (fcp);
		Buttons bp = new Buttons (data);
		right.add (bp);
		add (right);
	}

}
