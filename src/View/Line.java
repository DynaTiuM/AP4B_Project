package View;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.*;

import model.Tile;

public class Line {
	private static final int RECT_SIZE = Bord.RECT_SIZE;
	private Position position;
	private int length;
	private JButton button;
  
	private Tile_View[] tiles;
  
	private View view_m;

	public Line(Position position, int length, View view_ref) {
	  
		view_m = view_ref;  
		
	    this.position = position;
	    tiles = new Tile_View[length];
	    this.length = length;
	    this.button = new JButton();
  }

	public void draw(Graphics g) {
		ImageIcon icon = new ImageIcon("src\\Images\\Cube.png");
		if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
	        // There was an error loading the image
	    } else {
	       // The image was successfully loaded
	    	Image rect = icon.getImage();
	    	for (int i = 0; i < length; i++) {
	    		g.drawImage(rect, position.getX() + (4 - i) * RECT_SIZE, position.getY(), RECT_SIZE, RECT_SIZE, null);
	            
	        }
	    }
	}

	public void setButton(boolean value) {
		button.setVisible(value);
	}

	public void updateViewLine(LinkedList<Tile> to_send, int previous_index) {
		int temp = previous_index;
	
		for(Tile p: to_send) {
		  switch (p.getColorEnum()){
		  case O: tiles[temp] = new Orange(new Position(position.getX() + (4 - temp) * (RECT_SIZE ), position.getY())); 
		  	break;
		  case B: tiles[temp] = new Purple(new Position(position.getX() +  (4 - temp) * (RECT_SIZE), position.getY()));
		  	break;
		  case Bl: tiles[temp] = new Blue(new Position(position.getX() + (4 - temp) * (RECT_SIZE), position.getY()));
		  	break;
		  case Y: tiles[temp] = new Yellow(new Position(position.getX() + (4 - temp) * (RECT_SIZE), position.getY()));
		  	break;
		  case G: tiles[temp] = new Green(new Position(position.getX() + (4 - temp) * (RECT_SIZE), position.getY()));
			  break;
		  }
		  System.out.print(p.getColorEnum() + " / ");
		  view_m.getPanel().addT(tiles[temp]);
		 
		  temp++;
	}
	
}
  
  

}