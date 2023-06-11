// File: Data.java
// Originally written by: Dr. Watts
// Modified by:
// Contents:

import java.awt.*;
import java.util.*;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.geom.GeneralPath;
import java.awt.image.*;

public class Data
{
	public Canvas canvas;
	public Image image;
	public Design6 design6;
	public Design8 design8;
	public Kaleidoscope kaleidoscope;
	public ArrayList<DesignElement> elements;
	public DesignElement selectedElement = null;
	public int selectedIndex = -1;
	public Color currentStroke = Color.BLACK;
	public Color currentFill = Color.WHITE;
	public DesignElement onDeckElement = newHeart (0, 0, 90, 90, Color.BLACK, Color.WHITE);
	public SelectedPanel selected;

        public Data ()
        {
		elements = new ArrayList<DesignElement> ();
		//elements.add (newHeart(350, 350, 150, 150, Color.BLUE, Color.RED));
		elements.add (EQTriangle(200, 0, 100, 00, Color.BLACK, Color.GREEN));
		//elements.add (newRectangle(350, 350, 300, 300, Color.MAGENTA, Color.blue));
		//elements.add (lightning(300, 0, 200, 200, Color.ORANGE, Color.yellow));	
		//elements.add (Moon(350,350,200,200, Color.YELLOW, Color.CYAN));
		//elements.add (Star(350,350,200,200, Color.YELLOW, Color.CYAN));

		for (int i = 0; i < elements.size(); i++)
			System.out.println (elements.get(i));
        }

        public Data (String fileName)
        {
		elements = new ArrayList<DesignElement> ();
		FileIO io = new FileIO (this);
		io.readFile (fileName);
        }

	public DesignElement getSelected (int mouseX, int mouseY)
	{
		if (selectedElement != null)
			selectedElement.isSelected = false;
		selectedElement = null;
		selectedIndex = -1;
		for (int i = elements.size() - 1; i >= 0 && selectedElement == null; i--)
			if (elements.get(i).inElement (mouseX, mouseY))
			{
				selectedElement = elements.get(i);
				selectedIndex = i;
			}
		if (selectedElement != null)
			selectedElement.isSelected = true;
		return selectedElement;
	}

	

	public DesignElement deleteSelected (DesignElement selectedElement)
	{	
		elements.remove(selectedElement);
		selectedElement = null;
		selectedIndex = -1;
		canvas.repaint();
		return null;
	}

	public boolean moveSelectedBack (DesignElement selectedElement)
	{
		if (selectedIndex == elements.size()){
			return false;
		}else
		elements.add(selectedElement);
		selectedIndex = - 1;
		canvas.repaint();
		return true;
	}

	public boolean moveSelectedForward ()
	{
		return false;
	}


	public DesignElement getNewElement (int mouseX, int mouseY)
	{
		DesignElement newOne = onDeckElement.clone ();
		newOne.moveTo (mouseX, mouseY);
		newOne.isSelected = true;
		elements.add (newOne);
		selectedElement = newOne;
		selectedIndex = elements.size() - 1;
		return newOne;
	}



	public DesignElement newRectangle (double cx, double cy, double width, double height, Color stroke, Color fill)
	{
		DesignElement rect = new DesignElement ();
		rect.centerX = cx;
		rect.centerY = cy;
		rect.strokeColor = stroke;
		rect.fillColor = fill;
		rect.path.moveTo (cx-width/2, cy-height/2);
		rect.path.lineTo (cx+width/2, cy-height/2);
		rect.path.lineTo (cx+width/2, cy+height/2);
		rect.path.lineTo (cx-width/2, cy+height/2);
		rect.path.lineTo (cx-width/2, cy-height/2);
		return rect;
	}

	public DesignElement newHeart (double cx, double cy, double width, double height, Color stroke, Color fill)
	{
		DesignElement heart = new DesignElement ();
		heart.centerX = cx;
		heart.centerY = cy;
		heart.strokeColor = stroke;
		heart.fillColor = fill;
		heart.path.moveTo (cx, cy-height/3);
		heart.path.curveTo (cx-0.44*width, cy-0.87*height, cx-0.87*width, cy, cx, cy+height/2);
		heart.path.curveTo (cx+0.87*width, cy, cx+0.44*width, cy-0.87*height, cx, cy-height/3);
		return heart;
	}


	public DesignElement lightning (double cx, double cy, double width, double height, Color stroke, Color fill)
	{
		DesignElement lightning = new DesignElement ();
		lightning.centerX = cx+width/2;
		lightning.centerY = cy+height/2;
		lightning.strokeColor = stroke;
		lightning.fillColor = fill;
		lightning.path.moveTo(cx+0.3*width, cy+0.2*height);
		lightning.path.lineTo(cx+0.3*width, cy+0.2*height);
		lightning.path.lineTo(cx+0.2*width, cy+0.5*height);
		lightning.path.lineTo(cx+0.4*width, cy+0.5*height);
		lightning.path.lineTo(cx+0.3*width, cy+0.8*height);
		lightning.path.lineTo(cx+0.6*width, cy+0.4*height);
		lightning.path.lineTo(cx+0.4*width, cy+0.4*height);
		lightning.path.lineTo(cx+0.5*width, cy+0.2*height);
		lightning.path.lineTo(cx+0.3*width, cy+0.2*height);

		return lightning;
	}


	public DesignElement Triangle (double cx, double cy, double width, double height, Color stroke, Color fill)
	{
		DesignElement Triangle = new DesignElement ();
		Triangle.centerX =  cx+width/2;
		Triangle.centerY =  cy+height/2;
		Triangle.strokeColor = stroke;
		Triangle.fillColor = fill;
		Triangle.path.moveTo (cx+10, cy);
		Triangle.path.lineTo(cx+10,cy+height);
		Triangle.path.lineTo(cx+10+width,cy+height);
		Triangle.path.lineTo(cx+10, cy);
		return Triangle;
	}


	public DesignElement EQTriangle (double cx, double cy, double width, double height, Color stroke, Color fill)
	{
		DesignElement EQTriangle = new DesignElement ();
		EQTriangle.centerX =  cx;
		EQTriangle.centerY =  cy+height*2/3;
		EQTriangle.strokeColor = stroke;
		EQTriangle.fillColor = fill;
		EQTriangle.path.moveTo (cx,cy);
		EQTriangle.path.lineTo(cx-width/2,cy+width);
		EQTriangle.path.lineTo(cx+width/2,cy+width);
		EQTriangle.path.lineTo(cx, cy);
		return EQTriangle;
	}


	public DesignElement Moon (double cx, double cy, double width, double height, Color stroke, Color fill)
	{
		DesignElement Moon = new DesignElement ();
		Moon.centerX =  cx+width/3;
		Moon.centerY =  cy+height/3;
		Moon.strokeColor = stroke;
		Moon.fillColor = fill;
		Moon.path.moveTo (cx, cy-height/3);
		Moon.path.curveTo(cx - 0.4*width, cy+0.3*height, cx,cy+1.3*height, cx+width,cy+0.5*height);
		Moon.path.quadTo(cx, cy+0.5*height, cx, cy-height/3);
		return Moon;
	}


	public DesignElement Star (double cx, double cy, double width, double height, Color stroke, Color fill)
	{
		DesignElement Star = new DesignElement ();
		Star.centerX =  cx+width/2;
		Star.centerY =  cy+height/2;
		Star.strokeColor = stroke;
		Star.fillColor = fill;
		Star.path.moveTo(cx+ width/2, cy);
		Star.path.lineTo(cx+width/3, cy+height/3);
		Star.path.lineTo(cx, cy+height/2);
		Star.path.lineTo(cx+width/3, cy+height*2/3);
		Star.path.lineTo(cx+width/2, cy+height);
		Star.path.lineTo(cx+width*2/3, cy+height*2/3);
		Star.path.lineTo(cx+width, cy+height/2);
		Star.path.lineTo(cx+width*2/3, cy+height/3);
		Star.path.lineTo(cx+ width/2, cy);
		

		 
		return Star;
	}

	


// Add your DesignElement creation functions below this line
}
