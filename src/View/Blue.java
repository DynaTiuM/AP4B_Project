package View;

import javax.swing.ImageIcon;

public class Blue extends Tile {
  public Blue(Position position) {
	super(position);
    this.texture = new ImageIcon("src\\Images\\Algorithm.png").getImage();
  }
}