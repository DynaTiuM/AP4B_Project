package view;

import model.ColorEnum;

public class MalusTile extends Tile_View {
	public MalusTile(Position position) {
		super(position, "MalusTile.png", ColorEnum.MALUS, false);
  	}

	public MalusTile(Position position, boolean PopUp) {
		super(position, "MalusTile.png", ColorEnum.MALUS, PopUp);
	}
}