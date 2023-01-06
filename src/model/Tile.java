package model;

//Tiles used in Azul
public class Tile {
	
	// Color of the Tile
	private ColorEnum colorEnum;
	
	// Check if the Tile is occupied or not, used for the Pattern
	private boolean occupied;
	
	
	public Tile(ColorEnum color){
		this.occupied = false;
		this.colorEnum = color;
	}
	
	public ColorEnum getColorEnum() {
		return this.colorEnum;
	}

	public void setOccupiedTrue() {
		this.occupied = true;
	}
	
	public boolean getOccupied() {
		return this.occupied;
	}
}
