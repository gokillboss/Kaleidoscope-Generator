// File: Kaleidoscope.java 
// Originally written by: Dr. Watts
// Modified by: Sam Ho
// Contents: This class generates an image which displays the Canvas design
// in six pie shaped segments. 3 of the images are un-flipped, the other 3
// are flipped to create the appearance of reflections.

import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.Highlighter.Highlight;

import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.security.GeneralSecurityException;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.function.DoubleUnaryOperator;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
 
public class Kaleidoscope extends JPanel
{
	private Data data;



// Function: Kaleidoscope constructor
// Arguments: Pointer to Data object containing shared data
// Purpose: This function will initialize the Kaleidoscope object

	public Kaleidoscope (Data D) 
	{ 
		data = D;
		data.kaleidoscope =this;
        repaint(); 
   	} 

// Function: paintKaleidoscope
// Arguments: The Graphics2D container to which the disgn will be painted,
//	the width and height of the Graphics2D container.
// Purpose: This function will be called to paint the design to either
//	the Kaleidoscope tab or to a BufferedImage object. 

	public void paintKaleidoscope (Graphics2D g4, int width, int height)
	{
		
		// Determine the location of the center of the image within
		// the painting and the radius of the triagle that will contain
		// the image.
		int row = 8;
		int col = row;
		double h = height/row;
		double base = h/(Math.sqrt(3)/2); // base of each triangle
		double x0 = width/2-(3*base);
		double y0 = 0;


		
		double triW = base;
		double triH = h;
	
		// Create the buffered image from the design on the canvas
		BufferedImage bufferedimage = data.image.regularBufferedImage();
		// The flip herizontaly of the image
		BufferedImage flippedimage = data.image.flippedBufferedImage ();

		// Determine the scaling factor that the image can fit to the triangle
		double scaleX = triW/bufferedimage.getWidth();
		double scaleY = triH/bufferedimage.getHeight();
		double scale = Math.max (scaleX, scaleY);

		
		//star point
		x0 = width/2 - col*base/2;
		y0 = height/2;
		
		for (int i = 0; i < row; i++)
		{
		//Draw the row of the hexagon 
		y0 =i*2*h;
		drawHex(g4, x0, y0, base, h, scale, bufferedimage, flippedimage,row);
		}
		
		x0 = x0-3*base/2;
		for (int i = 0; i < row; i++)
		{
		//draw the row of hexagon which match with the haxagon above
		y0 =i*2*h + h;
		drawHex(g4, x0, y0, base, h, scale, bufferedimage, flippedimage,row);
		}
		
 	


	
	






		
		



		// Draw a square around the image
		g4.setClip(null);
		g4.setPaint(Color.BLACK);
		g4.setStroke(new BasicStroke(3.0f));
		g4.drawRect(0, 0, width, height);


		//Draw the frame
		double radius = 0.48 * (Math.min (width, height));
		GeneralPath frame = new GeneralPath (GeneralPath.WIND_EVEN_ODD);
		frame.append (new Rectangle2D.Double (-0.1*width, 0, 1.2*width,
		height), true);
		frame.append (new Ellipse2D.Double (width/2-radius,
		height/2-radius, 2*radius, 2*radius), true);
		g4.setPaint (Color.BLACK);
		g4.fill (frame);	
	

	}

// Function: paintComponent
// Arguments: The graphics container for the Kaleidoscope JPanel in the JTabbedPanel
// Purpose: This function will be called to update the Kaleidoscope tab image

	public void paintComponent (Graphics g)
	{
		//System.out.println ("In Kaleidoscope paintComponent");
		super.paintComponent (g); 
		Graphics2D g4 = (Graphics2D) g; 
		g4.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
		RenderingHints.VALUE_ANTIALIAS_ON); 
		paintKaleidoscope (g4, getWidth(), getHeight());
	} 



// Fuction: drawing the small upsidedown triangle
// Purpose: this fuction will be call to draw a new triangle
	// public GeneralPath drawTri(double x0, double y0, double base, double h)
	// {
	// 	GeneralPath tri = new GeneralPath();
	// 	tri.moveTo(x0, y0);
	// 	tri.lineTo(x0-base/2,y0+h);
	// 	tri.lineTo(x0-base,y0);
	// 	tri.closePath();
	// 	return tri;
	// }



	
// Function: drawing the small upside triangle
// Purpose: this fuction will be call to draw a new triangle
	public GeneralPath drawTriUp(double x0, double y0, double base, double h)
	{
		GeneralPath tri = new GeneralPath();
		tri.moveTo(x0, y0);
		tri.lineTo(x0-base/2,y0+h);
		tri.lineTo(x0+base/2,y0+h);
		tri.closePath();
		return tri;
	}



	// Function: drawing  a triangle then rotate it at the top vertex
	//	         to make a row of hexagon every 3 base unint
	// Purpose: this function can be call to draw a row of hexagon
	public void drawHex(Graphics2D g4, double x0, double y0, double base, double h, 
	double scale, BufferedImage bufferedimage, BufferedImage flippedimage, int row)
	{

		for(int j =0; j < row; j++){	 // 3 is the distant of each hexagon
			AffineTransform txs = new AffineTransform();
			GeneralPath triUp = new GeneralPath();
			triUp = drawTriUp(x0, y0, base, h);  // draw a triangle
			txs.rotate(Math.PI/3, x0,y0);
	
			for (int i = 0; i < 6; i++){	
			g4.setClip(triUp);
			AffineTransform txi = new AffineTransform();
			txi = new AffineTransform();
			txi.rotate(i*Math.PI/3, x0  ,y0);
			txi.translate(x0 - base/2, y0 );
			txi.scale(scale, scale);
			if (i % 2 == 0)
				 g4.drawImage (flippedimage, txi, null);
			 else
				 g4.drawImage (bufferedimage, txi, null);
			triUp.transform(txs);
			}
			x0 += 3*base;
		}
	
	}

} 
