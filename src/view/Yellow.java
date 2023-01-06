package view;

import model.ColorEnum;

public class Yellow extends Tile_View {

	public Yellow(Position position) {
		super(position, "Database.png", ColorEnum.Y, false);
	}
	
	public Yellow(Position position, boolean PopUp) {
		super(position, "Database.png", ColorEnum.Y, PopUp);
	}
}