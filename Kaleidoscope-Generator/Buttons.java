// File: Buttons.java 
// Originally written by: Dr. Watts
// Modified by:
// Contents:

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Buttons extends JPanel implements ActionListener
{
	private JButton clearButton;
	private JButton openButton;
	private JButton saveButton;
	Data data;

	public Buttons (Data D)
	{
		data = D;
		setBorder (BorderFactory.createRaisedBevelBorder());
		setLayout (new GridLayout(3,1));
		clearButton = new JButton ("Clear Design");
		add (clearButton);
		clearButton.addActionListener (this);
        	openButton = new JButton("Open Design");
        	add (openButton);
        	openButton.addActionListener(this);
        	saveButton = new JButton("Save Design");
        	add (saveButton);
        	saveButton.addActionListener(this);
	}

	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() == clearButton)
		{
			data.elements.clear();
			data.canvas.repaint();
		}
		else if (e.getSource() == openButton)
		{
			String filename = (String)JOptionPane.showInputDialog
				( this, "Enter file name prefix\n(.txt suffix will be added)", "Design Open Dialog",
                		JOptionPane.PLAIN_MESSAGE, null, null, "");
			if (filename != null)
			{
				FileIO fileio = new FileIO (data);
				fileio.readFile (filename + ".txt");
				data.canvas.repaint();
			}
		}
		else if (e.getSource() == saveButton)
		{
			String filename = (String)JOptionPane.showInputDialog
				( this, "Enter file name prefix\n(suffixes will be added)", "Design Save Dialog",
                		JOptionPane.PLAIN_MESSAGE, null, null, "");
			if (filename != null)
			{
				FileIO fileio = new FileIO (data);
				fileio.writeFile (filename + ".txt");
				fileio.saveAsImage (filename);
			}
		}
	}
}
