package model;


import java.awt.*;

public class Tile {
	
	// Couleur associé à la "Tile"
	private ColorEnum colorEnum;
	
	// Booléen indiquant si la "Tile" est occupée ou non
	private boolean occupied;
	
	
	// Construteur de "Tile" avec un objet Color en paramètre

	public Tile(ColorEnum color_p){
		this.occupied = false;
		this.colorEnum = color_p;
	}
	
	// renvoie la couleur selon l'alias défini par la classe ColorEnum
	public ColorEnum getColorEnum() {
		return this.colorEnum;
	}

	// change la couleur de la tuile (ou l'initialise)
	public void setColorEnum(ColorEnum color) {
		this.colorEnum = color;
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
