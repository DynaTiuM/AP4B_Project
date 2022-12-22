package model;

import java.util.LinkedList;

public class Pile {
	
	private Tile[] tiles;
	
	private LinkedList<Tile> tiles_bord;
	private LinkedList<Tile> tiles_middle;
	
	private Pot pot_ref;
	private MiddlePile middle_ref;
	private Game game_ref;
	
	private int current_index;
	
	public Pile(Game game, MiddlePile middle, Pot pot) {
		
		tiles = new Tile[4];
		
		tiles_bord = new LinkedList<Tile>();
		tiles_middle = new LinkedList<Tile>();
		
		pot_ref = pot;
		middle_ref = middle;
		game_ref = game;
	}
	
	
	public boolean hasContent() {
		return tiles[0]!=null;
	}
	
	public void setContent(Tile to_add) {
		tiles[current_index] = to_add;
	}
	
	public Tile[] getContent(){
		return this.tiles;
	}
	
	public void getSelection(Tile chosen) {
		for(int i =0; i<4; i++) {
			if(tiles[i].getColor() == chosen.getColor()) {
				tiles_bord.add(tiles[i]);
			} else tiles_middle.add(tiles[i]);
			
			tiles[i] = null;
		}
	}
	
	private void sendToMiddle() {
		this.middle_ref.addContent(tiles_middle);
	}
	
}
