package View;

import javax.swing.ImageIcon;

public class Green extends Tile_View {
  public Green(Position position) {
	  super(position);
    this.texture = new ImageIcon("src\\Images\\Network.png").getImage();
  }
}