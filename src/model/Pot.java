package model;

import java.util.LinkedList;

public class Pot {

	private Pile[] piles;
	private MiddlePile middle_pile;
	private Bag bag;
	

	private Game game_ref;
	
	public Pot(int numberOfPlayers, Game ref) {
		
		game_ref = ref;
		
		middle_pile = new MiddlePile(this);
		
		instanciatePiles(numberOfPlayers);
		
		bag = new Bag(piles);
		
		display();
		
		
		
		
		
		
		
	}
	
	public void test() {
		
		for(Pile p: piles) p.test();
		//piles[0].test();
	}
	
	private void instanciatePiles(int numberOfPlayers) {
		piles = new Pile[1 + numberOfPlayers * 2];
		for(int i = 0; i< 1 + numberOfPlayers * 2; i++) piles[i] = new Pile(game_ref, middle_pile, this);
	}
	
	public Pile getPileIndex(int index) {
		return piles[index];
	}
	
	private void sendToMiddle() {
		
	}
	
	public void display() {
		for(Pile p: piles) p.display();
		middle_pile.display();
	}
	
	public void sendToBag(LinkedList<Tile> tiles) {
		bag.getTilesBack(tiles);
	}
	
}