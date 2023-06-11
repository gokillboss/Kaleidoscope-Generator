// File: FileIO.java 
// Originally written by: Dr. Watts
// Modified by:
// Contents:

import java.awt.*;
import java.lang.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList; 
import java.awt.image.*;
import javax.imageio.ImageIO;

public class FileIO 
{
	Data data;

	public FileIO (Data D)
	{
		data = D;
	}

	public void readFile (String fileName)
   	{

		// Open input file
		BufferedReader input = null;
		File textFile = new File(fileName);
	   	try 
		{
			input = new BufferedReader( new FileReader(textFile) );      	
	     	}
		catch (FileNotFoundException ex) 
		{
			System.out.println ("File: " + textFile + " not found");
			return;
      			//ex.printStackTrace();
    		}
		// Read input file
		data.elements.clear();
		try 
    		{
			int count = 0;
			String line = null;
			line = input.readLine ();
                	String [] parts = line.split (" ");
                	try
                	{
                        	count = Integer.parseInt(parts[0]);
                	}
                	catch (NumberFormatException e)
                	{
                        	System.out.println ("File input error - invalid numeric value");;
                	}
			//System.out.println ("Count = " + count);
      			for (int i = 0; i < count; i++)
			{
				line = input.readLine ();
				DesignElement element = new DesignElement();
				element.fromString (line);
				data.elements.add (element);
			}	
			input.close();
		}
    		catch (IOException ex)
   		{
     		 	ex.printStackTrace();
    		}
		//System.out.println ("Finished reading file");
		//for (int i = 0; i < data.elements.size(); i++)
		//	System.out.println (data.elements.get(i));
   	}

   	public void writeFile (String fileName)
   	{
		BufferedWriter output = null;
		File textFile = new File(fileName);
   		try 
    		{
			output = new BufferedWriter( new FileWriter(textFile) );      	
     		}
		catch (IOException ex) 
		{
      			ex.printStackTrace();
    		}
		// Write output file
		try 
    		{
			output.write (String.valueOf(data.elements.size()));
			output.newLine();
      			for (int i = 0; i < data.elements.size(); i++)
      			{
				output.write (data.elements.get(i).toString());
				output.newLine();
			}
     			output.close();
		}
    		catch (IOException ex)
   		{
     		 	ex.printStackTrace();
    		}
   	}

	public void saveAsImage (String filename)
	{
		// Save as jpeg, bitmap, and png
		try 
    		{
			//System.out.println ("In saveAsImage");

			// Create and save image files of Canvas
			BufferedImage bufferedimage = data.image.regularBufferedImage ();
			ImageIO.write(bufferedimage, "jpeg", new File(filename + ".jpg"));

			// Create and save image files of Kaleidoscopeimage
			BufferedImage Design6image = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
			BufferedImage Design8image = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
			BufferedImage Kaleidoscopeimage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);

			Graphics2D g2 = Design6image.createGraphics();
			Graphics2D g3 = Design8image.createGraphics();
			Graphics2D g4 = Kaleidoscopeimage.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
			RenderingHints.VALUE_ANTIALIAS_ON);
			g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
			RenderingHints.VALUE_ANTIALIAS_ON);
			g4.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON); 
				g2.setPaint (Color.WHITE);
				g3.setPaint (Color.WHITE);
        		g4.setPaint (Color.WHITE);
			g2.fillRect (0, 0, 600, 600);
			g3.fillRect (0, 0, 600, 600);
			g4.fillRect (0, 0, 600, 600);
			data.design6.paintDesign6 (g2, 600 ,600);
			data.design8.paintDesign8 (g3, 600 ,600);
			data.kaleidoscope.paintKaleidoscope (g4, 600 ,600);
			g2.dispose();
			g3.dispose();
			g4.dispose();
			ImageIO.write(Design6image, "jpeg", new File(filename + "6.jpg"));
			ImageIO.write(Design8image, "jpeg", new File(filename + "8.jpg"));
			ImageIO.write(Kaleidoscopeimage, "jpeg", new File(filename + "K.jpg"));
		}
    		catch (IOException ex)
   		{
     		 	ex.printStackTrace();
    		}
	}

	
}
