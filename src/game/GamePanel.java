package game;

import java.awt.Graphics;

import javax.swing.JPanel;

import menu.WindowProperties;

public class GamePanel extends JPanel implements WindowProperties {

	Game game;
	int numberPlayers;
	GamePanel(int numberPlayers){
		this.setPreferredSize(WINDOW_GAME);
		this.setFocusable(true);
		
		game = new Game(numberPlayers);
		this.numberPlayers = numberPlayers;
	}
	
	public void draw(Graphics g) {
		int i = 0;
		while(i < numberPlayers) {
			game.getPlayer(i).draw(g);
			i++;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

}
