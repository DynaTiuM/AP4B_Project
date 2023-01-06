package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import model.Line;
import model.Tile;

public class PopupPanel extends JPanel {
    /**
     *
     */
    private static final int POPUP_BORD_SIZE = 500;
    private static final int POPUP_RECT_SIZE = 42;
    private static final long serialVersionUID = 1L;
    private final Image image;
    private final PlayGrid playGrid;
    private final Pattern pattern;
    private final Malus malus;
    private final JButton[] buttons;

    private final Position patternPosition = new Position(10 + POPUP_BORD_SIZE / 2, POPUP_BORD_SIZE / 3);
    private final Position gridPosition = new Position(POPUP_RECT_SIZE, POPUP_BORD_SIZE / 3);
    private final Position malusPosition = new Position(18, POPUP_BORD_SIZE - POPUP_BORD_SIZE / 6 - 20);

    // Constructor that takes in image, bords array, and pot object
    public PopupPanel(View viewRef, Tile[][] pattern, Tile[] malus, Line[] grid, Tile hand) {
        
    	ImageIcon icon_ = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Bord.png")));
        image = icon_.getImage();
        setLayout(null);
        setPreferredSize(new Dimension(500, 500));
        this.playGrid = new PlayGrid(gridPosition, viewRef, POPUP_RECT_SIZE);
        this.pattern = new Pattern(patternPosition, viewRef,  POPUP_RECT_SIZE);
        this.malus = new Malus(malusPosition, viewRef, POPUP_RECT_SIZE);

        //Add the buttons of the Piles and middle pile
        JButton malusButton = this.malus.getMalusButton();
        this.add(malusButton);
      //Add the buttons of the Piles and middle pile
        this.buttons = playGrid.getPileButtons();

        ImageIcon rolloverIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("rolloverButtonLines.png")));

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("ButtonLines.png")));
        // Ajout des boutons au JPanel
        for (JButton button : this.buttons) {
            if (button != null) {
                this.add(button);
                if (rolloverIcon.getImageLoadStatus() == MediaTracker.ERRORED) {
                    // Erreur lors du chargement de l'image
                    assert false : "Can't load the image";
                  } else {
                      button.setRolloverIcon(rolloverIcon);
                  }

                button.setVisible(true);
                // Chargement de l'image de la tuile

                // V�rifie si l'image a pu �tre charg�e correctement
                if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
                    assert false : "Can't load the image";
                    // Erreur lors du chargement de l'image
                } else {
                    button.setIcon(icon);
                }
            }
        }

        ImageIcon icon2 = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("ButtonLines2.png")));

        // Create the timer to blink the buttons
        // Toggle the visibility of the buttons every time the timer fires
        // Verifie if there was an error while loading the image
        // Timer to blink the buttons
        // Toggle the visibility of the buttons every time the timer fires
        Timer blinkTimer = new Timer(500, e -> {
            for (JButton button : buttons) {
                if (button.getIcon() == icon2) {
                    button.setIcon(icon);
                    repaint();
                } else {
                    if (icon2.getImageLoadStatus() == MediaTracker.ERRORED) {
                        assert false : "Can't load the image";
                        // Erreur lors du chargement de l'image
                    } else {
                        button.setIcon(icon2);
                        repaint();
                    }
                }
            }
        });

        // Start the timer
        blinkTimer.start();

        updateBord(pattern, malus, grid, hand);
        
    }
    
    // Closing the popup after the player clicked on the button
    public void closePopUp() {
 	   SwingUtilities.getWindowAncestor(this).dispose();
	}

	public void updateBord(Tile[][] pattern, Tile[] malus, Line[] grid, Tile hand) {
    	
    	int offsetX = -POPUP_RECT_SIZE;
    	int offsetY = -POPUP_RECT_SIZE;
    	
    	// Printing tiles of pattern
    	for(int y = 0; y < 5; y++) {
    		for(int x = 0; x < 5; x++) {
    			if(pattern[y][x].getOccupied()){
                    switchEnum(pattern[y][x], offsetX, offsetY, 1, 1, patternPosition);
                }
                offsetX += POPUP_RECT_SIZE;
    		}
            offsetX = -POPUP_RECT_SIZE;
            offsetY += POPUP_RECT_SIZE;
    	}
    	
    	offsetX = - POPUP_RECT_SIZE + 12;
    	offsetY = 35 - POPUP_RECT_SIZE ;
    	
    	// Printing tiles of malus
    	for(Tile t : malus) {
    		if(t != null) {
    			switchEnum(t, offsetX, offsetY, 1, 1, malusPosition);
    			offsetX += 63;
    		}
    	}
    	
    	offsetX = 0;
    	offsetY = 0;
    	
    	// Printing tiles of grid
    	for(Line line : grid) {
        	int i = 1;
    		for(Tile t : line.getTiles()) {
    			if(t != null) {
    				switchEnum(t, offsetX, offsetY, 5 - i, line.getLength() - 1, gridPosition);
    				
    				// The color of the tile selected is different from the color of tiles on the line
    				// Or the line is already full :
    				// We need to disable the button of this line
                    if((!line.isPossible(hand) || line.checkFull())) {
    					buttons[line.getLength() - 1].setVisible(false);
    				}
        		}
    			i++;
    		}
    	}
        for(Line line : grid){
            if(line.isAlreadyOnPattern(hand)) {
                buttons[line.getLength() - 1].setVisible(false);
            }
        }
    }
    
    private void switchEnum(Tile t, int offsetX, int offsetY, int x, int y, Position position) {
    	Tile_View tile = null;    	

    	switch (t.getColorEnum()){
			case O: tile = new Orange(new Position(position.getX() + x * (POPUP_RECT_SIZE + offsetX), position.getY() + y * (POPUP_RECT_SIZE + offsetY)), true);
				break;
			case M: tile = new Purple(new Position(position.getX() + x * (POPUP_RECT_SIZE + offsetX), position.getY() + y * (POPUP_RECT_SIZE + offsetY)), true);
				break;
			case B: tile = new Blue(new Position(position.getX() + x * (POPUP_RECT_SIZE + offsetX), position.getY() + y * (POPUP_RECT_SIZE + offsetY)), true);
				break;
			case Y: tile = new Yellow(new Position(position.getX() + x * (POPUP_RECT_SIZE + offsetX), position.getY() + y * (POPUP_RECT_SIZE + offsetY)), true);
				break;
			case G: tile = new Green(new Position(position.getX() + x * (POPUP_RECT_SIZE + offsetX), position.getY() + y * (POPUP_RECT_SIZE + offsetY)), true);
				break;
			case MALUS: tile = new MalusTile(new Position(position.getX() + x * (POPUP_RECT_SIZE + offsetX), position.getY() + y * (POPUP_RECT_SIZE + offsetY)), true);
		}
		this.add(tile);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw image 
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        malus.draw(g);
        playGrid.draw(g);
        pattern.draw(g);

    }
}
