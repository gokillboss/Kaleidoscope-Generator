// File: Project2.java 
// Originally written by: Dr. Watts
// Modified by:
// Contents:

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Project2
{
	public static void main (String[] args) 
	{ 
		Data data;
		if (args.length > 0)
			data = new Data (args[0]);
		else
			data = new Data ();
		JFrame frame = new JFrame ("Project 2"); 
		frame.setSize (1000,700); 
		frame.setResizable (false); 
		frame.setLocation (200, 200); 
		frame.setBackground (Color.BLACK);
		Container pane = frame.getContentPane();
		Palette palette = new Palette (data);
		JTabbedPane tabpane = new JTabbedPane ();
		Canvas canvas = new Canvas (data); 
		tabpane.add ("Canvas", canvas);
		Image pattern = new Image (data); 
		tabpane.add ("Image", pattern);
		Design6 design6 = new Design6 (data); 
		tabpane.add ("Design6", design6);
		Design8 design8 = new Design8 (data); 
		tabpane.add ("Design8", design8);
		Kaleidoscope kaleidoscope = new Kaleidoscope (data); 
		tabpane.add ("Kaleidoscope", kaleidoscope);
		pane.setLayout (new BorderLayout());
		pane.add (palette, BorderLayout.WEST);
		pane.add (tabpane, BorderLayout.CENTER);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); 
		frame.setVisible (true); 
	} 
} 
