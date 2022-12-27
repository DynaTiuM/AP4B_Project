package model;

import java.util.LinkedList;

public class Bord {
	
	private Line[] play_grid;
	private Malus malus_grid_m;
	private Pattern pattern_grid_m;
	
	private Game game_ref;
	
	private int playerID;
	
	private int current;
	
	private LinkedList<Tile> excedent_tiles_selection;
	private LinkedList<Tile> hand_of_player;
	
	public Bord(int number, Game ref) {
		
		current = 0;
		
		playerID = number;
		game_ref = ref;
		
		malus_grid_m = new Malus(this);
		pattern_grid_m = new Pattern();
		
		play_grid = new Line[5];
		for(int i=0; i<5; i++) {
			play_grid[i] = new Line(malus_grid_m, pattern_grid_m, i+1);
		}
		
		
		
		
		
		this.excedent_tiles_selection = new LinkedList<Tile>();
		
		this.hand_of_player = new LinkedList<Tile>();
		
	}
	
	public void setHand(LinkedList<Tile> tiles) {
		hand_of_player.clear();
		hand_of_player = tiles;
	}
	
	public void test(LinkedList<Tile> tiles) {
		setHand(tiles);
		displayHand();
		playHandIndex(current);
		if(current+1>=4) {
	    	current = 0;
	    }else current++;
		display();
	}
	
	public void test(Tile tile) {
		setHand(tile);
		displayHand();
		playHandIndex(current);
		if(current+1>=4) {
	    	current = 0;
	    }else current++;
		display();
	}
	
	public void setHand(Tile tile) {
		hand_of_player.clear();
		hand_of_player.add(tile);
	}
	
	public void displayHand() {
		System.out.print("Hand : ");
		for(Tile p: hand_of_player) System.out.print(p.getColorEnum() + " ");
		System.out.println();
		
	}
	
	public void display() {
		for(Line p: play_grid) p.display();
		malus_grid_m.display();

	}
	
	
	
	public void patternDisplay() {
		pattern_grid_m.display();
	}
	
	
	public void setExcedent(LinkedList<Tile> tiles) {
		excedent_tiles_selection.addAll(tiles);
	}
	
	public void setExcedent(Tile tile) {
		excedent_tiles_selection.add(tile);
	}
	
	public void clearExcedent() {
		excedent_tiles_selection.clear();
	}
	
	public void playHandIndex(int index) {
		play_grid[index].addChoice(hand_of_player);
	}
	
	public void endOfSet() {
		for(Line p: play_grid) {
			if(p.checkFull()) {
				game_ref.sendToBag(p.clear());
			}
		}
		
		game_ref.sendToBag(malus_grid_m.clear());
		pattern_grid_m.scoreMalus(malus_grid_m.computateMalus());
		
		
		
		//display();
	}

	public Tile[][] getPatternToView() {
		// TODO Auto-generated method stub
		return pattern_grid_m.getGrid();
	}
	
}
