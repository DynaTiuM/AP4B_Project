package View;

import javax.swing.ImageIcon;

import model.ColorEnum;

public class MalusTile extends Tile_View {
	public MalusTile(Position position) {
		super(position, new ImageIcon("src\\Images\\MalusTile.png"), ColorEnum.MALUS, false);
		
  	}
	
	
	public MalusTile(Position position, boolean PopUp) {
		super(position, new ImageIcon("src\\Images\\MalusTile.png"), ColorEnum.MALUS, PopUp);
		//this.texture = new ImageIcon("src\\Images\\Database.jpg");
	}
}