package View;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import model.Tile;


public class Bord{
	  
	  public static final int BORD_SIZE = 300;
	  public static final int RECT_SIZE = 25;
	  private Position position;
	  private PlayGrid playGrid;
	  private View view_ref;
	  private Pattern pattern;
	  private Malus malus;
	  private int playerID;

	  public Bord(Position position, View view_ref, int playerID) {
	    this.position = position;
	    this.view_ref = view_ref;
		this.playerID = playerID;
	    this.playGrid = new PlayGrid(new Position(position.getX() + RECT_SIZE, position.getY() + RECT_SIZE*4), view_ref,  RECT_SIZE);
	    this.pattern = new Pattern(new Position(position.getX() + 10 + BORD_SIZE / 2, position.getY()+ RECT_SIZE*4), view_ref, RECT_SIZE);
	    this.malus = new Malus(new Position(position.getX() + 10, position.getY() + BORD_SIZE - RECT_SIZE * 2 - 10 ), view_ref, RECT_SIZE);
	    this.setButtons(true);
	  }

	  public void draw(Graphics g) {
		    ImageIcon icon = new ImageIcon("src\\Images\\Bord.png");
		    if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
		      // There was an error loading the image
		    } else {
		      // The image was successfully loaded
		      Image bord = icon.getImage();
		      g.drawImage(bord, position.getX(), position.getY(), BORD_SIZE, BORD_SIZE, null);
		      
		    }
		    malus.draw(g);
		    playGrid.draw(g);
		    pattern.draw(g);
			try{
				int score = view_ref.getScore(playerID);
			    //g.setColor(Color.getHSBColor(45, 65, 41));
				Font font = new Font("Arial", Font.PLAIN, 25);
				g.setFont(font);
				g.drawString("ECTS: " +String.valueOf(score), position.getX() + BORD_SIZE/2 + 15, position.getY() + 50);
			}catch (Exception e) {
				System.out.println("Game not initialized !");
			}
		  }
	  
	  public void addT(Tile_View tile) {
		  view_ref.getPanel().addT(tile);
	  }
	  public void removeT(Tile_View tile) {
		  view_ref.getPanel().removeT(tile);
	  }
	  
	  public PlayGrid getPlayGrid() {
		  return playGrid;
	  }

	  public void setButtons(boolean visible) {
	    malus.setButton(visible);
	    playGrid.setButton(visible);
	  }

	public void updateViewLine(LinkedList<Tile> to_send, int previous_index, int i, LinkedList<Tile> linkedList, int previous_index_2) {
		playGrid.updateViewLine(to_send, previous_index, i);
		if(previous_index_2 < 7) malus.updateViewLine(linkedList, previous_index_2);
	}

	public void updatePattern(HashMap<Tile, Position> to_send) {
		pattern.updatePattern(to_send, this.playGrid);
	}

	public void updateMalus(LinkedList<Tile> to_send, int previous_index) {
		if(previous_index < 7) malus.updateViewLine(to_send, previous_index);
	}

	public void clearMalus(){
		  malus.clearMalus();
	}

	public void updateViewLine(LinkedList<Tile> to_send, int previous_index, int i) {
		playGrid.updateViewLine(to_send, previous_index, i);
		
	}

	
}

