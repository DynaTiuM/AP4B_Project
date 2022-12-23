package model;

import java.awt.Color;

public class Tile {
	
	private ColorEnum color_enum_m;
	private Color color_m;
	private boolean occupied;
	
	public Tile() {
		this.occupied = false;
	}
	
	public Tile(Color color_p){
		this.occupied = false;
		setColor(color_p);
		
	}
	
	public ColorEnum getColorEnum() {
		return this.color_enum_m;
	}
	
	public void setColor(Color color_p) {
		this.color_m = color_p;
		
		if(color_p == Color.BLUE) {
			color_enum_m = ColorEnum.Bl;
		}else if (color_p == Color.BLACK) {
			color_enum_m = ColorEnum.B;
		}else if (color_p == Color.ORANGE) {
			color_enum_m = ColorEnum.O;
		}else if (color_p == Color.WHITE) {
			color_enum_m = ColorEnum.W;
		}else if (color_p == Color.RED) {
			color_enum_m = ColorEnum.R;
		}
		
	}
	
	public void setColorEnum(ColorEnum color_p) {
		this.color_enum_m = color_p;
	}
	
	public Color getColor() {
		return this.color_m;
	}
	
	public void setOccupiedTrue() {
		this.occupied = true;
	}
	
	public void setOccupiedFalse() {
		this.occupied = false;
	}
	
	public boolean getOccupied() {
		return this.occupied;
	}
}
