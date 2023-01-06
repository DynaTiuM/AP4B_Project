package View;

import javax.swing.ImageIcon;

import model.ColorEnum;

public class Blue extends Tile_View {

	public Blue(Position position) {
		super(position, "..\\Images\\Algorithm.png", ColorEnum.B, false);
	}
	
	public Blue(Position position, boolean PopUp) {
		super(position, "..\\Images\\Algorithm.png", ColorEnum.B, PopUp);
	}
}