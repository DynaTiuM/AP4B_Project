package View;

import java.awt.*;
import java.util.HashMap;

import javax.swing.ImageIcon;

import model.Tile;

public class Pattern {
	private int RECT_SIZE;
	private Position position;
	private Tile_View[][] tile;

	public Pattern(Position position, int RECT_SIZE) {
		tile = new Tile_View[5][5];
		this.position = position;
		this.RECT_SIZE = RECT_SIZE;
	}

  public void draw(Graphics g) {  	  
	ImageIcon icon = new ImageIcon("src\\Images\\Pattern.png");
    if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
      System.out.println("Error Pattern : " + MediaTracker.ERRORED);
    } else {
      // The image was successfully loaded
      Image pattern = icon.getImage();
      		for(int i = 0; i < 6; i++) {
      			g.drawImage(pattern, position.getX(), position.getY(), RECT_SIZE*5, RECT_SIZE*5, null);
      		}
      }

  }
  
  
  public void updatePattern(HashMap<Tile, Position> to_add, Bord bordRef) {
      for (HashMap.Entry<Tile, Position> entry : to_add.entrySet()) {
          Tile key = entry.getKey();
          Position value = entry.getValue();
          System.out.println("Clé: " + key.getColorEnum() + ", Valeur: " + value.getX() + " ; " + value.getY());
      
          // Afficher les pairs clé-valeur
          switch (key.getColorEnum()){
		  case O: tile[value.getY()][value.getX()] = new Orange(new Position(position.getX() + value.getX() * RECT_SIZE, position.getY() + value.getY() * RECT_SIZE));
		  	break;
		  case M: tile[value.getY()][value.getX()] = new Purple(new Position(position.getX() + value.getX() * RECT_SIZE, position.getY() + value.getY() * RECT_SIZE));
		  	break;
		  case B: tile[value.getY()][value.getX()] = new Blue(new Position(position.getX() + value.getX() * RECT_SIZE, position.getY() + value.getY() * RECT_SIZE));
		  	break;
		  case Y: tile[value.getY()][value.getX()] = new Yellow(new Position(position.getX() + value.getX() * RECT_SIZE, position.getY() + value.getY() * RECT_SIZE));
		  	break;
		  case G: tile[value.getY()][value.getX()] = new Green(new Position(position.getX() + value.getX() * RECT_SIZE, position.getY() + value.getY() * RECT_SIZE));
			  break;
		  }
          
          bordRef.addT(tile[value.getY()][value.getX()]);
      
	  }
      
      PlayGrid play_grid = bordRef.getPlayGrid();

	  for(Line line : play_grid.getLines()) {
		  for(Tile_View tileView : line.getTiles()) {
			  for(int y = 0; y < 5; y++)
				  for(int x = 0; x < 5; x++)
					  if (tile[y][x] != null)
						  if(tileView.getTexture() == tile[y][x].getTexture() 
						  && line.getLength() == y + 1) {
							  System.out.println("SAME TEXTURE!");
							  bordRef.removeT(tileView);
						  }
		  }
	  }
  }
  
}

