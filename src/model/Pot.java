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
		numberOfPiles = 1 + numberOfPlayers * 2;
		possible_pile = new boolean[numberOfPiles];
		
		instanciatePiles();
		
		bag = new Bag(piles, this);
		
		display();
	}
	
	public void sendAddedTilesToView(LinkedList<Tile> to_add, int previous_index, boolean delete) {
		this.game_ref.updateMiddlePileView(to_add, previous_index, delete);
	}
	
	public void distributeContents() {
		this.bag.distributeContents();
	}
	
	public void setTilesSelectedToHand(int numberOfPile, int ID) {
		piles[numberOfPile].setTilesSelectedToHand(ID);
	}
	
	private void instanciatePiles() {
		piles = new Pile[numberOfPiles];
		System.out.println(piles.length);
		for(int i = 0; i < numberOfPiles; i++){
			piles[i] = new Pile(game_ref, middlePile, this, i);
			System.out.println(i);
		}
	}
	
	public Pile getPileIndex(int index) {
		return piles[index];
	}
	
	public LinkedList<Tile> modifyMiddlePile(int index) {
		return middlePile.modifyMiddlePile(index);
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

	public void sendMalusFirst(Tile first) {
		game_ref.sendMalusFirst(first);
		
	}
	
	public void setFirst() {
		middlePile.setFirst();
	}
	
	

	
}
