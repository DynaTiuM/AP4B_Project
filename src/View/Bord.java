package View;

import java.awt.*;

import javax.swing.ImageIcon;


public class Bord{
	  public static final int BORD_SIZE = 300;
	  public static final int RECT_SIZE = 25;
	  private Position position;
	  private PlayGrid playGrid;
	  private Pattern pattern;
	  private Malus malus;

	  public Bord(Position position) {
	    this.position = position;
	    this.playGrid = new PlayGrid(new Position(position.getX() + RECT_SIZE, position.getY() + RECT_SIZE*4), RECT_SIZE);
	    this.pattern = new Pattern(new Position(position.getX() + 10 + BORD_SIZE / 2, position.getY()+ RECT_SIZE*4), RECT_SIZE);
	    this.malus = new Malus(new Position(position.getX() + 10, position.getY() + BORD_SIZE - RECT_SIZE * 2 - 10 ), RECT_SIZE);
	    this.setButtons(true);
	  }

	  public void draw(Graphics g) {
	    ImageIcon icon = new ImageIcon("src\\Images\\Bord.png");
	    if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
	      // There was an error loading the image
	    	System.out.println("error");
	    } else {
	      // The image was successfully loaded
	      Image bord = icon.getImage();
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
	  
	  public void setPosition(Position position) {
		  this.position = position;
	  }
	  

	}

