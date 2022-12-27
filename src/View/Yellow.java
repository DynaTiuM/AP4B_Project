package View;

import javax.swing.ImageIcon;

public class Yellow extends Tile_View {
  public Yellow(Position position) {
	super(position);
    this.texture = new ImageIcon("src\\Images\\Database.jpg").getImage();
  }
}