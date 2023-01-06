package view;

import model.ColorEnum;

public class Green extends Tile_View {

	public Green(Position position) {
		super(position,"Network.png", ColorEnum.G, false);
  	}
	public Green(Position position, boolean PopUp) {
		super(position, "Network.png", ColorEnum.G, PopUp);
	}
}