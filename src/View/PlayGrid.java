package View;

import java.awt.*;
import java.util.LinkedList;

import model.Tile;

public class PlayGrid {
	private static final int RECT_SIZE = Bord.RECT_SIZE;
	private Position position;
	private Line[] lines;

  public PlayGrid(Position position, View view_ref) {
	  this.position = position;
	  this.lines = new Line[5];

	  for (int i = 0; i < 5; i++) {
		  lines[i] = new Line(new Position(position.getX(), position.getY() + i * RECT_SIZE), i + 1, view_ref);
	  }
  }
  
  public Line[] getLines() {
	  return lines;
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

  public void updateViewLine(LinkedList<Tile> to_send, int previous_index, int i) {
	  lines[i].updateViewLine(to_send, previous_index);
	
  }
}


