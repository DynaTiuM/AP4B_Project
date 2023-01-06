package model;



public class Tile {
	
	// Couleur associé à la "Tile"
	private ColorEnum color_enum_m;
	
	
	// Booléen indiquant si la "Tile" est occupée ou non
	private boolean occupied;
	
	
	// Construteur de "Tile" avec un objet Color en paramètre
	public Tile(ColorEnum color_p){
		this.occupied = false;
		this.color_enum_m = color_p;
	}
	
	// renvoie la couleur selon l'alias défini par la classe ColorEnum
	public ColorEnum getColorEnum() {
		return this.color_enum_m;
	}
	
	
	
	// change l'attribut occupied
	public void setOccupiedTrue() {
		this.occupied = true;
	}
	
	// renvoie la valeur de occupied
	public boolean getOccupied() {
		return this.occupied;
	}
}
