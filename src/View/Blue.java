package View;

import javax.swing.ImageIcon;

import model.ColorEnum;

public class Blue extends Tile_View {

	public Blue(Position position) {
		super(position, new ImageIcon("src\\Images\\Algorithm.png"), ColorEnum.B, false);
		//this.texture = new ImageIcon("src\\Images\\Algorithm.png");
	}
	
	public Blue(Position position, boolean PopUp) {
		super(position, new ImageIcon("src\\Images\\Algorithm.png"), ColorEnum.B, PopUp);
		//this.texture = new ImageIcon("src\\Images\\Algorithm.png");
	}
}