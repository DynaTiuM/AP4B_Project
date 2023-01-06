package View;

import java.awt.*;
import java.util.HashMap;

import javax.swing.ImageIcon;

import model.Tile;

public class Pattern {
	private final int RECT_SIZE;
	private final Position position;
	private final Tile_View[][] tile;
	private final View viewRef;

	public Pattern(Position position, View view, int RECT_SIZE) {
		tile = new Tile_View[5][5];
		viewRef = view;
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
  
  
  public void updatePattern(HashMap<Tile, Position> toAdd, PlayGrid playGrid) {
      for (HashMap.Entry<Tile, Position> entry : toAdd.entrySet()) {
          Tile key = entry.getKey();
          Position value = entry.getValue();

  
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
          
          viewRef.getPanel().addT(tile[value.getY()][value.getX()]);
      
	  }

	  for(Line line : playGrid.getLines()) {
		  for(Tile_View tileView : line.getTiles()) {
			  for(int y = 0; y < 5; y++)
				  for(int x = 0; x < 5; x++)
					  if (tile[y][x] != null && tileView != null)
						  if(tileView.color == tile[y][x].color
						  && line.getLength() == y + 1) {
							  viewRef.getPanel().removeT(tileView);
						  }
		  }
	  }
  }
  
  	public Tile_View[][] getTiles() {
  		return this.tile;
  	}
  
}

