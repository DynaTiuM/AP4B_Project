package view;

import model.ColorEnum;

public class Purple extends Tile_View {

	public Purple(Position position) {
		super(position, "Hardware.png", ColorEnum.M, false);
	}
	
	public Purple(Position position, boolean PopUp) {
		super(position, "Hardware.png", ColorEnum.M, PopUp);
	}
}