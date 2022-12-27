package View;

import java.awt.*;

public class PlayGrid {
  private int RECT_SIZE;
  private Position position;
  private Line[] lines;

  public PlayGrid(Position position, int RECT_SIZE) {
	  this.RECT_SIZE = RECT_SIZE;
    this.position = position;
    this.lines = new Line[5];

    for (int i = 0; i < 5; i++) {
      lines[i] = new Line(new Position(position.getX(), position.getY() + i * RECT_SIZE), i + 1, RECT_SIZE);
    }
  }

  public void draw(Graphics g) {
    for (Line line : lines) {
      line.draw(g);
    }
  }
  
  public void setButton(boolean value) {
	  for(Line line : lines) {
		  line.setButton(value);
	  }
  }
}


