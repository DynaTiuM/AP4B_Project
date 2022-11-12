package game;

import java.awt.Graphics;
import java.util.ArrayList;

// A Pot has many piles that each has 4 tiles. It also has a middle pile
public class Pot {
	
	private ArrayList<Pile> piles;
	private MiddlePile middlePile;
	
	public Pot(int players) {
		piles = new ArrayList<>();
		middlePile = new MiddlePile();
		instanciatePiles(players);
	}
	
	private void instanciatePiles(int players) {
		int numberOfPiles = 0, i = 0;
		//decide nombre de piles en fonction nombre de joueurs, peut remplacer le switch par : numberOfPiles = 1 + players * 2
		switch(players) {
		case 2 :
			numberOfPiles = 5;
			break;
		case 3 :
			numberOfPiles = 7;
			break;
		case 4 :
			numberOfPiles = 9;
			break;
		}
		
		while(i < numberOfPiles) {
			piles.add(new Pile());
			i++;
		}
	}
	
	public void initialisation() {
		for(Pile p : piles) {
			p.randomDistribution();
		}
	}
	
	
	public void draw(Graphics g) {
		
	}

}
