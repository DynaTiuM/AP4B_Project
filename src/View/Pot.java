package View;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Pot {
  private Position position;
  private List<Pile> piles;
  private MiddlePile middlePile;

  public Pot(Position position) {
    this.position = position;
    piles = new ArrayList<>();

    Pile pile1 = new Pile(new Position(position.getX() + 50, position.getY()));
    piles.add(pile1);
    
    middlePile = new MiddlePile(new Position(position.getX(), position.getY()));

  }

  public void draw(Graphics g) {
    // Draw the piles
    for (Pile pile : piles) {
      pile.draw(g);
    }
    // Draw the middle pile
    middlePile.draw(g);
  }

  public void setButtons(boolean value) {
    // Set the buttons for the piles
    for (Pile pile : piles) {
      pile.setButton(value);
    }
    // Set the button for the middle pile
    middlePile.setButton(value);
  }

}