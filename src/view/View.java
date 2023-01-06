package view;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

import controller.*;
import model.Line;
import model.Tile;

//Frame Containing the game
public class View extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int HEIGHT = 720;
    private static final int WIDTH = 1080;
	private static final int BORD_SIZE = 300;

    private final Bord[] bords;
    private final Pot potRef;
    private final Controller controllerRef;

    private ViewPanel contentPanel;

    //Constructor
    public View(Controller controller, int numberOfPlayers) {	 
        this.controllerRef = controller;

        // Basic JFrame configurations
        setBackground(new Color(255, 255, 255));
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Azul");
        
        //Loads Image from the resources File, we used this method to allow the executable .jar to get the images
        
        ImageIcon azulIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Azul.png")));
        Image azul = azulIcon.getImage();

        ImageIcon iconTable = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Table.png")));
        
        
        // Set the frame's icon
        setIconImage(azul);
        bords = new Bord[numberOfPlayers];
        bords[0] = new Bord(new Position(30, 20), this, 0);
        bords[1] = new Bord(new Position(WIDTH - BORD_SIZE - 50, 20), this, 1);

        if (numberOfPlayers > 2) {
            bords[2] = new Bord(new Position(30, HEIGHT -BORD_SIZE - 50), this, 2);
        }
        if (numberOfPlayers > 3) {
            bords[3] = new Bord(new Position(WIDTH - BORD_SIZE - 50, HEIGHT -BORD_SIZE - 50), this, 3);
        }
        // Create the Pot object
        potRef = new Pot(new Position(WIDTH/2, HEIGHT/2), (numberOfPlayers * 2) + 1, this);

        if (iconTable.getImageLoadStatus() == MediaTracker.ERRORED) {
        	// The image was not successfully loaded
            System.out.println("ERROR LOADING IMAGE BACKGROUND " + MediaTracker.ERRORED);
            
        } else {
            // The image was successfully loaded
            Image image = iconTable.getImage();
            contentPanel = new ViewPanel(controllerRef, image, bords, potRef, this);
            setContentPane(contentPanel);
        }


        this.setVisible(true);
    }

    // Call the method to update a pile
    public void updatePile(LinkedList<Tile> toUpdate, int index) {
        potRef.updatePile(toUpdate, index);
    }

    // Display the pop-up of the end of the game
    public void displayEndOfGame(model.Bord[] bords){
        this.getPanel().displayEndOfGame(bords);
    }

    // Get the contentPanel
    public ViewPanel getPanel() {
        return this.contentPanel;
    }

    // Call the method to update the line i of the bord of the current player
    public void updateViewLine(LinkedList<Tile> toSend, int previousIndex, int i, int currentPlayer, Tile[] malus) {
        bords[currentPlayer].updateViewLine(toSend, previousIndex, i, malus);
    }

    // Call the method to add or remove tiles "toSend" to the MiddlePile
    public void updateMiddlePile(LinkedList<Tile> toSend, int previousIndex, boolean delete) {
        potRef.updateMiddlePile(toSend, previousIndex, delete);
    }
    
    // Modify the pattern of the bord of the player "playerID"
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