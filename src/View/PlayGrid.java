package View;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.JButton;

import model.Tile;

public class PlayGrid {
	private Position position;
	private View view_ref;
	private Line[] lines;

  public PlayGrid(Position position, View view_ref, int RECT_SIZE) {
	  this.position = position;
	  this.view_ref = view_ref;
	  this.lines = new Line[5];

	  for (int i = 0; i < 5; i++) {
		  lines[i] = new Line(new Position(position.getX(), position.getY() + i * RECT_SIZE), i + 1, view_ref, RECT_SIZE);
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
	  lines[i].updateViewLine(to_send, previous_index, view_ref);
	
  }
  
  public JButton[] getPileButtons() {
      JButton[] buttons = new JButton[5];
      int count = 0;
      for (Line line : lines) {
          buttons[count] = line.getButton();
          count++;
      }
      return buttons;
  }
}


