package View;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.JButton;

import controller.ActionLine;
import model.Tile;

public class PlayGrid {
	private Position position;
	private View view_m;
	private Line[] lines;

  public PlayGrid(Position position, View view_ref, int RECT_SIZE) {
	  this.position = position;
	  this.view_m = view_ref;
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
	  lines[i].updateViewLine(to_send, previous_index, view_m);
	
  }
  
  public JButton[] getPileButtons() {
	  int ID = 0;
      JButton[] buttons = new JButton[5];
      int count = 0;
      for (Line line : lines) {
          buttons[count] = line.getButton();
          // Adding an ActionListener for each button of this popup
          // Every button possess an ID, which corresponds to the line of the grid
          ActionLine action = view_m.actionLine(ID);
          buttons[count].addActionListener(action);
          count++;
          ID++;
          System.out.println(count);
      }
      return buttons;
  }
}


