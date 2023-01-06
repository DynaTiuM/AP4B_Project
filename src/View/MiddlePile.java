package View;

import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.*;

import controller.ActionSelectionMiddlePile;
import model.Tile;
import model.ColorEnum;
public class MiddlePile {
	private static final int RECT_SIZE = Bord.RECT_SIZE;
	private int offsetY = 0;
	private final Position position;
	private final Pot pot_m;
	
	private final HashMap<Tile_View, JButton> tiles;

  	public MiddlePile(Pot ref, Position position) {
    	this.position = position;
    	this.tiles = new HashMap<>();
    	pot_m = ref;
  	}

  	public void updatePile(LinkedList<Tile> to_add, int previous_index, boolean delete) {
  		if(delete) {
  			for (HashMap.Entry<Tile_View, JButton> entry : this.tiles.entrySet()) {
  				pot_m.removeB(entry.getValue());
  				pot_m.removeT(entry.getKey());
  				this.offsetY = 0;
  			}
  		  		
  			tiles.clear();
  		}
  		Tile_View tile = null;
  		int offsetX = (previous_index % 7) * RECT_SIZE;
  		
	  
  		for(Tile p: to_add) {
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
  			
  			// For every tile, we associate it a button

			JButton button_tile = new JButton();
			button_tile.setOpaque(false);
			button_tile.setContentAreaFilled(false);
			//enlever la bordure
			button_tile.setBorderPainted(false);

  			button_tile.setBounds(position.getX() + offsetX, position.getY() + this.offsetY, RECT_SIZE, RECT_SIZE);
  			int ID = tiles.size();
  			
  			// Initiation of every button, with their ID and an ActionListener
  			initiateButton(button_tile, ID);
  			
  			if(p.getColorEnum() == ColorEnum.MALUS) {
  				button_tile.setVisible(false);
  				button_tile.setEnabled(false);
  			}
  			
  			// We add the couple Tile_View/JButton into a HashMap
  			tiles.put(tile, button_tile);
  			pot_m.addB(button_tile);
			pot_m.addT(tile);
  			
  			
  			offsetX += RECT_SIZE;
  			if (offsetX >= 7* RECT_SIZE) {
  				offsetX = 0;
  				this.offsetY += RECT_SIZE;
	  		}
  		}
  	}
  	
  	public void updatePile(LinkedList<Tile> to_remove) {
  		Tile ref = to_remove.get(0);
  		Tile_View tile = null;
  		
  		switch (ref.getColorEnum()){
		  case O: tile = new Orange(new Position(position.getX(), position.getY()));
		  	break;
		  case M: tile = new Purple(new Position(position.getX(), position.getY()));
		  	break;
		  case B: tile = new Blue(new Position(position.getX(), position.getY()));
		  	break;
		  case Y: tile = new Yellow(new Position(position.getX(), position.getY()));
		  	break;
		  case G: tile = new Green(new Position(position.getX(), position.getY()));
			  break;
		}
  		
  		HashMap<Tile_View, JButton> tiles_tmp = new HashMap<>();
  		
  		for (HashMap.Entry<Tile_View, JButton> entry : this.tiles.entrySet()) {
  			Tile_View key = entry.getKey();
            JButton value = entry.getValue();

			assert tile != null;
			if(key.color == tile.color) {
            	tiles_tmp.put(key, value);
            }
  		}
  		
  		for (HashMap.Entry<Tile_View, JButton> entry : tiles_tmp.entrySet()) {
  			pot_m.removeT(entry.getKey());
  			pot_m.removeB(entry.getValue());
  			this.tiles.remove(entry.getKey());
  		}
  	}
  	
  	public void initiateButton(JButton button, int ID) {
  		ActionSelectionMiddlePile action = pot_m.actionSelectionMiddlePile(ID);
			
		button.addActionListener(action);
  	}
}