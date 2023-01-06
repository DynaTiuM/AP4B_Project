package model;

import java.util.HashMap;
import java.util.LinkedList;

import view.Position;




//Bord of a Player
//Each bord contains 5 Lines, a Malus grid, and a Pattern grid
public class Bord {

	private final Line[] playGrid;
	private final Malus malusGrid;
	private final Pattern patternGrid;

	// Reference to the game, used for communication
	private final Game gameRef;
	
	// Used to allow Game to identify the player
	private final int playerID;

	//Tiles the player has currently selected, use to store Tiles before setting them on a line or in the Malus grid
	private LinkedList<Tile> playerHand;
	
	//allow game to know if this player has taken the Malus tile from MiddlePile
	private boolean nextFirstPlayer;
	
	
	public Bord(int number, Game ref) {

		nextFirstPlayer = false;
		
		playerID = number;
		
		//sets reference
		gameRef = ref;
		
		malusGrid = new Malus(this);
		patternGrid = new Pattern(this);
		
		playGrid = new Line[5];
		for(int i=0; i<5; i++) {
			playGrid[i] = new Line(malusGrid, patternGrid, i+1, this);
		}

		this.playerHand = new LinkedList<>();
	}
	
	


	

	// plays hand contained in hand of player to the chosen line
	public void playHandIndex(int index) {
		playGrid[index].addChoice(playerHand);
		this.gameRef.nextPlayer();
	}
	
	//check if the player has filled end of game conditions
	public boolean checkEnd(){
		return patternGrid.checkEndGame();
	}
	
	// fonction called at each end of round
	public void endOfSet() {
		boolean update = false;
		// if a line is filled, will empty the line
		for(Line line: playGrid) {
			if(line.checkFull()) {
				gameRef.sendToBag(line.clear());

				update = true;
			}
		}
		
		// computates malus, and trigger method emptying it
		if(!this.malusGrid.isEmpty()) {
			patternGrid.scoreMalus(malusGrid.computateMalus());
			gameRef.sendToBag(malusGrid.clear());
		}

		//if we emptied a line, the pattern grid needs to be emptied on the view
		if(update) {
			patternGrid.sendPattern();
		}
		
		
		this.gameRef.clearMalusView(playerID);

	}
	


	public void sendToBag(Tile p) {
		gameRef.sendToBag(p);
	}


	public void updateViewLine(LinkedList<Tile> toSend, int previousIndex, int i, boolean modified) {
	
		if(modified) {
			this.gameRef.updateViewLine(toSend, previousIndex, i, malusGrid.getLine());
		}else {
			this.gameRef.updateViewLine(toSend, previousIndex, i);
		}
		
	}

	public void updatePatternView(HashMap<Tile, Position> toSend) {
		this.gameRef.updatePatternView(this.playerID, toSend);
	}
	
	
	

	public void calculateEndOfGameBonuses() {
		patternGrid.calculateEndOfGameBonuses();
	}
	


	//called if a player was the first to choose a Tile in the MiddlePile

	public void sendMalusFirst(Tile first) {
		nextFirstPlayer = true;
		malusGrid.addTile(first);
		gameRef.sendMalusFirstToView(malusGrid.getPrevious());
	}
	
	//called when a player choose to directly put tiles in his Malus grid
	public void updateMalus() {
		
		//sets his hand in the malus grid
		this.malusGrid.addTile(playerHand);
		
		//updates the view
		this.gameRef.updateMalusToView(malusGrid.getLine());
		
		//pass to the next player
		this.gameRef.nextPlayer();
	}

	
	//Sets the selection in the hand of the player
	public void setHand(LinkedList<Tile> tiles) {
		playerHand.clear();
		playerHand = tiles;
	}
	
	public LinkedList<Tile> getHand() {
		return playerHand;
	}
	
	public Line[] getLines() {
		return playGrid;
	}
	
	public Tile[] getMalus() {
		return malusGrid.getLine();
	}


	public boolean getNextFirst() {
		
		return nextFirstPlayer;
	}
	
	//if player was first, reset the value
	
	public void resetNextFirst() {
		nextFirstPlayer = false;
	}
	
	
	// send pattern to the view
	public Tile[][] getPatternToView() {

		return patternGrid.getGrid();
	}
	
	public int getScore() {
		return this.patternGrid.getScore();
	}

	public int getID() {

		return this.playerID;
	}
	
	
	// displays the hand of the player
		public void displayHand() {
			System.out.print("Hand : ");
			for(Tile p: playerHand) System.out.print(p.getColorEnum() + " ");
			System.out.println();
			
		}
		
		// display the lines of the bord, as well as the Malus grid
		public void display() {
			for(Line p: playGrid) p.display();
			malusGrid.display();

		}
	
}
