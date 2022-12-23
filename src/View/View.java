package View;

import java.awt.*;
import javax.swing.*;

public class View extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int HEIGHT = 720;
	private static final int WIDTH = 1080;
	private static int numberOfPlayers = 3; 
	
	/**
	 * Create the frame.
	 */
	public View() {		
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
		Bord[] bords = new Bord[numberOfPlayers];
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
	    Pot pot = new Pot(new Position(WIDTH/2, HEIGHT/2), (numberOfPlayers * 2) + 1);
	    
	    if (iconTable.getImageLoadStatus() == MediaTracker.ERRORED) {
		      // There was an error loading the image
		    } else {
		      // The image was successfully loaded
		      Image image = Toolkit.getDefaultToolkit().getImage("src\\Images\\Table.png");
		      ViewPanel panel = new ViewPanel(image, bords, pot);
			  setContentPane(panel);
		    }
	}
	
}


class ViewPanel extends JPanel {
	  /**
	 * 
	 */
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
	    
	}

	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    // Draw image 
	    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	    
	    // Draw bords 
	    for (Bord bord : bords) {
	    	bord.draw(g);
	    }
	    pot.draw(g);
	}
}