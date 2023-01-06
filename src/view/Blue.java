package view;

import model.ColorEnum;

public class Blue extends Tile_View {

	public Blue(Position position) {
		super(position, "Algorithm.png", ColorEnum.B, false);
	}
	
	public Blue(Position position, boolean PopUp) {
		super(position, "Algorithm.png", ColorEnum.B, PopUp);
	}
}