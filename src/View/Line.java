package View;

import java.awt.*;
import javax.swing.*;

public class Line {
	private int RECT_SIZE;
  private Position position;
  private int length;
  private JButton button;

  public Line(Position position, int length, int RECT_SIZE) {
	  this.RECT_SIZE = RECT_SIZE;
    this.position = position;
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

}