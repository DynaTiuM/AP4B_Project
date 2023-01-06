package view;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.JButton;

import controller.ActionLine;
import model.Tile;

//Class displaying the PlayGrid in the ViewPanel
public class PlayGrid {
	
	//Reference to the view
	private final View viewRef;
	
	//Array of Lines
	private final Line[] lines;

  public PlayGrid(Position position, View viewRef, int RECT_SIZE) {
	  this.viewRef = viewRef;
	  this.lines = new Line[5];

	  for (int i = 0; i < 5; i++) {
		  lines[i] = new Line(new Position(position.getX(), position.getY() + i * RECT_SIZE), i + 1, RECT_SIZE);
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

  public void updateViewLine(LinkedList<Tile> toSend, int previousIndex, int i) {
	  lines[i].updateViewLine(toSend, previousIndex, viewRef);
	
  }
  
  public JButton[] getPileButtons() {
	  int ID = 0;
      JButton[] buttons = new JButton[5];
      int count = 0;
      for (Line line : lines) {
          buttons[count] = line.getButton();
          // Adding an ActionListener for each button of this popup
          // Every button possess an ID, which corresponds to the line of the grid
          ActionLine action = viewRef.actionLine(ID);
          buttons[count].addActionListener(action);
          count++;
          ID++;

      }
      return buttons;
  }
}


