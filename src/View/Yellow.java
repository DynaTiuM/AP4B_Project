package View;

import javax.swing.ImageIcon;

import model.ColorEnum;

public class Yellow extends Tile_View {
  /**
	 * 
	 */
	private static final long serialVersionUID = 669572170038317053L;

public Yellow(Position position) {
	  
	  super(position, new ImageIcon("src\\Images\\Database.jpg"), ColorEnum.Y);
	  //this.texture = new ImageIcon("src\\Images\\Database.jpg");
  }
}