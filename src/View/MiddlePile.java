package View;

import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JButton;

import model.Tile;

public class MiddlePile {
	private static final int RECT_SIZE = Bord.RECT_SIZE;
	private int offsetY = 0;
	private Position position;
	private JButton button;
	private Pot pot_m;

  public MiddlePile(Pot ref, Position position) {
    this.position = position;
    this.button = new JButton();
    pot_m = ref;
  }

  int yPosition = 0;
  
  public void draw(Graphics g) {
    // Draw the pile at the specified position
	for (int i = 0; i < 28; i++) {
		
		if (i != 0 && i % 7 == 0) {
			yPosition += RECT_SIZE;
		}
		
		g.drawRect(position.getX() + RECT_SIZE * (i % 7), position.getY() + yPosition, RECT_SIZE, RECT_SIZE);
	}
}

  	public void setButton(boolean value) {
	    button.setVisible(value);
	}
  
  	public void updatePile(LinkedList<Tile> to_add, int previous_index) {

  		Tile_View tile = null;
  		int offsetX = previous_index * RECT_SIZE;
  		
  		if ((offsetX % 7) / RECT_SIZE == 0) {
			this.offsetY += RECT_SIZE;
  		}
	  
	  for(Tile p: to_add) {
		  switch (p.getColorEnum()){
		  case O: tile = new Orange(new Position(position.getX() + offsetX, position.getY() + this.offsetY)); 
		  	break;
		  case B: tile = new Purple(new Position(position.getX() + offsetX, position.getY() + this.offsetY));
		  	break;
		  case Bl: tile = new Blue(new Position(position.getX() + offsetX, position.getY() + this.offsetY));
		  	break;
		  case Y: tile = new Yellow(new Position(position.getX() + offsetX, position.getY() + this.offsetY));
		  	break;
		  case G: tile = new Green(new Position(position.getX() + offsetX, position.getY() + this.offsetY));
			break;
		  }
		  pot_m.addT(tile);
		  offsetX += RECT_SIZE;
	  }
	  tile = null;
  }
}