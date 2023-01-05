package View;

import javax.swing.ImageIcon;

import model.ColorEnum;

public class Purple extends Tile_View {

	public Purple(Position position) {
		super(position, new ImageIcon("src\\Images\\Hardware.png"), ColorEnum.M, false);
		//this.texture = new ImageIcon("src\\Images\\Hardware.png");
	}
	
	public Purple(Position position, boolean PopUp) {
		super(position, new ImageIcon("src\\Images\\Hardware.png"), ColorEnum.M, PopUp);
		//this.texture = new ImageIcon("src\\Images\\Hardware.png");
	}
}