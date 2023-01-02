package model;

import java.util.LinkedList;

public class Pot {

	private Pile[] piles;
	private MiddlePile middlePile;
	private Bag bag;
	
	 private boolean[] possible_pile;

	private Game game_ref;
	
	private int numberOfPiles;
	
	public Pot(int numberOfPlayers, Game ref) {
		
		game_ref = ref;
		
		middlePile = new MiddlePile(this);
		numberOfPiles = 1 + numberOfPlayers *2;
		possible_pile = new boolean[1 + numberOfPlayers * 2];
		
		instanciatePiles(numberOfPlayers);
		
		bag = new Bag(piles, this);
		
		display();
	}
	
	public void sendAddedTilesToView(LinkedList<Tile> to_add, int previous_index, boolean delete) {
		this.game_ref.updateMiddlePileView(to_add, previous_index, delete);
	}
	
	public Pile getPile(int ID) {
		return piles[ID];
	}
	
	public void test(int number) {
		
		piles[number].test(0);
		/*for(Pile p: piles) {
			p.test();
		}*/
		
		//display();
	}
	
	private void instanciatePiles(int numberOfPlayers) {
		piles = new Pile[1 + numberOfPlayers * 2];
		for(int i = 0; i< 1 + numberOfPlayers * 2; i++) piles[i] = new Pile(game_ref, middlePile, this, i);
	}
	
	public Pile getPileIndex(int index) {
		return piles[index];
	}
	
	public LinkedList<Tile> modifyMiddlePile(int index) {
		return middlePile.modifyMiddlePile(index);
	}
	
	private void sendToMiddle() {
		
	}
	
	public void display() {
		for(Pile p: piles) p.display();
		middlePile.display();
	}
	
	public void sendToBag(LinkedList<Tile> tiles) {
		bag.getTilesBack(tiles);
	}
	
	public boolean[] checkPossible() {
		  for(int i = 0; i < possible_pile.length; i++) {
			  possible_pile[i] = piles[i].checkPossible();
		  }
		  
		  return possible_pile;
	  }
	
	
	public void testShuffle() {
		bag.distributeContents();
		
	}
	
	public void sendCompleteMiddlePileToView(boolean bool) {
		middlePile.sendCompletePileToView(bool);
	}

	public void sendToBag(Tile p) {
		bag.getTilesBack(p);
		
	}
	
	public boolean isPlayPossible() {
		int empty = 0;
		for(Pile p: piles) empty += p.isEmpty();
		
		empty+= middlePile.isEmpty();
		
		return empty != this.numberOfPiles + 1;
		
	}
	
	

	
}
