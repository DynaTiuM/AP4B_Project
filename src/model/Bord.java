package model;

import java.util.LinkedList;

public class Bord {
	
	private Line[] play_grid;
	private Malus malus_grid_m;
	private Pattern pattern_grid_m;
	
	private Game game_ref;
	
	private int playerID;
	
	private LinkedList<Tile> excedent_tiles_selection;
	private LinkedList<Tile> hand_of_player;
	
	public Bord(int number, Game ref) {
		
		playerID = number;
		game_ref = ref;
		
		play_grid = new Line[5];
		
		for(int i = 0; i < 5; i++) {
			play_grid[i] = new Line(malus_grid_m, i+1);
		}
		
		malus_grid_m = new Malus(this);
		
		pattern_grid_m = new Pattern();
		
	}
	
	public void sendMalus(Tile[] malus) {
		for(Tile m : malus) {
			this.malus_grid_m.addTile(m);
		}
	}
	
	public Malus getMalusGrid() {
		return this.malus_grid_m;
	}
	
	public Game getModel() {
		return this.game_ref;
	}
}
