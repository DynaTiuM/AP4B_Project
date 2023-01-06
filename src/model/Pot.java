package model;

import java.util.LinkedList;

public class Pot {

	private Pile[] piles;
	private final MiddlePile middlePile;
	private final Bag bag;

	private final Game game_ref;
	
	private final int numberOfPiles;
	
	public Pot(int numberOfPlayers, Game ref) {
		
		game_ref = ref;
		
		middlePile = new MiddlePile(this);
		numberOfPiles = 1 + numberOfPlayers * 2;
		
		instanciatePiles();
		
		bag = new Bag(piles);
		
		//display();
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
		for(int i = 0; i < numberOfPiles; i++){
			piles[i] = new Pile(game_ref, middlePile,  i);
		}
	}
	
	public LinkedList<Tile> modifyMiddlePile(int index) {
		return middlePile.modifyMiddlePile(index);
	}
	
	public void sendToBag(LinkedList<Tile> tiles) {
		bag.getTilesBack(tiles);
	}
	
	public void sendCompleteMiddlePileToView(boolean bool) {
		middlePile.sendCompletePileToView(bool);
	}

	public void sendToBag(Tile p) {
		bag.getTilesBack(p);
		
	}
	
	public boolean isPlayNotPossible() {
		int empty = 0;
		for(Pile p: piles) empty += p.isEmpty();
		
		empty+= middlePile.isEmpty();
		
		return empty == this.numberOfPiles + 1;
		
	}

	public void sendMalusFirst(Tile first) {
		game_ref.sendMalusFirst(first);
		
	}
	
	public void setFirst() {
		middlePile.setFirst();
	}
	
	

	
}
