package model;

//Tiles used in Azul
public class Tile {
	
	// Color of the Tile
	private ColorEnum colorEnum;
	
	// Check if the Tile is occupied or not, used for the Pattern
	private boolean occupied;
	
	
	public Tile(ColorEnum color_p){
		this.occupied = false;
		this.colorEnum = color_p;
	}
	
	public ColorEnum getColorEnum() {
		return this.colorEnum;
	}

	public void setColorEnum(ColorEnum color) {
		this.colorEnum = color;
	}

	public void setOccupiedTrue() {
		this.occupied = true;
	}
	
	public boolean getOccupied() {
		return this.occupied;
	}
}
