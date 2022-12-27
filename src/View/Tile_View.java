package View;

import java.awt.Graphics;
import java.awt.Image;

public class Tile_View {
	private static final int RECT_SIZE = Bord.RECT_SIZE;
	  protected Position position;
	  protected Image texture;
	  
	  //Constructeur protected pour que Tile ne puisse pas être initialisé
	  protected Tile_View(Position position) {
		    this.position = position;
		  }

	  public void draw(Graphics g) {
	    // Affiche l'image en la redimensionnant à RECT_SIZE
	    g.drawImage(texture, position.getX(), position.getY(), RECT_SIZE, RECT_SIZE, null);
	  }

	  public void hide(Graphics g) {
	    // Désafiche la tuile en utilisant la position actuelle de la tuile
	    g.clearRect(position.getX(), position.getY(), RECT_SIZE, RECT_SIZE);
	  }
}
