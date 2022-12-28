package View;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.*;

import controller.Controller;
import model.Tile;

public class View extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int HEIGHT = 720;
	private static final int WIDTH = 1080;
	private int numberOfPlayers; 
	
	private Bord[] bords;
	private Pot pot_m;
	
	private ViewPanel panel;
	
	private Controller controller_m;
	
	/**
	 * Create the frame.
	 */
	public View(Controller ref, int numberOfPlayers) {	
		this.numberOfPlayers = numberOfPlayers;
		controller_m = ref;
        // Set the background color of the frame to white
		setBackground(new Color(255, 255, 255));
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Empecher l'utilisateur de redimensioner la fenetre
		setResizable(false);
		//Afficher la fenetre au centre de l'�crant
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
		      panel = new ViewPanel(image, bords, pot_m);
			  setContentPane(panel);
		    }
	    
	    
	    
	    this.setVisible(true);
	}
	
	public void setButtonsPot(boolean possible[]) {
		pot_m.setButtons(possible);
	}
	
	public void setTile(LinkedList<Tile> tiles, int number) {
		  pot_m.setTile(tiles, number);
	  }
	
	public void updtatePile(LinkedList<Tile> to_update,int index) {
		
		pot_m.updatePile(to_update, index);
	}
	
	
	
	public ViewPanel getPanel() {
		return this.panel;
	}

	public void updateViewLine(LinkedList<Tile> to_send, int previous_index, int i, int current_player, LinkedList<Tile> linkedList, int previous_index_2) {
		bords[current_player].updateViewLine(to_send, previous_index, i, linkedList, previous_index_2);
		
	}

	
	
}


class ViewPanel extends JPanel {
	  /**
	 * 
	 */
	private Graphics ref;
	
	private static final long serialVersionUID = 1L;
	// Image to display
	private Image image;
	private Bord[] bords; 
	private Pot pot;
	
	
	// Constructor that takes in image, bords array, and pot object
	public ViewPanel(Image image, Bord[] bords, Pot pot) {
		this.image = image;
	    this.bords = bords; 
	    this.pot = pot;
	    this.setLayout(null); 
	   
	    
	    
	}
	
	public void addT(Tile_View tile) {
		this.add(tile);
		this.repaint();
	}
	
	public void removeT(Tile_View tile) {
		this.remove(tile);
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