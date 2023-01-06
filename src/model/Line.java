package model;

import java.util.Arrays;
import java.util.LinkedList;


// Class representing one of the Lines where a player is able to play Tiles
public class Line {

    // Contains the Tiles
	private Tile[] lineArray;
	
    //Allows us to know about the progress of the Line
	private int currentIndex;
	private int previousIndex;
	
    // Size of Line
	private int size;

    // Allows us to know if a color has already been placed in this line, thus can't be placed here again
	private final boolean[] colorPresence;

    // The color of Tiles still present in the line
	private ColorEnum currentColor;
	
    // Reference to the Malus Grid of the player
	private final Malus malusRef;
	
    // Reference to the Pattern Grid of the player
	private final Pattern patternRef;
		
	// 
	private final LinkedList<Tile> toSend;
	
	private final Bord bordRef;
	private final int length;
	
	
	// Constructeur de la classe Line
	public Line(Malus malus, Pattern patternRef, int size, Bord ref) {
		
		this.bordRef = ref;
		
		this.malusRef = malus;
		
		this.patternRef = patternRef;
		
		currentIndex = 0;
		previousIndex = 0;
		
		colorPresence = new boolean[5];
		
		currentColor = null;
		this.length = size;
		
		toSend = new LinkedList<>();
		
		for(int i = 0; i<5;i++) {
			colorPresence[i]=false;
		}
		
		// initialise la "Line"
		initArray(size);
		
	}
	
	public int getLength() {
		return this.length;
	}
	
	// initialise la "Line"
	private void initArray(int index) {
		lineArray = new Tile[index];
		size = index;
	}

	// Sets hand of Player on the Line
	public void addChoice(LinkedList<Tile> tiles) {
		
		int previousMalus = malusRef.getPrevious();
		
		//checks if the Malus grid of the player has been modified by the current function
		boolean modified = false;
		
		int i = currentIndex;
		for(Tile p: tiles) {
			if(i < size) {
				setTileIndex(i, p);
				i++;
			}else { // If the line is full, tiles will be sent to the Malus Grid
			
				
				malusRef.addTile(p);
				modified = true;
				
			}
		}
		
		previousIndex = currentIndex;

		currentIndex = i;

		malusRef.setPrevious(previousMalus);
		
		//updates the view
		updateViewLine(modified);
	}
	
	//updates the view
	private void updateViewLine(boolean modified) {
		LinkedList<Tile> toSend = new LinkedList<>(Arrays.asList(lineArray).subList(previousIndex, currentIndex));

		this.bordRef.updateViewLine(toSend, previousIndex, this.size - 1, modified);
	}
	
	// check if the Line is already full
	public boolean checkFull() {
		return currentIndex == size;
	}

	// checks if it is possible to place a tile in the current Line
	// it is possible to place a Tile, if the color of the Tile is the same as the current_color or if we haven't played a Tile of this color on this Line
	public boolean isPossible(Tile tile) {
		return !checkColor(tile) && (tile.getColorEnum() == currentColor || currentColor == null);
	}

	public boolean isAlreadyOnPattern(Tile tile){
		return patternRef.isAlreadyOnPattern(size, tile);
	}
	
	// check if color has already been placed here before
	public boolean checkColor(Tile tile) {
		return colorPresence[tile.getColorEnum().ordinal()];
	}

	// Puts Tile to given Index
	public void setTileIndex(int index, Tile tile){
		if(index < size) {
			currentColor = tile.getColorEnum();
			lineArray[index] = tile;
		} else {
			malusRef.addTile(tile);
		}
	}

	
	// Gets content of the Line
	public Tile[] getTiles() {
		return this.lineArray;
	}
	
	public Tile[] getLine() {
		return this.lineArray;
	}
	
	
	// Displays the Line
	public void display() {
		int i =0;
		while(i<this.currentIndex) {
			System.out.print(lineArray[i].getColorEnum() + " ");
			i++;
		}
		
		while(i<size) {
			System.out.print("- ");
			i++;
		}
		
		System.out.println();
	}
	
	
	// function called only if the line is full, clears the line
	public LinkedList<Tile> clear() {
		
		toSend.clear();
		
		//takes first Tile of the line and sends it to the Pattern of the player
		patternRef.determineSendingPlace(size - 1, lineArray[0]);
		
		lineArray[0] = null;
		
		
		//prepares the rest to be sent to the Bag
		for(int i =1; i<size; i++) {
			toSend.add(lineArray[i]);
			lineArray[i] = null;
		}
		//reset the current color
		currentColor = null;
		
		currentIndex = 0;
		
		return toSend;
	}

}
