/**
 * TycoonPanel.java
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

class TycoonPanel extends JPanel implements ActionListener
{
	private Tycoon game;
	private ArrayList<TycoonListener> listen = new ArrayList<TycoonListener>();
	private ArrayList<JLabel> area = new ArrayList<JLabel>();
	static boolean first;
	private JButton button;
	private int player;
	private String playPip;
	private int playNum;
	private int saveNum;
	
	TycoonPanel()
	{
		setBackground(Color.BLACK);
	}
	TycoonPanel(Tycoon t)
	{
		setBackground(Color.BLACK);
		game = t;
		first = true;
		button = new JButton();
		for(int i = 0; i < t.getPlayer1Size(); i++)
		{
			listen.add(new TycoonListener(this, i));
		}
	}
	
	//disable button while other players play
	//can keep getFirst in the Tycoon class
	//totally remove button
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		if(first)
		{
			setUpLabel();
			player = game.getFirst();
			first = false;
			System.out.println("First");
		}
		
		/*while(player != 1)
		{
			if(player == 2 && game.getPlayer2Size() != 0)
			{
				
			}
			if(player == 3 && game.getPlayer3Size() != 0)
			{
				
			}
			if(player == 4 && game.getPlayer4Size() != 0)
			{
				
			}
		}*/
		
		if(player != 1)
		{
			System.out.println("Doing selction");
			System.out.println("2 " + game.getPlayer2Size());
			System.out.println("3 " + game.getPlayer3Size());
			System.out.println("4 " + game.getPlayer4Size());
			if(player == 2 && game.getPlayer2Size() != 0)
			{
				System.out.println("2");
				game.selection(game.getPlayer2());
			}
			else if(player == 3 && game.getPlayer3Size() != 0)
			{
				System.out.println("3");
				game.selection(game.getPlayer3());
			}
			else if(player == 4 && game.getPlayer4Size() != 0)
			{
				System.out.println("4");
				game.selection(game.getPlayer4());
			}
		}
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setStroke(new BasicStroke(5));
		
		g2d.setColor(Color.WHITE);
		g2d.drawRect(10, 230, 250, 150);
		g2d.drawRect(465, 40, 250, 150); //60
		g2d.drawRect(920, 230, 250, 150);
		
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g2d.drawString("Player 2", 72, 220);
		g2d.drawString("Player 3", 527, 30); // 50
		g2d.drawString("Player 4", 982, 220);
		
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		g2d.drawString("Cards" , 20, 325);
		g2d.drawString("Left", 40, 370);
		g2d.drawString("Cards" , 475, 135);
		g2d.drawString("Left", 495, 180);
		g2d.drawString("Cards" , 930, 325);
		g2d.drawString("Left", 950, 370);
		
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 95));
		g2d.drawString(String.valueOf(game.getPlayer2Size()), 150, 360);
		g2d.drawString(String.valueOf(game.getPlayer3Size()), 605, 170);
		g2d.drawString(String.valueOf(game.getPlayer4Size()), 1060, 360);
		
		Image image;
		int size = game.getPlayer1Size();
		ArrayList<Card> player1 = game.getPlayer1();
		int start = (1200 - 80 * size)/2;
		
		for(int j = 0; j < size; j++)
		{
			area.get(j).setBounds(start + (80 * j), 575, 80, 131);
			add(area.get(j));
		}

		for(int i = 0; i < size; i++)
		{
			image = player1.get(i).recieveImage();
			
			if(listen.get(i).getSelected() || listen.get(i).getHighlight())
			{
				g2d.drawImage(image, start + (80 * i), 555, 80 , 131, null);
			}
			else
			{
				g2d.drawImage(image, start + (80 * i), 575, 80 , 131, null);
			}
		}
		
		button.setBounds(1020, 495, 150, 50);
		button.setFocusable(false);
		button.setText("Play");
		button.addActionListener(this);
		add(button);
	}
	
	public void setUpLabel()
	{
		for(int i = 0; i < game.getPlayer1Size(); i++)
		{
			area.add(new JLabel());
			area.get(i).addMouseListener(listen.get(i));
		}
	}
	
	public boolean goodSelection()
	{
		ArrayList<Card> temp = game.getPlayer1();
		String savePip = null;
		for(int i = 0; i < listen.size(); i++)
		{
			if(listen.get(i).getSelected())
			{
				if(savePip == null)
				{
					savePip = temp.get(i).getPip();
				}
				else if(!(temp.get(i).getPip().equals(savePip)))
				{
					return false;
				}
			}
		}
		playPip = savePip;
		return true;
	}
	
	public void delete()
	{
		ArrayList<Card> temp = game.getPlayer1();
		int save = listen.size() - 1;
		for(int i = save; i >= 0; i--)
		{
			if(listen.get(i).getSelected())
			{
				temp.remove(i);
				listen.remove(i);
				remove(area.get(i));
				area.remove(i);
				playNum++;
			}
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == button)
		{
			if(goodSelection())
			{
				delete();
				repaint();
			}
		}
	}
}
