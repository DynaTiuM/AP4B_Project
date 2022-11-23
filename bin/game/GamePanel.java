package game;

import java.awt.Color;
import java.awt.Graphics;


import java.util.ListIterator;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import menu.WindowProperties;

public class GamePanel extends JPanel implements WindowProperties {

	Game game;
	int numberPlayers;
	GamePanel(int numberPlayers,  JLayeredPane pane_p){
		this.setBounds(0,0,800,800);;
		this.setFocusable(true);
		this.setLayout(null);
		
		game = new Game(numberPlayers, pane_p, this);
		this.numberPlayers = numberPlayers;
		
	}
	
	public void draw(Graphics g) {
		int i = 0;
		while(i < numberPlayers) {
			game.getPlayer(i).draw(g);
			game.getPot().draw(g);
			i++;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

}
