package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Pot extends JPanel{
  private Position position;
  private List<Pile> piles;
  private MiddlePile middlePile;

  public Pot(Position position, int numberOfPiles) {
	  super();
    this.position = position;
    piles = new ArrayList<>();
    
    setPreferredSize(new Dimension(View.WIDTH / 3, View.HEIGHT));
    setOpaque(false);
    
    // Calculate the center position of the frame
    int centerX = position.getX();

    // Calculate the size of the stacks
    int pileSize = Bord.RECT_SIZE * 4;

    // Calculate the x and y coordinates of the top-left corner of the first stack
    int firstPileX = centerX - pileSize;
    
    int yPosition = pileSize / 2;
    
    for (int i=0; i < numberOfPiles; i++) {
    	
        if (i % 2 == 0 && i != 0) {
            yPosition += pileSize;
        }
    	
    	Pile pile = new Pile(new Position(0,0));
    	piles.add(pile);
    	this.add(pile);
    }
    
    middlePile = new MiddlePile(new Position(0, 0));
    this.add(middlePile);
  }

  @Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
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