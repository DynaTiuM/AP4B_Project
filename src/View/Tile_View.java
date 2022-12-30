package View;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tile_View extends JLabel {
	private static final int RECT_SIZE = 25;
	  protected Position position;
	  protected ImageIcon texture;
	  
	  //Constructeur protected pour que Tile ne puisse pas être initialisé
	  protected Tile_View(Position position, ImageIcon image2) {
		    this.position = position;
		    //this.setSize(RECT_SIZE, RECT_SIZE);
		    this.setBounds(position.getX(), position.getY(), RECT_SIZE, RECT_SIZE);
		    ImageIcon imageIcon3 = image2; // load the image to a imageIcon
		    Image image = imageIcon3.getImage(); // transform it 
		    Image newimg = image.getScaledInstance(RECT_SIZE, RECT_SIZE,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		    imageIcon3 = new ImageIcon(newimg);  // transform it back
		    this.setIcon(imageIcon3);
		    this.setVisible(true);
		    
		   // System.out.println("created");
		    
		  }

	  public void draw(Graphics g) {
	    // Affiche l'image en la redimensionnant à RECT_SIZE
	    //g.drawImage(texture, position.getX(), position.getY(), RECT_SIZE, RECT_SIZE, null);
		  System.out.print(" draw");
		  //this.setVisible(true);
	  }

	  
	  public ImageIcon getTexture() {
		  return this.texture;
	  }
	  
	  /*public void paintComponent(Graphics g){
		  super.paintComponent(g);
		  this.draw(g);
		  
	  }*/
	  
}
