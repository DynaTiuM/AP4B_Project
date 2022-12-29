package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Pile extends JPanel{
	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	private static final int RECT_SIZE = Bord.RECT_SIZE;
	private static int buttonIdCounter = 0;
  private Position position;
  private JButton button;

  public Pile(Position position) {
	  super();
	    setOpaque(false);
	  setPreferredSize(new Dimension(RECT_SIZE * 4, RECT_SIZE * 4));
    this.position = position;
    button = new JButton();

    button.setBounds(position.getX() + RECT_SIZE, position.getY() + RECT_SIZE, RECT_SIZE, RECT_SIZE);
    button.setActionCommand("buttonPile" + Integer.toString(buttonIdCounter));
    buttonIdCounter++;
    this.add(button);
  }

  public void draw(Graphics g) {
	  ImageIcon icon = new ImageIcon("src\\Images\\Pile.png");
	    if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
	        // There was an error loading the image
	      } else {
	        // The image was successfully loaded
	        Image pile = icon.getImage();
	        g.drawImage(pile, position.getX(), position.getY(), RECT_SIZE*3, RECT_SIZE*3, null);

	        }

	    // Crée 4 tuiles vertes � l'emplacement de la pile
	    Green tile1 = new Green(new Position(position.getX(), position.getY()));
	    Green tile2 = new Green(new Position(position.getX() + (RECT_SIZE * 2), position.getY()));
	    Green tile3 = new Green(new Position(position.getX(), position.getY() + (RECT_SIZE * 2)));
	    Green tile4 = new Green(new Position(position.getX() + (RECT_SIZE * 2), position.getY() + (RECT_SIZE * 2)));

	    // Dessine les 4 tuiles vertes
	    tile1.draw(g);
	    tile2.draw(g);
	    tile3.draw(g);
	    tile4.draw(g);

	  }
  
  @Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    // Draw image 
	    draw(g);
	    }    

public void setButton(boolean value) {
    button.setVisible(value);
  }
}