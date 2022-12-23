package View;

import java.awt.Graphics;
import javax.swing.JButton;

public class MiddlePile {
	private static final int RECT_SIZE = Bord.RECT_SIZE;
  private Position position;
  private JButton button;

  public MiddlePile(Position position) {
    this.position = position;
    this.button = new JButton();
  }

  public void draw(Graphics g) {
    // Draw the pile at the specified position
    g.drawRect(position.getX(), position.getY(), RECT_SIZE, RECT_SIZE);
  }

  public void setButton(boolean value) {
    button.setVisible(value);
  }
}