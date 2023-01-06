package view;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

import controller.*;
import model.Line;
import model.Tile;

public class View extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int HEIGHT = 720;
    private static final int WIDTH = 1080;

    private final Bord[] bords;
    private final Pot potRef;
    private final Controller controllerRef;

    private ViewPanel contentPanel;

    /**
     * Create the frame.
     */
    public View(Controller controller, int numberOfPlayers) {	 //View(Controller ref, int numberOfPlayers)
        this.controllerRef = controller;

        // Set the background color of the frame to white
        setBackground(new Color(255, 255, 255));
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Empecher l'utilisateur de redimensioner la fenetre
        setResizable(false);
        //Afficher la fenetre au centre de l'ï¿½crant
        setLocationRelativeTo(null);

        setTitle("Azul");
        
        ImageIcon azulIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Azul.png")));
        Image azul = azulIcon.getImage();

        ImageIcon iconTable = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Table.png")));
        // Set the frame's icon
        setIconImage(azul);
        bords = new Bord[numberOfPlayers];
        bords[0] = new Bord(new Position(30, 20), this, 0);
        bords[1] = new Bord(new Position(WIDTH - Bord.BORD_SIZE - 50, 20), this, 1);

        if (numberOfPlayers > 2) {
            bords[2] = new Bord(new Position(30, HEIGHT -Bord.BORD_SIZE - 50), this, 2);
        }
        if (numberOfPlayers > 3) {
            bords[3] = new Bord(new Position(WIDTH - Bord.BORD_SIZE - 50, HEIGHT -Bord.BORD_SIZE - 50), this, 3);
        }
        // Create the Pot object
        potRef = new Pot(new Position(WIDTH/2, HEIGHT/2), (numberOfPlayers * 2) + 1, this);

        if (iconTable.getImageLoadStatus() == MediaTracker.ERRORED) {
            System.out.println("ERROR LOADING IMAGE BACKGROUND " + MediaTracker.ERRORED);
            
        } else {
            // The image was successfully loaded
        	
            Image image = iconTable.getImage();
            contentPanel = new ViewPanel(controllerRef, image, bords, potRef, this);
            setContentPane(contentPanel);
        }


        this.setVisible(true);
    }

    public void updatePile(LinkedList<Tile> toUpdate, int index) {
        potRef.updatePile(toUpdate, index);
    }

    public void displayEndOfGame(model.Bord[] bords){
        this.getPanel().displayEndOfGame(bords);
    }

    public ViewPanel getPanel() {
        return this.contentPanel;
    }

    public void updateViewLine(LinkedList<Tile> toSend, int previousIndex, int i, int currentPlayer, Tile[] malus) {
        bords[currentPlayer].updateViewLine(toSend, previousIndex, i, malus);
    }

    public void updateMiddlePile(LinkedList<Tile> toSend, int previousIndex, boolean delete) {
        potRef.updateMiddlePile(toSend, previousIndex, delete);
    }
    public void updatePattern(int playerID, HashMap<Tile, Position> toSend) {
        bords[playerID].updatePattern(toSend);
    }

    public void updateMalus(Tile[] malus, int currentPlayer) {
        bords[currentPlayer].updateMalus(malus);
    }

    public void clearMalus(int playerID){
        bords[playerID].clearMalus();
    }
    
    public void updatePopup(Tile[][] pattern, Tile[] malus, Line[] grid, Tile hand) {
    	contentPanel.updateBordPopUp(pattern, malus, grid, hand);
    }
    
    public void closePopup() {
    	contentPanel.closePopUp();
    }
    
    public void initiateButtons() {
    	potRef.initiateButtons();
    }
    public int getScore(int playerID) {
        return controllerRef.getScore(playerID);
    }
    
    public ActionSelectionTile actionSelectionTile(int ID, int numberPile) {
    	return controllerRef.actionSelectionTile(ID, numberPile);
    }
    public ActionSelectionMiddlePile actionSelectionMiddlePile(int ID) {
    	return controllerRef.actionSelectionMiddlePile(ID);
    }
    
    public ActionLine actionLine(int ID) {
    	return controllerRef.actionLine(ID);
    }
    
    public ActionMalus actionMalus() {
    	return controllerRef.actionMalus();
    }

    public ActionEnd actionEnd(int ID, PopupEnd popupEnd) {
        return controllerRef.actionEnd(ID, popupEnd);
    }

	public void updateViewLine(LinkedList<Tile> toSend, int previousIndex, int i, int currentPlayer) {
		bords[currentPlayer].updateViewLine(toSend, previousIndex, i);
	}

	public void sendMalusFirstToView(int previous, int currentPlayer) {
		bords[currentPlayer].sendMalusFirstToView(previous);
		
	}

    public void stopGame(){
        controllerRef.stopGame();
    }
}


class ViewPanel extends JPanel {
    /**
     *
     */
    private final View view_ref;
    private static final long serialVersionUID = 1L;
    // Image to display
    private final Image image;
    private final Bord[] bords;
    private final Pot pot;
    private PopupPanel panel;
    private final Controller controllerRef;
    private final Font font;

// Constructor that takes in image, bords array, and pot object
    public ViewPanel(Controller controller, Image image, Bord[] bords, Pot pot, View view_ref) {
        this.view_ref = view_ref;
        this.image = image;
        this.bords = bords;
        this.pot = pot;
        this.setLayout(null);
        this.controllerRef= controller;
        font = new Font("Arial", Font.BOLD, 30);    
        
    }

    public void closePopUp() {
    	panel.closePopUp();
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


  //Cette fonction est appelï¿½e pour afficher en grand le Bord du joueur actif avec des boutons
    public void updateBordPopUp(Tile[][] pattern, Tile[] malus, Line[] grid, Tile hand) {
    	// Crï¿½er un panel pour afficher le bord en grand

        panel = new PopupPanel(view_ref, pattern, malus, grid, hand);
        JDialog dialog = new JDialog((JFrame)null, "", true);
        dialog.setUndecorated(true);
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        panel.setLayout(new BorderLayout());

        // Afficher la fenï¿½tre pop-up avec le panel contenant le bord en grand

        //JOptionPane.showMessageDialog(null, panel, "Bord en grand", JOptionPane.PLAIN_MESSAGE);


    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        // Draw image
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        // Draw bords
        for (Bord bord : bords) {
            bord.draw(g);
        }
        pot.draw(g);

        //Modification de la couleur et de la police de g
        g.setFont(font);
        g.setColor(Color.WHITE);
        try{
            g.drawString("Tour du joueur " + (controllerRef.getCurrentPlayer() + 1), 413, 35);
        }catch (Exception e) {
            System.out.println("Game not initialized !");
        }
        //On remet g a le couleur de base;
        g.setColor(Color.BLACK);
    }

    public void displayEndOfGame(model.Bord[] bords) {
        JPanel panel = new PopupEnd(view_ref, bords);
        JDialog dialog = new JDialog((JFrame)null, "", true);
        dialog.setUndecorated(true);
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        panel.setLayout(new BorderLayout());
    }
}



class PopupPanel extends JPanel {
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
    public PopupPanel(View view_ref, Tile[][] pattern, Tile[] malus, Line[] grid, Tile hand) {
        
    	ImageIcon icon_ = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Bord.png")));
        image = icon_.getImage();
        setLayout(null);
        setPreferredSize(new Dimension(500, 500));
        this.playGrid = new PlayGrid(gridPosition, view_ref, POPUP_RECT_SIZE);
        this.pattern = new Pattern(patternPosition, view_ref,  POPUP_RECT_SIZE);
        this.malus = new Malus(malusPosition, view_ref, POPUP_RECT_SIZE);

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
        // Erreur lors du chargement de l'image
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