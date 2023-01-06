package View;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.ColorEnum;

public class Tile_View extends JLabel {
	protected Position position;
	protected ColorEnum color;
	  
	//Constructeur protected pour que Tile ne puisse pas être initialisé
	protected Tile_View(Position position, String path, ColorEnum color, boolean PopUp) {
		int RECT_SIZE = 25;
		if(PopUp) {
			RECT_SIZE = 40;
		}
		this.position = position;
		this.color = color;
		this.setBounds(position.getX(), position.getY(), RECT_SIZE, RECT_SIZE);
		URL url = getClass().getResource(path);

		assert url != null;
		ImageIcon imageIcon3 = new ImageIcon(url); // load the image to a imageIcon
		Image image = imageIcon3.getImage(); // transform it
		Image newimg = image.getScaledInstance(RECT_SIZE, RECT_SIZE,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageIcon3 = new ImageIcon(newimg);  // transform it back
		this.setIcon(imageIcon3);
		this.setVisible(true);
	}
}
