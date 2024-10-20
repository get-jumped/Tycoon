/**
 * Tycoon.java
 * 
 * making a game
 * 
 * @author Aidan Yamada
 * @version 1.0
 * @since 4/2/2022
 */

import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Tycoon extends JFrame
{
	final private String [] pipHierarchy = {"Three", "Four", "Five", "Six", "Seven", 
	"Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace", "Two", "Joker"};
	final private String [] suitHierarchy = {"Spades", "Diamonds", "Hearts", "Clubs"};
	private ArrayList<Card> player1 = new ArrayList<Card>();
	private ArrayList<Card> player2 = new ArrayList<Card>();
	private ArrayList<Card> player3 = new ArrayList<Card>();
	private ArrayList<Card> player4 = new ArrayList<Card>();
	private Deck pile;
	private TycoonPanel set;
	private int first;
	private String savePip;
	private int saveNum;
	
	public Tycoon ()
	{
		savePip = "Three";
	}
	
	public static void main (String [] args)
	{
		Tycoon start = new Tycoon();
		start.setUp();
		start.createGame();
	}
	
	public void setUp ()
	{
		pile = new Deck();
		pile.setUpDeck(true);
		ArrayList<Card> temp = pile.getDeck();
		while(temp.size() > 0)
		{
			if(temp.size() > 0)
				player1.add(temp.remove((int)(Math.random()*temp.size())));
			if(temp.size() > 0)
				player2.add(temp.remove((int)(Math.random()*temp.size())));
			if(temp.size() > 0)
				player3.add(temp.remove((int)(Math.random()*temp.size())));
			if(temp.size() > 0)
				player4.add(temp.remove((int)(Math.random()*temp.size())));
		}
		sort(player1, 0, player1.size()-1);
		sort(player2, 0, player2.size()-1);
		sort(player3, 0, player3.size()-1);
		sort(player4, 0, player4.size()-1);
		testOutput();
		
	}
	
	public void testOutput ( )
	{
		System.out.println("\n\n\n");
		System.out.println("Player 1 Deck");
		for(int i=0; i<player1.size(); i++)
		{
			System.out.print(player1.get(i) + ", ");
		}
		System.out.println("\n\nPlayer 2 Deck");
		for(int i=0; i<player2.size(); i++)
		{
			System.out.print(player2.get(i) + ", ");
		}
		System.out.println("\n\nPlayer 3 Deck");
		for(int i=0; i<player3.size(); i++)
		{
			System.out.print(player3.get(i) + ", ");
		}
		System.out.println("\n\nPlayer 4 Deck");
		for(int i=0; i<player4.size(); i++)
		{
			System.out.print(player4.get(i) + ", ");
		}
		System.out.println("\n\n\n");
	}
	
	public void sort(ArrayList<Card> a, int start, int end)
	{
		if(end - start < 2)
		{
			if(end > start && a.get(start).greaterThan(a.get(end), pipHierarchy, suitHierarchy))
			{
				Card temp = a.get(end);
				a.set(end, a.get(start));
				a.set(start, temp);
			}	
		}
		else
		{
			int middle = (start + end)/2;
			sort(a, start, middle);
			sort(a, middle+1, end);
			merge(a, start, middle, end);
		}
	}
	
	public void merge(ArrayList<Card> a, int s, int m, int e)
	{
		int i = s;
		int j = m + 1;
		
		ArrayList<Card> temp = new ArrayList<Card>();
		
		while(i <= m && j <= e)
		{
			if(a.get(i).greaterThan(a.get(j), pipHierarchy, suitHierarchy))
			{
				temp.add(a.get(j));
				j++;
			}
			else
			{
				temp.add(a.get(i));
				i++;
			}
		}
		while(i <= m)
		{
			temp.add(a.get(i));
			i++;
		}
		while(j <= e)
		{
			temp.add(a.get(j));
			j++;
		}
		
		int counter = 0;
		for(int k = s; k <= e; k++)
		{
			a.set(k, temp.get(counter));
			counter++;
		}
	}
	
	public void createGame()
	{
		JFrame frame = new JFrame("Tycoon");
		frame.setSize(1200, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		set = new TycoonPanel(this);
		frame.add(set);
		
		frame.setVisible(true);
		
		first = getFirst();
	}
	
	public int getFirst()
	{
		if(player1.get(0).getPip() == "Three")
		{
			if(player1.get(0).getSuit() == "Spades")
			{
				return 1;
			}
		}
		else if(player2.get(0).getPip() == "Three")
		{
			if(player1.get(0).getSuit() == "Spades")
			{
				return 2;
			}
		}
		else if(player3.get(0).getPip() == "Three")
		{
			if(player1.get(0).getSuit() == "Spades")
			{
				return 3;
			}
		}
		else if(player4.get(0).getPip() == "Three")
		{
			if(player1.get(0).getSuit() == "Spades")
			{
				return 4;
			}
		}
		return 0;
	}
	
	public ArrayList<Card> getPlayer1()
	{
		return player1;
	}
	
	public ArrayList<Card> getPlayer2()
	{
		return player2;
	}
	
	public ArrayList<Card> getPlayer3()
	{
		return player3;
	}
	
	public ArrayList<Card> getPlayer4()
	{
		return player4;
	}
	
	public int getPlayer1Size()
	{
		return player1.size();
	}
	
	public int getPlayer2Size()
	{
		return player2.size();
	}
	
	public int getPlayer3Size()
	{
		return player3.size();
	}
	
	public int getPlayer4Size()
	{
		return player4.size();
	}
	
	public void playPlayer2()
	{
		
	}
	
	public void playPlayer3()
	{
		
	}
	
	public void playPlayer4()
	{
		
	}
	
	public void selection(ArrayList<Card> playerHand)
	{
		// counts how many cards
		int count = 0;
		for(int i = 0; i < playerHand.size(); i++)
		{
			if(savePip.equals(playerHand.get(i).getPip()))
			{
				count++;
			}
		}
		System.out.println(count);
		if(saveNum == 0)
		{
			saveNum = count;
		}
		boolean loop = true;
		int temp = 0;
		while(loop)
		{
			if(savePip.equals(pipHierarchy[temp]))
			{
				savePip = pipHierarchy[temp + 1];
				loop = false;
			}
			temp++;
		}
		if(count < saveNum)
		{
			selection(playerHand);
		}
		else
		{
			playCards(playerHand);
		}
	}
	
	public void playCards(ArrayList<Card> hand)
	{
		boolean loop = true;
		int temp = 0;
		int count = saveNum;
		System.out.println("BABA");
		while(loop)
		{
			if(savePip.equals(hand.get(temp)) && count > 0)
			{
				hand.remove(temp);
				count--;
				if(count == 0)
				{
					loop = false;
				}
			}
			temp++;
		}
	}
}
