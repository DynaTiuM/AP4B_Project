package View;

import javax.swing.ImageIcon;

public class MalusTile extends Tile {
	public MalusTile(Position position) {
		super(position);
		this.texture = new ImageIcon("src\\Images\\MalusTile.png").getImage();
  	}
}