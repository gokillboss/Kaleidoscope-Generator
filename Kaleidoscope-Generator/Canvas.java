// File: Canvas.java 
// Originally written by: Dr. Watts
// Modified by:
// Contents:
 
import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
 
public class Canvas extends JPanel implements  MouseMotionListener, MouseListener, KeyListener
{
	
	private Data data;
	private boolean inScreen = true;
	private int prevX = 0, prevY = 0;
	private DesignElement selectedElement = null;

	public Canvas (Data D) 
	{ 
		data = D;
		data.canvas = this;

       

        	addMouseMotionListener(this);
        	addMouseListener(this);
			setFocusable (true);
			grabFocus();
        	addKeyListener(this);

        	repaint(); 
   	} 

	public void paintComponent (Graphics g) 
	{ 
		super.paintComponent (g); 
		Graphics2D g2 = (Graphics2D) g; 
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
		RenderingHints.VALUE_ANTIALIAS_ON); 
		for (int i = 0; i < data.elements.size(); i++){
			data.elements.get(i).paintElement (g2);
		}
		grabFocus();
		
	} 

	public void mouseDragged(MouseEvent e)
	{
		//System.out.println ("Mouse dragged called" + e);
		if (selectedElement == null)
			return;
		int currentX = e.getX();
		int currentY = e.getY();
		if (e.isShiftDown())
		{ // Resize selected element
			selectedElement.scale (prevX, prevY, currentX, currentY);
		}
		else if (e.isControlDown())
		{ // Rotate selected element
			selectedElement.rotate (prevX, prevY, currentX, currentY);
		}
		else
		{ // Move selected element
			selectedElement.translate (prevX, prevY, currentX, currentY);
		}
		prevX = currentX;
		prevY = currentY;
		repaint();
	}

	public void mouseMoved(MouseEvent e)
	{
		//System.out.println ("Mouse moved called");
	}

	public void mousePressed(MouseEvent e)
	{
		//System.out.println ("Mouse pressed called");
	
		if (selectedElement != null)
		selectedElement.isSelected = false;
		if (e.getButton() == e.BUTTON1) // Left mouse button
		{ // Select an existing element
		selectedElement = data.getSelected (e.getX(), e.getY());
		}
		else if (e.getButton() == e.BUTTON3) // Right mouse button
		{ // Create a new element centered at mouse position
		selectedElement = data.getNewElement (e.getX(), e.getY());
		}
		prevX = e.getX();
		prevY = e.getY();
		repaint();
	}

	public void mouseReleased(MouseEvent e)
	{
		//System.out.println ("Mouse released called");
	}

	public void mouseEntered(MouseEvent e) 
	{
		//System.out.println ("Mouse entered called");
		inScreen = true;
	}
	public void mouseExited(MouseEvent  e) 
	{
		//System.out.println ("Mouse exited called");
		inScreen = false;
	}
	public void mouseClicked(MouseEvent e)
	{
		//System.out.println ("Mouse clicked called");
		selectedElement = null;
		for (int i = data.elements.size() - 1; i >= 0 && selectedElement == null; i--)
			if (data.elements.get(i).inElement (e.getX(), e.getY()))
				selectedElement = data.elements.get(i);
	}


 
        public void keyTyped (KeyEvent e)
        {
                //System.out.println ("Key Listener event: " + e);
        }

        public void keyPressed (KeyEvent e)
        {
                //System.out.println ("Key Listener event: " + e);
		if (selectedElement == null)
			return;
		if (e.getKeyCode () == KeyEvent.VK_KP_RIGHT || e.getKeyCode () == KeyEvent.VK_RIGHT) 
			selectedElement.translate (1, 0);
		else if (e.getKeyCode () == KeyEvent.VK_KP_LEFT || e.getKeyCode () == KeyEvent.VK_LEFT) 
			selectedElement.translate (-1, 0);
		else if (e.getKeyCode () == KeyEvent.VK_KP_DOWN || e.getKeyCode () == KeyEvent.VK_DOWN) 
			selectedElement.translate (0, 1);
		else if (e.getKeyCode () == KeyEvent.VK_KP_UP || e.getKeyCode () == KeyEvent.VK_UP) 
			selectedElement.translate (0, -1);
		else if (e.getKeyCode () == KeyEvent.VK_L)
			selectedElement.rotate(0.035);
		else if (e.getKeyCode () == KeyEvent.VK_R)
			selectedElement.rotate(-0.035);
		else if (e.getKeyChar() == '+')
			selectedElement.scale(1.05, 1.05);
		else if (e.getKeyChar() == '-')
			selectedElement.scale(0.95, 0.95);
		else if (e.getKeyCode () == KeyEvent.VK_DELETE)
		    data.deleteSelected(selectedElement);
		else if (e.getKeyCode () == KeyEvent.VK_B)
		    data.moveSelectedBack(selectedElement);
			


		repaint ();
        }

        public void keyReleased (KeyEvent e)
        {
                //System.out.println ("Key Listener event: " + e);
        }
} 
