package View;

import javax.swing.ImageIcon;

import model.ColorEnum;

public class MalusTile extends Tile_View {
	public MalusTile(Position position) {
		super(position, "..\\Images\\MalusTile.png", ColorEnum.MALUS, false);
  	}

	public MalusTile(Position position, boolean PopUp) {
		super(position, "..\\Images\\MalusTile.png", ColorEnum.MALUS, PopUp);
	}
}