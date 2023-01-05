package View;

import javax.swing.ImageIcon;

import model.ColorEnum;

public class Yellow extends Tile_View {

	public Yellow(Position position) {
		super(position, new ImageIcon("src\\Images\\Database.png"), ColorEnum.Y, false);
	}
	
	public Yellow(Position position, boolean PopUp) {
		super(position, new ImageIcon("src\\Images\\Database.png"), ColorEnum.Y, PopUp);
	}
}