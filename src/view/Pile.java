package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.LinkedList;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import controller.ActionSelectionTile;
import model.Tile;
//Display A pile in the ViewPanel
public class Pile {
    private static final int RECT_SIZE = 25;
    private final Position position;
   
    //Stores tiles in the pile
    private final Tile_View[] tiles;
    
    //Stores Buttons we will need to get the Tiles
    private final JButton[] buttons;
    
    //Reference to the View
    private final View viewRef;
    
    //ID of the Pile
    int number;

    public Pile(Position position, View viewRef, int number) {
        this.number = number;

        this.viewRef = viewRef;

        this.position = position;
        tiles = new Tile_View[4];
        buttons = new JButton[4];
        
    }
    
    public void initiateButtons() {
    	int i = 0;
    	for(JButton button : buttons) {
	    	ActionSelectionTile action = viewRef.actionSelectionTile(i, number);
	        button.addActionListener(action);
    		i++;
    	}
    }

    public void draw(Graphics g) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Pile.PNG")));
        if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
            System.out.println("CANT LOAD IMAGE");
        } else {
            // The image was successfully loaded
            Image pile = icon.getImage();
            g.drawImage(pile, position.getX(), position.getY(), RECT_SIZE * 3, RECT_SIZE * 3, null);
        }
    }

    //Method used to get Tiles from the model and adding them on the ViewPanel
    public boolean updateTile(LinkedList<Tile> toIterate) {

    	//Coordinates x and y of the tiles (will be multiplied by RECT_SIZE * 2)
        int x = 0;
        int y = 0;
        
        //index of the current Tile beeing studied
        int i = 0;

        if (toIterate.getFirst() != null) {
            for (Tile p : toIterate) {
                switch (p.getColorEnum()) {
                    case O:
                        tiles[i] = new Orange(new Position(position.getX() + x * (RECT_SIZE * 2), position.getY() + y * (RECT_SIZE * 2)));
                        break;
                    case M:
                    	tiles[i] = new Purple(new Position(position.getX() + x * (RECT_SIZE * 2), position.getY() + y * (RECT_SIZE * 2)));
                        break;
                    case B:
                    	tiles[i] = new Blue(new Position(position.getX() + x * (RECT_SIZE * 2), position.getY() + y * (RECT_SIZE * 2)));
                        break;
                    case Y:
                    	tiles[i] = new Yellow(new Position(position.getX() + x * (RECT_SIZE * 2), position.getY() + y * (RECT_SIZE * 2)));
                        break;
                    case G:
                    	tiles[i] = new Green(new Position(position.getX() + x * (RECT_SIZE * 2), position.getY() + y * (RECT_SIZE * 2)));
                        break;
                }
                
                buttons[i] = new JButton();
                
                //Basic configurations of buttons, buttons have been configured to not be visible from behind the tiles
                buttons[i].setOpaque(false);
                buttons[i].setContentAreaFilled(false);
                //Removes border
                buttons[i].setBorderPainted(false);
                buttons[i].setBounds(position.getX() + x * (RECT_SIZE * 2), position.getY() + y * (RECT_SIZE * 2), RECT_SIZE, RECT_SIZE);
               
                //Add the current Button
                viewRef.getPanel().addB(buttons[i]);
                
                //Add the current Tile
                viewRef.getPanel().addT(tiles[i]);
                
                //changes coordinates x and y of piles for the next one 
                switch (i) {
                    case 0:
                    case 2:
                        x++;
                        break;
                    case 1:
                        x = 0;
                        y++;
                        break;
                }
                i++;
            }
        }

        //checks if the pile was not empty
        return toIterate.getFirst() != null;

    }

    //Remove the buttons and tiles on the ViewPanel
    public void updatePile(LinkedList<Tile> toUpdate) {

        ViewPanel temp = this.viewRef.getPanel();

        if (!updateTile(toUpdate)) {

        	for(JButton button : buttons) {
        		//Remove the current button
        		temp.removeB(button);
        	}
            for (Tile_View tile : tiles) {
            	//Remove the current Tile
                temp.removeT(tile);
            }

            temp.repaint();
        }
    }
}