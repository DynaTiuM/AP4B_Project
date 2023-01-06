package view;

import java.util.HashMap;

import java.util.LinkedList;

import javax.swing.*;

import controller.ActionSelectionMiddlePile;
import model.Tile;
import model.ColorEnum;

//Class used to display the MiddlePile on the View Panel
public class MiddlePile {
	//Size of a cell in MiddlePile
	private static final int RECT_SIZE = 25;
	
	//allows us to track on which row to add new tiles
	private int offsetY = 0;
	
	//Position of the MiddlePile on the viewPanel
	private final Position position;
	
	//Reference to the pot
	private final Pot potRef;
	
	//Contains Tiles and their positions, to be able to access their references to remove them from the ViewPanel
	private final HashMap<Tile_View, JButton> tiles;

	
  	public MiddlePile(Pot potRef, Position position) {
    	this.position = position;
    	this.tiles = new HashMap<>();
    	this.potRef = potRef;
  	}

  	//either add or delete the tiles on the View Panel
  	public void updatePile(LinkedList<Tile> toAdd, int previousIndex, boolean delete) {
  		
  		//if we choose to delete : 
  		if(delete) {
  			for (HashMap.Entry<Tile_View, JButton> entry : this.tiles.entrySet()) {
  				
  				//remove current Button
  				potRef.removeB(entry.getValue());
  				
  				//Remove current Tile
  				potRef.removeT(entry.getKey());
  				this.offsetY = 0;
  			}
  		  		
  			tiles.clear();
  		}
  		
  		Tile_View tile = null;
  		int offsetX = (previousIndex % 7) * RECT_SIZE;
  		
  		//Parse all the Tiles to add to Panel View
	  
  		for(Tile p: toAdd) {
  			switch (p.getColorEnum()){
  				case O: tile = new Orange(new Position(position.getX() + offsetX, position.getY() + this.offsetY));
  					break;
  				case M: tile = new Purple(new Position(position.getX() + offsetX, position.getY() + this.offsetY));
  					break;
  				case B: tile = new Blue(new Position(position.getX() + offsetX, position.getY() + this.offsetY));
  					break;
  				case Y: tile = new Yellow(new Position(position.getX() + offsetX, position.getY() + this.offsetY));
  					break;
  				case G: tile = new Green(new Position(position.getX() + offsetX, position.getY() + this.offsetY));
  					break;
  				case MALUS: tile = new MalusTile(new Position(position.getX() + offsetX, position.getY() + this.offsetY));
  		  	  		break;
  			}
  			
  			// For every tile, we associate it with a button
			JButton buttonTile = new JButton();
			buttonTile.setOpaque(false);
			buttonTile.setContentAreaFilled(false);
			
			//Remove border
			buttonTile.setBorderPainted(false);

			buttonTile.setBounds(position.getX() + offsetX, position.getY() + this.offsetY, RECT_SIZE, RECT_SIZE);
  			int ID = tiles.size();
  			
  			// Initiation of every button, with their ID and an ActionListener
  			initiateButton(buttonTile, ID);
  			
  			if(p.getColorEnum() == ColorEnum.MALUS) {
				buttonTile.setVisible(false);
				buttonTile.setEnabled(false);
  			}
  			
  			// We add the couple Tile_View/JButton into a HashMap
  			tiles.put(tile, buttonTile);
  			potRef.addB(buttonTile);
  			potRef.addT(tile);
  			
  			//Change the offsetX and offsetY after having set a Tile
  			offsetX += RECT_SIZE;
  			if (offsetX >= 7* RECT_SIZE) {
  				offsetX = 0;
  				this.offsetY += RECT_SIZE;
	  		}
  		}
  	}

  	public void initiateButton(JButton button, int ID) {
  		ActionSelectionMiddlePile action = potRef.actionSelectionMiddlePile(ID);
		button.addActionListener(action);
  	}
}