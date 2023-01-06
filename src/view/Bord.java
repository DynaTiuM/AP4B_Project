package view;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

import javax.swing.ImageIcon;

import model.Tile;

//Class used to contain the Malus grid, Pattern grid and the PlayGrid of a player (5 Lines)
public class Bord{
	
	  //Size of a Bord
	  private static final int BORD_SIZE = 300;
	  
	  //Size of a Tile
	  private static final int RECT_SIZE = 25;
	  
	  //Position of a Bord on the View Panel
	  private final Position position;
	  
	  //Lines of a player
	  private final PlayGrid playGrid;
	 
	  //Pattern grid of a player
	  private final Pattern pattern;
	  
	  //Malus grid of a player
	  private final Malus malus;
	  
	  //Reference to the View
	  private final View viewRef;
	  
	  //used to identify the player
	  private final int playerID;

	  
	  public Bord(Position position, View viewRef, int playerID) {
	    this.position = position;
	    this.viewRef = viewRef;
		this.playerID = playerID;
	    this.playGrid = new PlayGrid(new Position(position.getX() + RECT_SIZE, position.getY() + RECT_SIZE*4), viewRef,  RECT_SIZE);
	    this.pattern = new Pattern(new Position(position.getX() + 10 + BORD_SIZE / 2, position.getY()+ RECT_SIZE*4), viewRef, RECT_SIZE);
	    this.malus = new Malus(new Position(position.getX() + 10, position.getY() + BORD_SIZE - RECT_SIZE * 2 - 10 ), viewRef, RECT_SIZE);
	  }

	  //Draw the Bord
	  public void draw(Graphics g) {
		  ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Bord.png")));
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
				int score = viewRef.getScore(playerID);

				Font font = new Font("Arial", Font.PLAIN, 25);
				g.setFont(font);
				g.drawString("ECTS: " + score, position.getX() + BORD_SIZE/2 + 15, position.getY() + 50);
			}catch (Exception e) {
				System.out.println("Game not initialized !");
			}
		  }

	  // Update the line i of playGrid
	  public void updateViewLine(LinkedList<Tile> toSend, int previousIndex, int i, Tile[] malus) {
		  playGrid.updateViewLine(toSend, previousIndex, i);
		  this.malus.updateViewLine(malus);
	  }

	  //Update the pattern of the bord
	  public void updatePattern(HashMap<Tile, Position> toSend) {
		  pattern.updatePattern(toSend, this.playGrid);
	  }

	  //Update the line malus of the bord
	  public void updateMalus(Tile[] malus) {
		  this.malus.updateViewLine(malus);
	  }

	  //Clear the line Malus
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

