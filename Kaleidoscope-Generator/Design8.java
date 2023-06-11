// File: Design8.java 
// Originally written by: Dr. Watts
// Modified by: Sam Ho
// Contents: This class generates an image which displays the Canvas design
// in six pie shaped segments. 3 of the images are un-flipped, the other 3
// are flipped to create the appearance of reflections.

import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
 
public class Design8 extends JPanel
{
	private Data data;
	// Ratios used to create one sixth of the circle
	double r1 = 0.5;
	double r2 = 0.866;
	double r3 = 0.20;
	double r4 = 1.045;

// Function: Design8 constructor
// Arguments: Pointer to Data object containing shared data
// Purpose: This function will initialize the Design8 object

	public Design8 (Data D) 
	{ 
		data = D;
		data.design8 = this;
        	repaint(); 
   	} 

// Function: paintDesign8
// Arguments: The Graphics2D container to which the disgn will be painted,
//	the width and height of the Graphics2D container.
// Purpose: This function will be called to paint the design to either
//	the Design8 tab or to a BufferedImage object. 

	public void paintDesign8 (Graphics2D g3, int width, int height)
	{
		//System.out.println ("In Design8 paintDesign8");
		// Determine the location of the center of the image within
		// the painting and the radius of the circle that will contain
		// the image.
		double centerX = width/2.0;
		double centerY = height/2.0;
		double radius = 0.48 * (Math.min (width, height));

		// Create a slice equal to 1/6 of the circle with vertex
		// at center of image 
		GeneralPath slice = new GeneralPath ();
		slice.moveTo (centerX, centerY);
		slice.lineTo (centerX-r1*radius, centerY-r2*radius);
		slice.curveTo (centerX-r3*radius, centerY-r4*radius,
			       centerX+r3*radius, centerY-r4*radius,
			       centerX+r1*radius, centerY-r2*radius);
		slice.closePath();
		double sliceWidth = radius;
		double sliceHeight = radius;
    		AffineTransform txs = new AffineTransform();
		txs.rotate (Math.PI/4, centerX, centerY);

		// Create buffered images from the design on the canvas
		// One image will be as it appears on the canvas
		BufferedImage bufferedimage = data.image.regularBufferedImage ();
		// The other image will be flipped horizontally
		BufferedImage flippedimage = data.image.flippedBufferedImage ();
		// Determine the scaling factors required to fit the image into
		// the slice
		double scaleX = sliceWidth/bufferedimage.getWidth();
		double scaleY = sliceHeight/bufferedimage.getHeight();
		double scale = Math.max (scaleX, scaleY);
		double mid = scale * bufferedimage.getWidth()/2;
		double bottom = scale * bufferedimage.getHeight();

		// Draw 6 copies of the canvas image into the 6 slices
		// The un-flipped and flipped images will be alternated to 
		// create relections
		for (int i = 0; i < 8; i++)
		{
			g3.setClip (slice);
			AffineTransform txi = new AffineTransform();
			// 3. Rotate the image to match the rotation of the slice
			txi.rotate (i*Math.PI/4, centerX, centerY);
			// 2. Translate the image so that the center of its bottom
			//    edge is at the center of the painting 
    		txi.translate(centerX-mid, centerY-bottom);
			// 1. Scale the image to fit into the slice
			txi.scale (scale, scale);
			if (i % 2 == 0)
				g3.drawImage (bufferedimage, txi, null);
			else
				g3.drawImage (flippedimage, txi, null);
			// Rotate the clipping slice for the next slice of the painting
			slice.transform (txs);
		}

		// Draw a circle around the image
		g3.setClip (null);
		g3.setPaint (Color.BLACK);
 		g3.setStroke(new BasicStroke(3.0f));
		g3.draw(new Ellipse2D.Double (centerX-radius, centerY-radius, 2*radius, 2*radius));
	}

// Function: paintComponent
// Arguments: The graphics container for the design8 JPanel in the JTabbedPanel
// Purpose: This function will be called to update the design8 tab image

	public void paintComponent (Graphics g)
	{
		//System.out.println ("In design8 paintComponent");
		super.paintComponent (g); 
		Graphics2D g3 = (Graphics2D) g; 
		g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
		RenderingHints.VALUE_ANTIALIAS_ON); 
		paintDesign8 (g3, getWidth(), getHeight());
	} 
} 
