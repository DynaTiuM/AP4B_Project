package View;

import javax.swing.ImageIcon;

import model.ColorEnum;

public class Green extends Tile_View {

	public Green(Position position) {
		super(position, new ImageIcon("src\\Images\\Network.png"), ColorEnum.G, false);
  	}
	public Green(Position position, boolean PopUp) {
		super(position, new ImageIcon("src\\Images\\Network.png"), ColorEnum.G, PopUp);
	}
}