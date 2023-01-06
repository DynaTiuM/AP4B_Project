package model;

import java.util.LinkedList;

//Class used to group the Piles, MiddlePile and the Bag as well as communication between these elements and view and bords of players
public class Pot {
	
	//contains the Piles, MiddlePile and Bag of the game
	private Pile[] piles;
	private final MiddlePile middlePile;
	private final Bag bag;

	//reference of the game, used for communication
	private final Game gameRef;
	
	private final int numberOfPiles;
	
	public Pot(int numberOfPlayers, Game ref) {
		
		gameRef = ref;
		
		middlePile = new MiddlePile(this);
		
		//determine the number of Piles depending on the number of players
		numberOfPiles = 1 + numberOfPlayers * 2;
		
		instanciatePiles();
		
		bag = new Bag(piles);
		
	
	}
	
	private void instanciatePiles() {
		piles = new Pile[numberOfPiles];
		for(int i = 0; i < numberOfPiles; i++){
			piles[i] = new Pile(gameRef, middlePile,  i);
		}
	}
	
	//called by game to see if Piles or MiddlePile are empty
	public boolean isPlayNotPossible() {
		
		//used to track the number of elements empty
		int empty = 0;
		
		for(Pile p: piles) empty += p.isEmpty();
		
		empty+= middlePile.isEmpty();
		
		return empty == this.numberOfPiles + 1;
		
	}
	
	//methods used to transfer instructions
	
	public void distributeContents() {
		this.bag.distributeContents();
	}
	
	public void setTilesSelectedToHand(int numberOfPile, int ID) {
		piles[numberOfPile].setTilesSelectedToHand(ID);
	}

	public void sendAddedTilesToView(LinkedList<Tile> toAdd, int previousIndex, boolean delete) {
		this.gameRef.updateMiddlePileView(toAdd, previousIndex, delete);
	}
	
	public void sendCompleteMiddlePileToView(boolean bool) {
		middlePile.sendCompletePileToView(bool);
	}
	
	public LinkedList<Tile> modifyMiddlePile(int index) {
		return middlePile.modifyMiddlePile(index);
	}

	
	//methods used to send Tiles 
	
	public void sendToBag(Tile p) {
		bag.getTilesBack(p);
		
	}
	
	public void sendToBag(LinkedList<Tile> tiles) {
		bag.getTilesBack(tiles);
	}
	


	public void sendMalusFirst(Tile first) {
		gameRef.sendMalusFirst(first);
		
	}
	
	public void setFirst() {
		middlePile.setFirst();
	}
	
	

	
}
