package View;

import javax.swing.ImageIcon;

public class Yellow extends Tile {
  public Yellow(Position position) {
	super(position);
    this.texture = new ImageIcon("src\\Images\\Database.jpg").getImage();
  }
}