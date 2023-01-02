package View;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import controller.ActionLine;
import controller.ActionMalus;
import controller.ActionSelectionMiddlePile;
import controller.ActionSelectionTile;
import controller.Controller;
import model.Line;
import model.Tile;

public class View extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int HEIGHT = 720;
    private static final int WIDTH = 1080;
    private int numberOfPlayers;

    private Bord[] bords;
    private Pot pot_m;
    private Controller controller_ref;

    private ViewPanel ContentPanel;

    //private Controller controller_m;

    /**
     * Create the frame.
     */
    public View(Controller controller, int numberOfPlayers) {	 //View(Controller ref, int numberOfPlayers) 
        this.numberOfPlayers = numberOfPlayers;
        this.controller_ref = controller;
        //controller_m = ref;
        // Set the background color of the frame to white
        setBackground(new Color(255, 255, 255));
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Empecher l'utilisateur de redimensioner la fenetre
        setResizable(false);
        //Afficher la fenetre au centre de l'ï¿½crant
        setLocationRelativeTo(null);

        setTitle("Azul");
        Image icon = Toolkit.getDefaultToolkit().getImage("src\\Images\\Azul.png");

        ImageIcon iconTable = new ImageIcon("src\\Images\\Table.png");
        // Set the frame's icon
        setIconImage(icon);
        bords = new Bord[numberOfPlayers];
        bords[0] = new Bord(new Position(30, 20), this);
        bords[1] = new Bord(new Position(WIDTH - Bord.BORD_SIZE - 50, 20), this);

        if (numberOfPlayers > 2) {
            bords[2] = new Bord(new Position(30, HEIGHT -Bord.BORD_SIZE - 50), this);
        }
        if (numberOfPlayers > 3) {
            bords[3] = new Bord(new Position(WIDTH - Bord.BORD_SIZE - 50, HEIGHT -Bord.BORD_SIZE - 50), this);
        }
        //getContentPane().add(center);
        bords[0].setButtons(true);

        // Create the Pot object
        pot_m = new Pot(new Position(WIDTH/2, HEIGHT/2), (numberOfPlayers * 2) + 1, this);

        if (iconTable.getImageLoadStatus() == MediaTracker.ERRORED) {
            System.out.println("ERRO LOADING IMAGE BACKGROUND " + MediaTracker.ERRORED);
        } else {
            // The image was successfully loaded
            System.out.println("hop");
            Image image = Toolkit.getDefaultToolkit().getImage("src\\Images\\Table.png");
            ContentPanel = new ViewPanel(image, bords, pot_m, this);
            setContentPane(ContentPanel);
        }


        this.setVisible(true);
    }

    public void setButtonsPot(boolean possible[]) {
        //pot_m.setButtons(possible);
    }

    public void setTile(LinkedList<Tile> tiles, int number) {
        pot_m.setTile(tiles, number);
    }

    public void updatePile(LinkedList<Tile> to_update, int index) {
        pot_m.updatePile(to_update, index);
    }

    public ViewPanel getPanel() {
        return this.ContentPanel;
    }

    public void updateViewLine(LinkedList<Tile> to_send, int previous_index, int i, int current_player, LinkedList<Tile> linkedList, int previous_index_2) {
        bords[current_player].updateViewLine(to_send, previous_index, i, linkedList, previous_index_2);
    }

    public void updateMiddlePile(LinkedList<Tile> to_send, int previous_index, boolean delete) {
        pot_m.updateMiddlePile(to_send, previous_index, delete);
    }
    
    public void updateMiddlePile(LinkedList<Tile> to_send) {
    	pot_m.updateMiddlePile(to_send);
    }

    public void updatePattern(int playerID, HashMap<Tile, Position> to_send) {
        bords[playerID].updatePattern(to_send);
    }
    
    public void updateMalus(int playerID) {
    	bords[playerID].updateMalus();
    }
    
    public void updatePopup(Tile[][] pattern, Tile[] malus, Line[] grid, Tile hand) {
    	ContentPanel.updateBordPopUp(pattern, malus, grid, hand);
    }
    
    public void closePopup() {
    	ContentPanel.closePopUp();
    }
    
    public void initiateButtons() {
    	pot_m.initiateButtons();
    }

    
    public ActionSelectionTile actionSelectionTile(int ID, int numberPile) {
    	return controller_ref.actionSelectionTile(ID, numberPile);
    }
    public ActionSelectionMiddlePile actionSelectionMiddlePile(int ID) {
    	return controller_ref.actionSelectionMiddlePile(ID);
    }
    
    public ActionLine actionLine(int ID) {
    	return controller_ref.actionLine(ID);
    }
    
    public ActionMalus actionMalus() {
    	return controller_ref.actionMalus();
    }
}


class ViewPanel extends JPanel {
    /**
     *
     */
    private Graphics ref;
    private View view_ref;
    private static final long serialVersionUID = 1L;
    // Image to display
    private Image image;
    private Bord[] bords;
    private Pot pot;
    private PopupPanel panel;

    // Constructor that takes in image, bords array, and pot object
    public ViewPanel(Image image, Bord[] bords, Pot pot, View view_ref) {
        this.view_ref = view_ref;
        this.image = image;
        this.bords = bords;
        this.pot = pot;
        this.setLayout(null);
    }

    public void closePopUp() {
    	panel.closePopUp();
	}
    
    public void addButtons() {
    	//Add the buttons of the Piles and middle pile
        System.out.print("Adding buttons!");
        LinkedList<JButton> buttons = pot.getTileButtons();

        // Ajout des boutons au JPanel
        for (JButton button : buttons) {
            if (button != null) {
                this.add(button);
            }
        }

        pot.setButtons(true);
    }

	public void addT(Tile_View tile) {
        this.add(tile);
        this.repaint();
    }
    
    public void addB(JButton button) {
    	this.add(button);
    	this.repaint();
    }

    public void removeT(Tile_View tile) {
        this.remove(tile);
        this.repaint();
    }
    
    public void removeB(JButton button) {
    	this.remove(button);
    	this.repaint();
    }
  //Cette fonction est appelée pour afficher en grand le Bord du joueur actif avec des boutons
    public void updateBordPopUp(Tile[][] pattern, Tile[] malus, Line[] grid, Tile hand) {
    	// Créer un panel pour afficher le bord en grand
        panel = new PopupPanel(view_ref, pattern, malus, grid, hand);
        panel.setLayout(new BorderLayout());

        // Afficher la fenêtre pop-up avec le panel contenant le bord en grand
        JOptionPane.showMessageDialog(null, panel, "Bord en grand", JOptionPane.PLAIN_MESSAGE);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ref = g;
        // Draw image 
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

        // Draw bords 
        for (Bord bord : bords) {
            bord.draw(g);
        }
        pot.draw(g);
    }
}


class PopupPanel extends JPanel {
    /**
     *
     */
    private static final int POPUP_BORD_SIZE = 500;
    private static final int POPUP_RECT_SIZE = 42;
    private static final long serialVersionUID = 1L;
    private Image image = Toolkit.getDefaultToolkit().getImage("src\\Images\\Bord.png");
    private PlayGrid playGrid;
    private Pattern pattern;
    private Malus malus;
    private JButton[] buttons;
    private JButton malusButton;
     
    private View view_m;
    
    private Position patternPosition = new Position(10 + POPUP_BORD_SIZE / 2, POPUP_RECT_SIZE*4);
    private Position gridPosition = new Position(POPUP_RECT_SIZE, POPUP_RECT_SIZE*4);
    private Position malusPosition = new Position(18, POPUP_BORD_SIZE - POPUP_RECT_SIZE * 2 - 20);
    
    private final static int TILE_SIZE = 30;

    // Timer to blink the buttons
    private Timer blinkTimer;
    
    // Constructor that takes in image, bords array, and pot object
    public PopupPanel(View view_ref, Tile[][] pattern, Tile[] malus, Line[] grid, Tile hand) {
    	this.view_m = view_ref;
        setLayout(null);
        setPreferredSize(new Dimension(500, 500));
        this.playGrid = new PlayGrid(gridPosition, view_ref, POPUP_RECT_SIZE);
        this.pattern = new Pattern(patternPosition, view_ref,  POPUP_RECT_SIZE);
        this.malus = new Malus(malusPosition, view_ref, POPUP_RECT_SIZE);
        
        
        //Add the buttons of the Piles and middle pile
        this.malusButton = this.malus.getMalusButton();
        this.add(malusButton);
      //Add the buttons of the Piles and middle pile
        this.buttons = playGrid.getPileButtons();
        ImageIcon rolloverIcon = new ImageIcon("src\\Images\\rolloverButtonLines.png");
        ImageIcon icon = new ImageIcon("src\\Images\\ButtonLines.png");
        // Ajout des boutons au JPanel
        for (JButton button : this.buttons) {
            if (button != null) {
                this.add(button);
                if (rolloverIcon.getImageLoadStatus() == MediaTracker.ERRORED) {
                    // Erreur lors du chargement de l'image
                	System.out.println("error");
                  } else {
                      button.setRolloverIcon(rolloverIcon);
                  }

                button.setVisible(true);
                // Chargement de l'image de la tuile

                // Vérifie si l'image a pu être chargée correctement
                if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
                  // Erreur lors du chargement de l'image
                } else {
                    button.setIcon(icon);
                }
            }

        }



        ImageIcon icon2 = new ImageIcon("src\\Images\\ButtonLines2.png");

        // Create the timer to blink the buttons
        blinkTimer = new Timer(500, new ActionListener() {
            // Toggle the visibility of the buttons every time the timer fires
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JButton button : buttons) {
                    if (button.getIcon() == icon2) {
                        button.setIcon(icon);
                        repaint();
                    } else {
                        if (icon2.getImageLoadStatus() == MediaTracker.ERRORED) {
                            // Erreur lors du chargement de l'image
                          } else {
                              button.setIcon(icon2);
                              repaint();
                          }
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
    	
    	int offsetX = 12;
    	int offsetY = 12;
    	
    	// Printing tiles of pattern
    	for(int y = 0; y < 5; y++) {
    		for(int x = 0; x < 5; x++) {
    			if(pattern[y][x].getOccupied())
	    			switchEnum(pattern[y][x], offsetX, offsetY, 1, 1, patternPosition);
    		}
    	}
    	
    	offsetX = -18;
    	offsetY = 35;
    	
    	// Printing tiles of malus
    	for(Tile t : malus) {
    		if(t != null) {
    			switchEnum(t, offsetX, -TILE_SIZE + offsetY, 1, 1, malusPosition);
    			offsetX = 40;
    		}
    	}
    	
    	offsetX = 12;
    	offsetY = 12;
    	
    	// Printing tiles of grid
    	for(Line line : grid) {
        	int i = 1;
    		for(Tile t : line.getTiles()) {
    			if(t != null) {
    				switchEnum(t, offsetX, offsetY, 5 - i, line.getLength() - 1, gridPosition);
    				
    				// The color of the tile selected is different from the color of tiles on the line
    				// Or the line is already full :
    				// We need to disable the button of this line
    				if(hand.getColor() != t.getColor() || line.checkFull()) {
    					buttons[line.getLength() - 1].setVisible(false);
    				}
        		}
    			i++;
    		}
    	}
    }
    
    private void switchEnum(Tile t, int offsetX, int offsetY, int x, int y, Position position) {
    	Tile_View tile = null;    	

    	switch (t.getColorEnum()){
			case O: tile = new Orange(new Position(position.getX() + x * (TILE_SIZE + offsetX), position.getY() + y * (TILE_SIZE + offsetY)), true);
				break;
			case M: tile = new Purple(new Position(position.getX() + x * (TILE_SIZE + offsetX), position.getY() + y * (TILE_SIZE + offsetY)), true);
				break;
			case B: tile = new Blue(new Position(position.getX() + x * (TILE_SIZE + offsetX), position.getY() + y * (TILE_SIZE + offsetY)), true);
				break;
			case Y: tile = new Yellow(new Position(position.getX() + x * (TILE_SIZE + offsetX), position.getY() + y * (TILE_SIZE + offsetY)), true);
				break;
			case G: tile = new Green(new Position(position.getX() + x * (TILE_SIZE + offsetX), position.getY() + y * (TILE_SIZE + offsetY)), true);
				break;
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