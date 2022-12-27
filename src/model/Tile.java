package model;

import java.awt.Color;



public class Tile {
	
	// Couleur associé à la "Tile"
	private ColorEnum color_enum_m;
	private Color color_m;
	
	// Booléen indiquant si la "Tile" est occupée ou non
	private boolean occupied;
	
	
	// Construteur de "Tile", initialise occupied à false
	public Tile() {
		this.occupied = false;
	}
	
	// Construteur de "Tile" avec un objet Color en paramètre
	public Tile(Color color_p){
		this.occupied = false;
		setColor(color_p);	
	}
	
	// renvoie la couleur selon l'alias défini par la classe ColorEnum
	public ColorEnum getColorEnum() {
		return this.color_enum_m;
	}
	
	// change la couleur de la tuile (ou l'initialise)
	public void setColorEnum(ColorEnum color_p) {
		this.color_enum_m = color_p;
	}
	
	// renvoie la couleur de la tuile 
	public Color getColor() {
		return this.color_m;
	}
	
	// change la couleur de la tuile (ou l'initialise) et appel le setColorEnum correspondant
	public void setColor(Color color_p) {
		this.color_m = color_p;
		
		if(color_p == Color.BLUE) {
			setColorEnum(ColorEnum.Bl);
		}else if (color_p == Color.BLACK) {
			setColorEnum(ColorEnum.B);
		}else if (color_p == Color.ORANGE) {
			setColorEnum(ColorEnum.O);
		}else if (color_p == Color.WHITE) {
			setColorEnum(ColorEnum.W);
		}else if (color_p == Color.RED) {
			setColorEnum(ColorEnum.R);
		}
	}

	
	// change l'attribut occupied
	public void setOccupiedTrue() {
		this.occupied = true;
	}
	public void setOccupiedFalse() {
		this.occupied = false;
	}
	
	// renvoie la valeur de occupied
	public boolean getOccupied() {
		return this.occupied;
	}
}
