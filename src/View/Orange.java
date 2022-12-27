package View;

import javax.swing.ImageIcon;

public class Orange extends Tile_View {
  public Orange(Position position) {
	  super(position);
    this.texture = new ImageIcon("src\\Images\\Computer.png").getImage();
  }
}