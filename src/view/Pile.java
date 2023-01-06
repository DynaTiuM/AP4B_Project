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

public class Pile {
    private static final int RECT_SIZE = Bord.RECT_SIZE;
    private final Position position;
   
    private final Tile_View[] tiles;
    private final JButton[] buttons;
    private final View viewRef;
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

    public boolean updateTile(LinkedList<Tile> toIterate) {

        int x = 0;
        int y = 0;
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
                buttons[i].setOpaque(false);
                buttons[i].setContentAreaFilled(false);
                //enlever la bordure
                buttons[i].setBorderPainted(false);
                buttons[i].setBounds(position.getX() + x * (RECT_SIZE * 2), position.getY() + y * (RECT_SIZE * 2), RECT_SIZE, RECT_SIZE);
               
                viewRef.getPanel().addB(buttons[i]);
                viewRef.getPanel().addT(tiles[i]);
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


        return toIterate.getFirst() != null;

    }


    public void updatePile(LinkedList<Tile> toUpdate) {

        ViewPanel temp = this.viewRef.getPanel();

        if (!updateTile(toUpdate)) {

        	for(JButton button : buttons) {
        		temp.removeB(button);
        	}
            for (Tile_View tile : tiles) {
                temp.removeT(tile);
            }

            temp.repaint();
        }
    }
}