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
	/**
	 * Create the frame.
	 */
	public View() {
		setBackground(new Color(255, 255, 255));
		setTitle("WIDTH");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Empecher l'utilisateur de redimensioner la fenetre
		setResizable(false);
		//Afficher la fenetre au centre de l'écrant
		setLocationRelativeTo(null);

		setTitle("Azul");
		Image icon = Toolkit.getDefaultToolkit().getImage("src\\Images\\Azul.png");

		ImageIcon iconTable = new ImageIcon("src\\Images\\Table.png");
		// Set the frame's icon
		setIconImage(icon);

		Bord topLeft = new Bord(new Position(30, 20));
		Bord topRight = new Bord(new Position(WIDTH - Bord.BORD_SIZE - 50, 20));
		Bord bottomLeft = new Bord(new Position(30, HEIGHT -Bord.BORD_SIZE - 50));
		Bord bottomRight = new Bord(new Position(WIDTH - Bord.BORD_SIZE - 50, HEIGHT -Bord.BORD_SIZE - 50));
		//Bord center = new Bord(new Position(0, 0));

		//getContentPane().add(center);
		topLeft.setButtons(true);
		
		 // Create the Pot object
	    Pot pot = new Pot(new Position(WIDTH/2, HEIGHT/2));
	    
	    if (iconTable.getImageLoadStatus() == MediaTracker.ERRORED) {
		      // There was an error loading the image
		    } else {
		      // The image was successfully loaded
		      Image image = Toolkit.getDefaultToolkit().getImage("src\\Images\\Table.png");
		      ViewPanel panel = new ViewPanel(image, topLeft, topRight, bottomLeft, bottomRight, pot);
			  setContentPane(panel);
		    }
	}

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}


class ViewPanel extends JPanel {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;
	  private Bord player1;
	  private Bord player2;
	  private Bord player3;
	  private Bord player4;
	  private Pot pot;

	  public ViewPanel(Image image, Bord player1, Bord player2, Bord player3, Bord player4, Pot pot) {
	    this.image = image;
	    this.player1 = player1;
	    this.player2 = player2;
	    this.player3 = player3;
	    this.player4 = player4;
	    this.pot = pot;
	    
	  }

	  @Override
	  public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	    player1.draw(g);
	    player2.draw(g);
	    player3.draw(g);
	    player4.draw(g);
	    pot.draw(g);
	  }
	}