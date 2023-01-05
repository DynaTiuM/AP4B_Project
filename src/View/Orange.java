package View;

import javax.swing.ImageIcon;

import model.ColorEnum;

public class Orange extends Tile_View {
	
	public Orange(Position position) {
		super(position, new ImageIcon("src\\Images\\Computer.png"), ColorEnum.O, false);
		//this.texture = new ImageIcon("src\\Images\\Computer.png");
	}
	
	public Orange(Position position, boolean PopUp) {
		super(position, new ImageIcon("src\\Images\\Computer.png"), ColorEnum.O, PopUp);
		//this.texture = new ImageIcon("src\\Images\\Computer.png");
	}
}