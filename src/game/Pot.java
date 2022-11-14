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
		iterator_pile = piles.listIterator();
	}
	
	private void instanciatePiles(int players) {
		int numberOfPiles = 0, i;
		
		numberOfPiles = 1 + players * 2;
		
		for( i =0; i<(numberOfPiles-1)/2; i++) {
			piles.add(new Pile(new Position(0, i)));
			piles.add(new Pile(new Position(1,i)));
		}
		
		piles.add(new Pile(new Position(0,i)));
		
	}
	
	public void initialisation() {
		for(Pile p : piles) {
			p.randomDistribution();
		}
	}
	
	
	public void draw(Graphics g) {
		
	}
	
	public ListIterator<Pile> returnPile() {
		
		return  iterator_pile;
	}

}
