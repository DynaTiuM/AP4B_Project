package view;

import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JButton;

import controller.ActionSelectionMiddlePile;
import model.Tile;

//Displays the Piles and MiddlePile
public class Pot {
    // Array contening the Piles
	private static final int RECT_SIZE = 25;
    private final Pile[] piles;
    
    private final MiddlePile middlePile;
  
    // Reference towards view, used for communication
    private final View viewRef;
 

    // Constructor with the position of the Pot, the number of tiles to instanciate and the reference to the view
    public Pot(Position position, int numberOfPiles, View viewRef) {
	  
	    // sets reference to View
	    this.viewRef = viewRef;
        
 
        this.piles = new Pile[numberOfPiles];
    
        // Determine center of the frame
        int centerX = position.getX();

        // Determine size of Piles
        int pileSize = RECT_SIZE * 4;

        // Determine the top left coordinates x and y of the first pile
        int firstPileX = centerX - pileSize;

        int yPosition = pileSize / 2;

        // Creation of piles
        for (int i = 0; i < numberOfPiles; i++) {
        	
            if (i % 2 == 0 && i != 0) {
                yPosition += pileSize;
            }

            
            piles[i] = new Pile(new Position(firstPileX + (i % 2) * pileSize, yPosition), viewRef, i);
        }
    
        
        middlePile = new MiddlePile(this, new Position(firstPileX, yPosition + pileSize));
    }
  
    //Method allowing us to add the Tile panels to the ViewPanel
    public void addT(Tile_View tile) {
	    viewRef.getPanel().addT(tile);
    }
  
    //Method allowing us to add the Buttons to the ViewPanel
    public void addB(JButton button) {
	    viewRef.getPanel().addB(button);
    }
    
    //Method allowing us to remove the Tile panels from the ViewPanel
    public void removeT(Tile_View tile) {
		viewRef.getPanel().removeT(tile);
	}
	
    //Method allowing us to remove the Buttons from the ViewPanel
	public void removeB(JButton button) {
		viewRef.getPanel().removeB(button);
	}
  
    public ActionSelectionMiddlePile actionSelectionMiddlePile(int ID) {
        return viewRef.actionSelectionMiddlePile(ID);
    }

    
    public void draw(Graphics g) {
        
        for (Pile pile : piles) {
            pile.draw(g);
        }
    }
  
  	public void initiateButtons() {
		for(Pile pile : piles) {
			pile.initiateButtons();
		}
	}

    public void updatePile(LinkedList<Tile> toUpdate, int index) {
        piles[index].updatePile(toUpdate);
    }

    public void updateMiddlePile(LinkedList<Tile> toUpdate, int previousIndex, boolean delete) {
        middlePile.updatePile(toUpdate, previousIndex, delete);
    }

	
}