package View;

import javax.swing.ImageIcon;

import model.ColorEnum;

public class Purple extends Tile_View {
  /**
	 * 
	 */
	private static final long serialVersionUID = -838698759961229514L;

public Purple(Position position) {
	  super(position, new ImageIcon("src\\Images\\Hardware.png"), ColorEnum.M);
    //this.texture = new ImageIcon("src\\Images\\Hardware.png");
  }
}