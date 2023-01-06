package view;

import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

import javax.swing.ImageIcon;

import model.Tile;

//Class displaying the Pattern Gri of a Player on the View Panel
public class Pattern {
	
	//Size of a cell in Pattern
	private final int RECT_SIZE;
	
	//Position of Pattern on the View Panel
	private final Position position;
	
	//Stores the tiles , to be able to access their references to remove them from the ViewPanel
	private final Tile_View[][] tile;
	
	//Reference to the view
	private final View viewRef;

	public Pattern(Position position, View viewRef, int RECT_SIZE) {
		tile = new Tile_View[5][5];
		this.viewRef = viewRef;
		this.position = position;
		this.RECT_SIZE = RECT_SIZE;
	}

  public void draw(Graphics g) {
	  
	//Load image of the Pattern, Tiles will be added in front of this image
	ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Pattern.PNG")));
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
    	  //gets key of current Entry
          Tile key = entry.getKey();
          
          //gets Position of current Entry
          Position value = entry.getValue();

  
          //Sets a Tile on the coordinates
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
          
          //Add the tile
          viewRef.getPanel().addT(tile[value.getY()][value.getX()]);
      
	  }

      // Deleting tiles that are full on the playGrid
      // For every line
	  for(Line line : playGrid.getLines()) {
		  // We analyze every tile on this line
		  for(Tile_View tileView : line.getTiles()) {
			  // And we check this tile with every tile on the pattern
			  for(int y = 0; y < 5; y++)
				  for(int x = 0; x < 5; x++)
					  // If both are not null, their color are same and the line is full
					  if (tile[y][x] != null && tileView != null)
						  if(tileView.color == tile[y][x].color
						  && line.getLength() == y + 1) {
							  // We remove this tile on the playGrid view from the playGrid
							  viewRef.getPanel().removeT(tileView);
						  }
		  }
	  }
  }
  
  	public Tile_View[][] getTiles() {
  		return this.tile;
  	}
  
}

