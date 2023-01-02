package View;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import controller.ActionSelectionMiddlePile;
import controller.ActionSelectionPile;
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

    public void updtatePile(LinkedList<Tile> to_update,int index) {

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
    
    public void updatePopup(Tile[][] pattern, Tile[] malus, Line[] grid) {
    	ContentPanel.updateBordPopUp(pattern, malus, grid);
    }

    
    public void initiateButtons() {
    	pot_m.initiateButtons();
    }
    
    public void displayPilePopup(LinkedList<Tile> tiles, int ID) {
    	
    	System.out.println("ID : " + ID);
    	System.out.println(tiles);
    }
    
    public ActionSelectionPile actionSelectionPile(int number) {
    	return controller_ref.actionSelectionPile(number);
    }
    public ActionSelectionMiddlePile actionSelectionMiddlePile(int ID) {
    	return controller_ref.actionSelectionMiddlePile(ID);
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
        

        //Add the buttons of the Piles and middle pile
        JButton[] buttons = pot.getPileButtons();

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
    public void updateBordPopUp(Tile[][] pattern, Tile[] malus, Line[] grid) {
    	// Créer un panel pour afficher le bord en grand
        panel = new PopupPanel(view_ref, pattern, malus, grid);
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
    
    private HashMap<Tile_View, Position> tiles_pattern;
    private Tile_View[] tiles_malus;
    private Tile_View[][] tiles_playGrid;
    
    private View view_ref;
    
    private Position patternPosition = new Position(10 + POPUP_BORD_SIZE / 2, POPUP_RECT_SIZE*4);
    private Position gridPosition = new Position(POPUP_RECT_SIZE, POPUP_RECT_SIZE*4);
    private Position malusPosition = new Position(18, POPUP_BORD_SIZE - POPUP_RECT_SIZE * 2 - 20);
    
    private final static int TILE_SIZE = 30;
    

    // Timer to blink the buttons
    private Timer blinkTimer;
    // Constructor that takes in image, bords array, and pot object
    public PopupPanel(View view_ref, Tile[][] pattern, Tile[] malus, Line[] grid) {
    	this.view_ref = view_ref;
        setLayout(null);
        setPreferredSize(new Dimension(500, 500));
        this.playGrid = new PlayGrid(gridPosition, view_ref, POPUP_RECT_SIZE);
        this.pattern = new Pattern(patternPosition, view_ref,  POPUP_RECT_SIZE);
        this.malus = new Malus(malusPosition, view_ref, POPUP_RECT_SIZE);
        
        //Add the buttons of the Piles and middle pile
        JButton[] buttons = playGrid.getPileButtons();
     
        // Ajout des boutons au JPanel
        for (JButton button : buttons) {
            if (button != null) {
                this.add(button);
                button.setVisible(true);
            }
        }


        // Create the timer to blink the buttons
        blinkTimer = new Timer(500, new ActionListener() {
            // Toggle the visibility of the buttons every time the timer fires
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JButton button : buttons) {
                    if (button.getBackground() == Color.blue) {
                        button.setBackground(Color.red);
                        repaint();
                    } else {
                        button.setBackground(Color.blue);
                        repaint();
                    }
                }
            }
        });

        // Start the timer
        blinkTimer.start();
        
        updateBord(pattern, malus, grid);
        
    }
    
    public void updateBord(Tile[][] pattern, Tile[] malus, Line[] grid) {
    	
    	int offsetX = 12;
    	int offsetY = 12;
    	
    	for(int y = 0; y < 5; y++) {
    		for(int x = 0; x < 5; x++) {
    			if(pattern[y][x].getOccupied())
	    			switchEnum(pattern[y][x], offsetX, offsetY, 1, 1, patternPosition);
    		}
    	}
    	
    	offsetX = -18;
    	offsetY = 35;
    	
    	for(Tile t : malus) {
    		if(t != null) {
    			switchEnum(t, offsetX, -TILE_SIZE + offsetY, 1, 1, malusPosition);
    			offsetX = 40;
    		}
    	}
    	
    	offsetX = 12;
    	offsetY = 12;
    	
    	for(Line line : grid) {
        	int i = 1;
    		for(Tile t : line.getTiles()) {
    			if(t != null) {
    				switchEnum(t, offsetX, offsetY, 5 - i, line.getLength() - 1, gridPosition);
        		}
    			i++;
    		}
    	}
    	
    	this.repaint();
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