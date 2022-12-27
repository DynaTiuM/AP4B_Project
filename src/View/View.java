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
	private static int numberOfPlayers = 3; 
	
	private Bord[] bords;
	private Pot pot_m;
	
	private Controller controller_m;
	
	/**
	 * Create the frame.
	 */
	public View(Controller ref) {		
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
		bords[0] = new Bord(new Position(30, 20));
		bords[1] = new Bord(new Position(WIDTH - Bord.BORD_SIZE - 50, 20));
		
		if (numberOfPlayers > 2) {
			bords[2] = new Bord(new Position(30, HEIGHT -Bord.BORD_SIZE - 50));
		}
		if (numberOfPlayers > 3) {
			bords[3] = new Bord(new Position(WIDTH - Bord.BORD_SIZE - 50, HEIGHT -Bord.BORD_SIZE - 50));
		}
		//getContentPane().add(center);
		bords[0].setButtons(true);
		
		 // Create the Pot object
	    pot_m = new Pot(new Position(WIDTH/2, HEIGHT/2), (numberOfPlayers * 2) + 1, this);
	    
	    if (iconTable.getImageLoadStatus() == MediaTracker.ERRORED) {
		      // There was an error loading the image
		    } else {
		      // The image was successfully loaded
		      Image image = Toolkit.getDefaultToolkit().getImage("src\\Images\\Table.png");
		      ViewPanel panel = new ViewPanel(image, bords, pot_m);
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
	
	public LinkedList<Tile> updtatePile(int index) {
		System.out.print("view - ");
		return controller_m.getTilesToView(index);
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
	    System.out.println("HEY");
	    
	}
	
	public Graphics getGraphics() {
		return ref;
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