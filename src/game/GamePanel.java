package game;

import java.awt.Graphics;
import java.util.ListIterator;


import javax.swing.JPanel;

import menu.WindowProperties;

public class GamePanel extends JPanel implements WindowProperties {

	Game game;
	int numberPlayers;
	private ListIterator<Pile> iterator_pile;
	GamePanel(int numberPlayers){
		this.setPreferredSize(WINDOW_GAME);
		this.setFocusable(true);
		this.setLayout(null);
		
		game = new Game(numberPlayers);
		this.numberPlayers = numberPlayers;
	}
	
	public void draw(Graphics g) {
		int i = 0;
		while(i < numberPlayers) {
			game.getPlayer(i).draw(g);
			i++;
		}
		i=0;
		
			iterator_pile = game.getPile();
			while(iterator_pile.hasNext()) {
				iterator_pile.next().draw(g);
			}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

}
