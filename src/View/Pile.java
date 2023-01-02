package View;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ActionDisplayTile;
import controller.ActionSelectionTile;
import model.Tile;

public class Pile {
    private static final int RECT_SIZE = Bord.RECT_SIZE;
    private Position position;
   
    private Tile_View[] tiles;
    private JButton[] buttons;
    private View view_m;
    private JPanel panel;
    int number;
    private int passage;

    public Pile(Position position, View view_ref, int number) {
        this.number = number;

        view_m = view_ref;

        this.position = position;
        tiles = new Tile_View[4];
        buttons = new JButton[4];
        
    }
    
    public void initiateButtons() {
    	int i = 0;
    	for(JButton button : buttons) {
	    	ActionSelectionTile action = view_m.actionSelectionTile(i, number);
	        button.addActionListener(action);
    		i++;
    	}
    }

    public void draw(Graphics g) {
        ImageIcon icon = new ImageIcon("src\\Images\\Pile.png");
        if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
            System.out.println("CANT LOAD IMAGE");
        } else {
            // The image was successfully loaded
            Image pile = icon.getImage();
            g.drawImage(pile, position.getX(), position.getY(), RECT_SIZE * 3, RECT_SIZE * 3, null);
        }
    }

    public boolean updateTile(LinkedList<Tile> to_iterate) {

        int x = 0;
        int y = 0;
        int i = 0;

        if (to_iterate.getFirst() != null) {
            for (Tile p : to_iterate) {
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
                buttons[i].setBounds(position.getX() + x * (RECT_SIZE * 2), position.getY() + y * (RECT_SIZE * 2), (int)(RECT_SIZE/1.5), (int)(RECT_SIZE/1.5));
               
                view_m.getPanel().addB(buttons[i]);
                view_m.getPanel().addT(tiles[i]);
                switch (i) {
                    case 0:
                        x++;
                        break;
                    case 1:
                        x = 0;
                        y++;
                        break;
                    case 2:
                        x++;
                        break;
                }
                i++;
            }
        }


        return to_iterate.getFirst() != null;

    }

    public void setButton(boolean value) {
    	for(JButton button : buttons) {
    		button.setVisible(value);
    	}
    }


    public void updatePile(LinkedList<Tile> to_update) {

        ViewPanel temp = this.view_m.getPanel();

        if (!updateTile(to_update)) {
            //System.out.println("\n" +number + "test bari");
        	for(JButton button : buttons) {
        		temp.removeB(button);
        	}
            for (Tile_View tile : tiles) {
                temp.removeT(tile);
            }
            //temp.revalidate();
            temp.repaint();
        }
    }
    
    public JButton[] getButtons() {
    	return buttons;
    }

}