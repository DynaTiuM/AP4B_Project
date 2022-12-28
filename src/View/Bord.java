package View;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import model.Tile;


public class Bord{
	  
	  public static final int BORD_SIZE = 300;
	  public static final int RECT_SIZE = 25;
	  private Position position;
	  private PlayGrid playGrid;
	  private Pattern pattern;
	  private Malus malus;

	  public Bord(Position position, View view_ref) {
	    this.position = position;
	    this.playGrid = new PlayGrid(new Position(position.getX() + 10, position.getY() + RECT_SIZE*4), view_ref);
	    this.pattern = new Pattern(new Position(position.getX() + 10 + BORD_SIZE / 2, position.getY()+ RECT_SIZE*4));
	    this.malus = new Malus(new Position(position.getX() + 10, position.getY() + BORD_SIZE - 60), view_ref);
	  }

	  public void draw(Graphics g) {
	    ImageIcon icon = new ImageIcon("src\\Images\\Bord.png");
	    if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
	      // There was an error loading the image
	    } else {
	      // The image was successfully loaded
	      Image bord = icon.getImage();
	      //Pourquoi 6 ?
	      for(int i = 0; i < 6; i++) {
	    	  g.drawImage(bord, position.getX(), position.getY(), BORD_SIZE, BORD_SIZE, null);
	      }
	    }
	    malus.draw(g);
	    playGrid.draw(g);
	    pattern.draw(g);
	  }

	  public void setButtons(boolean visible) {
	    malus.setButton(visible);
	    playGrid.setButton(visible);
	  }

	public void updateViewLine(LinkedList<Tile> to_send, int previous_index, int i, LinkedList<Tile> linkedList, int previous_index_2) {
		playGrid.updateViewLine(to_send, previous_index, i);
		malus.updateViewLine(linkedList, previous_index_2);
		
	}
	}

