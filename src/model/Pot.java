package model;

import java.util.LinkedList;

public class Pot {

	private Pile[] piles;
	private MiddlePile middle_pile;
	private Bag bag;
	
	 private boolean[] possible_pile;

	private Game game_ref;
	
	public Pot(int numberOfPlayers, Game ref) {
		
		game_ref = ref;
		
		middle_pile = new MiddlePile(this);
		
		possible_pile = new boolean[1 + numberOfPlayers * 2];
		
		instanciatePiles(numberOfPlayers);
		
		bag = new Bag(piles, this);
		
		display();
	}
	
	public void sendAddedTilesToView(LinkedList<Tile> to_add, int previous_index) {
		this.game_ref.updateMiddlePileView(to_add, previous_index);
	}
	
	public void test() {
		
		piles[0].test();
		/*for(Pile p: piles) {
			p.test();
		}*/
		
		//display();
	}
	
	private void instanciatePiles(int numberOfPlayers) {
		piles = new Pile[1 + numberOfPlayers * 2];
		for(int i = 0; i< 1 + numberOfPlayers * 2; i++) piles[i] = new Pile(game_ref, middle_pile, this, i);
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
	
	public boolean[] checkPossible() {
		  for(int i =0; i< possible_pile.length; i++) {
			  possible_pile[i] = piles[i].checkPossible();
		  }
		  
		  return possible_pile;
	  }
	
	
	public void testShuffle() {
		bag.distributeContents();
		
	}

	
	

	
}
