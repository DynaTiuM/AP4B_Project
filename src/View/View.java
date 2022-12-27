package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class View extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final int HEIGHT = 720;
	protected static final int WIDTH = 1080;
	private static int numberOfPlayers = 4; 
	ViewPanel ContentPanel;
	
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
	    pot.setButtons(true);
	    if (iconTable.getImageLoadStatus() == MediaTracker.ERRORED) {
		      // There was an error loading the image
		    } else {
		      // The image was successfully loaded
		      Image image = Toolkit.getDefaultToolkit().getImage("src\\Images\\Table.png");
		      ViewPanel panel = new ViewPanel(image, bords, pot);
		      ContentPanel = panel;
			  setContentPane(panel);
		    }
	}
	
	//Cette fonction est appelée pour affiché en grand le Bord du joueur actif avec des boutons
	public void showBordInPopUp() {
		ContentPanel.showBordInPopUp();
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
	    
	    this.add(pot);
	    
	}
	
	//Cette fonction est appelée pour affiché en grand le Bord du joueur actif avec des boutons
	public void showBordInPopUp() {
		  // Créer un panel pour afficher le bord en grand
		  PopupPanel panel = new PopupPanel();
		  panel.setLayout(new BorderLayout());
		  
		  // Afficher la fenêtre pop-up avec le panel contenant le bord en grand
		  JOptionPane.showMessageDialog(null, panel, "Bord en grand", JOptionPane.PLAIN_MESSAGE);
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
	  // Array to store the buttons
	  private JButton[] buttons;

	// Timer to blink the buttons
	  private Timer blinkTimer;
	
	
	// Constructor that takes in image, bords array, and pot object
	public PopupPanel() {
		setLayout(null);
	    setPreferredSize(new Dimension(500, 500));
	    this.playGrid = new PlayGrid(new Position(POPUP_RECT_SIZE, POPUP_RECT_SIZE*4), POPUP_RECT_SIZE);
	    this.pattern = new Pattern(new Position(10 + POPUP_BORD_SIZE / 2, POPUP_RECT_SIZE*4), POPUP_RECT_SIZE);
	    this.malus = new Malus(new Position(18, POPUP_BORD_SIZE - POPUP_RECT_SIZE * 2 - 20 ), POPUP_RECT_SIZE);
	    
	    buttons = new JButton[6];
	    
	    // Initialize the buttons and add them to the array
	    for (int i = 0; i < buttons.length; i++) {
	      buttons[i] = new JButton();
	      // Set the bounds of the button
	      buttons[i].setBounds(0, POPUP_BORD_SIZE/2 - POPUP_RECT_SIZE*2 + POPUP_RECT_SIZE * i, POPUP_RECT_SIZE, POPUP_RECT_SIZE);
	      add(buttons[i]);
	      buttons[i].setVisible(true);
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