package view;

import model.ColorEnum;

public class Orange extends Tile_View {
	
	public Orange(Position position) {
		super(position, "Computer.png", ColorEnum.O, false);
	}
	
	public Orange(Position position, boolean PopUp) {
		super(position,"Computer.png", ColorEnum.O, PopUp);
	}
}