package View;

import javax.swing.ImageIcon;

public class Purple extends Tile_View {
  public Purple(Position position) {
	  super(position);
    this.texture = new ImageIcon("src\\Images\\Hardware.png").getImage();
  }
}