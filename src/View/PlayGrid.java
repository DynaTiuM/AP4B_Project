package View;

import java.awt.*;

public class PlayGrid {
  private static final int RECT_SIZE = Bord.RECT_SIZE;
  private Position position;
  private Line[] lines;

  public PlayGrid(Position position) {
    this.position = position;
    this.lines = new Line[5];

    for (int i = 0; i < 5; i++) {
      lines[i] = new Line(new Position(position.getX(), position.getY() + i * RECT_SIZE), i + 1);
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


