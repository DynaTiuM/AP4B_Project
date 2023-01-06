package view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.ColorEnum;

public class Tile_View extends JLabel {
	
	//Position of Tile
	protected Position position;
	
	//Color of Tile
	protected ColorEnum color;
	  
	//Protected so that Tile can't be initialized
	protected Tile_View(Position position, String path, ColorEnum color, boolean PopUp) {
		
		int RECT_SIZE = 25;
		
		if(PopUp) {
			RECT_SIZE = 40;
		}
		this.position = position;
		this.color = color;
		this.setBounds(position.getX(), position.getY(), RECT_SIZE, RECT_SIZE);
		
		ImageIcon imageIcon3 = new ImageIcon(getClass().getClassLoader().getResource(path)); // load the image to a imageIcon
		Image image = imageIcon3.getImage(); // transform it
		Image newimg = image.getScaledInstance(RECT_SIZE, RECT_SIZE,  java.awt.Image.SCALE_SMOOTH); // rescales it
		imageIcon3 = new ImageIcon(newimg);  // transform it back
		this.setIcon(imageIcon3);
		this.setVisible(true);
	}
}
