package View;

import java.awt.*;
import java.util.HashMap;
import javax.swing.ImageIcon;

import model.Tile;

public class Pattern {
	private static final int RECT_SIZE = Bord.RECT_SIZE;
	private Position position;
	private Bord bord_ref;

	public Pattern(Bord ref, Position position) {
		bord_ref = ref;
		this.position = position;
	}

  public void draw(Graphics g) {  	  
	ImageIcon icon = new ImageIcon("src\\Images\\Pattern.png");
    if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
      System.out.println("Error Patern : " + MediaTracker.ERRORED);
    } else {
      // The image was successfully loaded
      Image pattern = icon.getImage();
      for(int i = 0; i < 6; i++) {
    		g.drawImage(pattern, position.getX(), position.getY(), RECT_SIZE*5, RECT_SIZE*5, null);
      }
      }

  }
  
  public void drawPattern(Graphics g, Tile[][] pattern) {
	  
	  for(int i =0; i<5; i++) {
		  for(int j = 0; j<5; j++) {
			  if(pattern[i][j]!=null) {
				  switch (pattern[i][j].getColorEnum()){
				  case O: new Orange(new Position(position.getX() + j * (RECT_SIZE * 2), position.getY() + i * (RECT_SIZE * 2)));
				  	break;
				  case B:  new Purple(new Position(position.getX() + j * (RECT_SIZE * 2), position.getY() + i * (RECT_SIZE * 2)));
				  	break;
				  case Bl: new Blue(new Position(position.getX() + j * (RECT_SIZE * 2), position.getY() + i * (RECT_SIZE * 2)));
				  	break;
				  case Y: new Yellow(new Position(position.getX() + j * (RECT_SIZE * 2), position.getY() + i * (RECT_SIZE * 2)));
				  	break;
				  case G: new Green(new Position(position.getX() + j * (RECT_SIZE * 2), position.getY() + i * (RECT_SIZE * 2)));
					  break;
				  }
			  }
		  }
	  }
  }
  
  public void updatePattern(HashMap<Tile, Position> to_add) {
      for (HashMap.Entry<Tile, Position> entry : to_add.entrySet()) {
          Tile key = entry.getKey();
          Position value = entry.getValue();
          System.out.println("Clé: " + key.getColorEnum() + ", Valeur: " + value.getX() + " ; " + value.getY());
      
          // Afficher les pairs clé-valeur
          switch (key.getColorEnum()){
		  case O: new Orange(new Position(position.getX() + value.getX() * (RECT_SIZE * 2), position.getY() + value.getY() * (RECT_SIZE * 2)));
		  	break;
		  case B:  new Purple(new Position(position.getX() + value.getX() * (RECT_SIZE * 2), position.getY() + value.getY() * (RECT_SIZE * 2)));
		  	break;
		  case Bl: new Blue(new Position(position.getX() + value.getX() * (RECT_SIZE * 2), position.getY() + value.getY() * (RECT_SIZE * 2)));
		  	break;
		  case Y: new Yellow(new Position(position.getX() + value.getX() * (RECT_SIZE * 2), position.getY() + value.getY() * (RECT_SIZE * 2)));
		  	break;
		  case G: new Green(new Position(position.getX() + value.getX() * (RECT_SIZE * 2), position.getY() + value.getY() * (RECT_SIZE * 2)));
			  break;
		  }
	  } 

      bord_ref.repaint();
	  
  }
  
}

