/**
 * Deck.java
 * 
 * A deck of cards
 * 
 * @author Aidan Yamada
 * @version 1.0
 * @since 4/2/2022
 * 
 */

import java.util.List;
import java.util.ArrayList;
 
public class Deck
{
	private ArrayList<Card> stack;
	
	final private String [] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
	final private String [] pips = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
	
	public Deck ( )
	{
		stack = new ArrayList<Card>();
	}
	
	public void setUpDeck (boolean extra)
	{
		for(int i=0; i<4; i++)
		{
			for(int j=0; j<13; j++)
			{
				stack.add(new Card(pips[j], suits[i]));
			}
		}
		if(extra)
		{
			stack.add(new Card("Joker", null));
			stack.add(new Card("Joker", null));
		}
	}
	
	public ArrayList<Card> getDeck ( )
	{
		return stack;
	}
	
	public int getSize ( )
	{
		return stack.size();
	}
}
