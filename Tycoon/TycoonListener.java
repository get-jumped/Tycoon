/**
 * TycoonListener.java
 * 
 * making a game
 * 
 * @author Aidan Yamada
 * @version 1.0
 * @since 4/6/2023
 */

//DOUBLE CHECK IMPORTS
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TycoonListener extends TycoonPanel implements MouseListener
{
	boolean selected;
	boolean highlight;
	TycoonPanel display;
	
	int a;
	
	TycoonListener()
	{
		selected = false;
		highlight = false;
	}
	
	TycoonListener(TycoonPanel d, int po)
	{
		selected = false;
		highlight = false;
		display = d;
		a = po;
	}
	
	public boolean getSelected()
	{
		return selected;
	}
	
	public boolean getHighlight()
	{
		return highlight;
	}
	
	public void setSelected(boolean s)
	{
		selected = s;
	}
	
	public void mouseClicked(MouseEvent e)
	{
		//invoked when the mouse button has been clicked (Pressed and relased) on a component
		//System.out.println("Mouse clicked - " + a);
		if(selected == false)
		{
			selected = true;
			//System.out.println("ABBA");
		}
		else if(selected == true)
		{
			selected = false;
			//System.out.println("BABA");
		}
		
		//System.out.println("Mouse clicked - " selected);
		
		display.repaint();
	}
	
	public void mousePressed(MouseEvent e)
	{
		//invoked when a mouse button has been pressed on a component
		//System.out.println("Mouse pressed");
	}
	
	public void mouseReleased(MouseEvent e)
	{
		//invoked when a mouse button has been released on a component
		//System.out.println("Mouse released");
	}
	
	public void mouseEntered(MouseEvent e)
	{
		//invoked when the mouse enters a component
		//System.out.println("Mouse entered - " + a);
		highlight = true;
		display.repaint();
	}
	
	public void mouseExited(MouseEvent e)
	{
		//invoked when the mouse exits a component
		//System.out.println("Mouse exited - " + a);
		highlight = false;
		display.repaint();
	}
}
