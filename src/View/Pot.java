package View;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Tile;

public class Pot {
  private Position position;
  private Pile[] piles;
  private MiddlePile middlePile;
  
  private View view_m;
 

  public Pot(Position position, int numberOfPiles, View ref) {
	  
	 view_m = ref;
	  
    this.position = position;
    piles = new Pile[numberOfPiles];
    
    
    
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
    	
    	//Pile pile = new Pile(new Position(firstPileX + (i % 2) * pileSize, yPosition));
    	piles[i] = new Pile(new Position(firstPileX + (i % 2) * pileSize, yPosition), ref, i);
    }
    
    middlePile = new MiddlePile(new Position(firstPileX, yPosition + pileSize));

  }

  public void draw(Graphics g) {
    // Draw the piles
    for (Pile pile : piles) {
      pile.draw(g);
    }
    // Draw the middle pile
    middlePile.draw(g);
  }

  public void setButtons(boolean value[]) {
    // Set the buttons for the piles
	int i =0;
    for (Pile pile : piles) {
      pile.setButton(value[i]);
      i++;
    }
    
    //la dernière valeur de value c donc pour cheque que middle Pile est okay
    // Set the button for the middle pile
    middlePile.setButton(value[i]);
  }
  
  public void setTile(LinkedList<Tile> tiles, int number) {
	  //piles[number].redraw(tiles);
  }
  
  
  

}