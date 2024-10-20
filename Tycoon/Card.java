/**
 * Card.java
 * 
 * Represents a card in a deck
 * 
 * @author Aidan Yamada
 * @version 1.0
 * @since 4/2/2022
 * 
 */

import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;


 
 public class Card
 {
	Image display;
	private String pip;
	private String suit;
	 
	public Card ( )
	{
		 
	}
	 
	public Card (String p, String s)
	{
		pip = p;
		suit = s;
		if(pip == "Joker")
		{
			display = new ImageIcon("Joker.png").getImage(); 
		}
		else
		{
			display = new ImageIcon(pip + " of " + suit + ".png").getImage(); 
		}
	}
	 
	public String getPip()
	{
		return pip;
	}
	 
	public String getSuit ( )
	{
		return suit;
	}
	 
	public String toString ( )
	{
		return pip + " of " + suit;
	}
	 
	public Image recieveImage()
	{
		return display;
	}
	 
	public boolean greaterThan(Card temp, String [] pH, String [] sH)
	{
		int one = 0;
		int two = 0;
		 
		 //System.out.println(pip + "\t" + temp.getPip());
		for(int i = 0; i < pH.length; i++)
		{
			if(pip.equals(pH[i]))
			{
				one = i;
			}
			if(temp.getPip().equals(pH[i]))
			{
				two = i;
			}
		}
		
		//System.out.println(one + "\t" + two);
		 
		if(pH[one].equals(pH[two]) && !(pH[one].equals("Joker")))
		{
			for(int j = 0; j < 4; j++)
			{
				if(suit.equals(sH[j]))
				{
					one = j;
				}
				if(temp.getSuit().equals(sH[j]))
				{
					two = j;
				}
			}
			return (one > two);
		}
		else
		{
			return (one > two);
		}
	}
 }
