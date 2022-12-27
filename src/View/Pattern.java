package View;

import java.awt.*;

import javax.swing.ImageIcon;

public class Pattern {
	private int RECT_SIZE;
  private Position position;

  public Pattern(Position position, int RECT_SIZE) {
	  this.RECT_SIZE = RECT_SIZE;
    this.position = position;
  }

  public void draw(Graphics g) {  	  
	ImageIcon icon = new ImageIcon("src\\Images\\Pattern.png");
    if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
      // There was an error loading the image
    } else {
      // The image was successfully loaded
      Image pattern = icon.getImage();
      for(int i = 0; i < 6; i++) {
    		g.drawImage(pattern, position.getX(), position.getY(), RECT_SIZE*5, RECT_SIZE*5, null);
      }
      }

  }
}
