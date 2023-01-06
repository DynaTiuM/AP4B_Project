package View;

import javax.swing.ImageIcon;

import model.ColorEnum;

public class Purple extends Tile_View {

	public Purple(Position position) {
		super(position, "..\\Images\\Hardware.png", ColorEnum.M, false);
	}
	
	public Purple(Position position, boolean PopUp) {
		super(position, "..\\Images\\Hardware.png", ColorEnum.M, PopUp);
	}
}