package View;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import model.Tile;


public class Bord{
	  
	  public static final int BORD_SIZE = 300;
	  public static final int RECT_SIZE = 25;
	  private final Position position;
	  private final PlayGrid playGrid;
	  private final View view_ref;
	  private final Pattern pattern;
	  private final Malus malus;
	  private final int playerID;

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
				assert false : "Can't load the image!";
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

				Font font = new Font("Arial", Font.PLAIN, 25);
				g.setFont(font);
				g.drawString("ECTS: " + score, position.getX() + BORD_SIZE/2 + 15, position.getY() + 50);
			}catch (Exception e) {
				System.out.println("Game not initialized !");
			}
		  }

	  public void setButtons(boolean visible) {
	    malus.setButton(visible);
	    playGrid.setButton(visible);
	  }

	public void updateViewLine(LinkedList<Tile> toSend, int previousIndex, int i, Tile[] malus) {
		playGrid.updateViewLine(toSend, previousIndex, i);
		this.malus.updateViewLine(malus);
	}

	public void updatePattern(HashMap<Tile, Position> toSend) {
		pattern.updatePattern(toSend, this.playGrid);
	}

	public void updateMalus(Tile[] malus) {
		this.malus.updateViewLine(malus);
	}

	public void clearMalus(){
		  malus.clearMalus();
	}

	public void updateViewLine(LinkedList<Tile> toSend, int previousIndex, int i) {
		playGrid.updateViewLine(toSend, previousIndex, i);
		
	}

	public void sendMalusFirstToView(int previous) {
		malus.addMalusFirst(previous);
		
	}

	
}

