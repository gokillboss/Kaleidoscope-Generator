// File: Image.java 
// Originally written by: Dr. Watts
// Modified by:
// Contents:


import java.awt.*;
import java.awt.geom.*;
import java.awt.font.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;

public class Image extends JPanel
{
	Data data;

	public Image (Data D)
	{
		data = D;
		data.image = this;
		repaint();
	}

	public void paintComponent (Graphics g)
	{
		//System.out.println ("In Image paintComponent");
		super.paintComponent (g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		BufferedImage bufferedimage = regularBufferedImage ();
		int width = getWidth();
		int height = getHeight();
		g2.setClip (new Ellipse2D.Double (0.1*width, 0.1*height, 0.8*width, 0.8*height));
		AffineTransform tx = new AffineTransform();
		tx.translate (0.1*width, 0.1*height);
		tx.scale(0.8, 0.8);
		g2.drawImage (bufferedimage, tx, null);
	}

	public BufferedImage regularBufferedImage ()
	{
		//System.out.println ("In regularBufferedImage");
		int width = data.canvas.getWidth();
		int height = data.canvas.getHeight();
		BufferedImage bufferedimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bufferedimage.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
			RenderingHints.VALUE_ANTIALIAS_ON); 
		g2.setPaint (Color.WHITE);
		g2.fillRect (0, 0, width, height);
		for (int i = 0; i < data.elements.size(); i++)
			data.elements.get(i).paintElement (g2);
		g2.dispose();
		return bufferedimage;
	}

	public BufferedImage flippedBufferedImage ()
	{
		//System.out.println ("In flippedBufferedImage");
		int width = data.canvas.getWidth();
		int height = data.canvas.getHeight();
		BufferedImage bufferedimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bufferedimage.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
			RenderingHints.VALUE_ANTIALIAS_ON); 
		g2.setPaint (Color.WHITE);
		g2.fillRect (0, 0, width, height);
		for (int i = 0; i < data.elements.size(); i++)
		{
			DesignElement e = data.elements.get(i).clone();
			e.scale (-1, 1);
			e.moveTo (width-e.centerX, e.centerY);
			e.paintElement (g2);
		}
		g2.dispose();
		return bufferedimage;
	}
}
