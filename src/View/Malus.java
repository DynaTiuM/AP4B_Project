package View;

import java.awt.*;
import javax.swing.*;

public class Malus {
	private static final int RECT_SIZE = Bord.RECT_SIZE;
  private Position position;
  private JButton button;

  public Malus(Position position) {
    this.position = position;
    this.button = new JButton();
  }

  public void draw(Graphics g) {
    // Afficher les cases numérotées -1
    g.drawString("-1", position.getX(), position.getY());
    g.drawString("-1", position.getX() + RECT_SIZE, position.getY());
    // Afficher les cases numérotées -2
    g.drawString("-2", position.getX() + RECT_SIZE * 2, position.getY());
    g.drawString("-2", position.getX() + RECT_SIZE * 3, position.getY());
    g.drawString("-2", position.getX() + RECT_SIZE * 4, position.getY());
    // Afficher les cases numérotées -3
    g.drawString("-3", position.getX() + RECT_SIZE * 5, position.getY());
    g.drawString("-3", position.getX() + RECT_SIZE * 6, position.getY());

    ImageIcon icon = new ImageIcon("src\\Images\\Malus.png");
    if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
      // There was an error loading the image
    } else {
      // The image was successfully loaded
      Image malus = icon.getImage();
      for(int i = 0; i < 6; i++) {
        g.drawImage(malus, position.getX() + (int)(RECT_SIZE *1.5*i), position.getY(), (int)(RECT_SIZE*1.5), (int) RECT_SIZE*2, null);
      }
      }
    }

  public void setButton(boolean value) {
    button.setVisible(value);
  }


}