package controller;

import java.util.LinkedList;

import View.View;
import model.Game;
import model.Tile;

public class Controller {
	private Game game_m;
	private View view_m;
	
	public Controller() {
		game_m = new Game(this, 4);
		view_m = new View(this);
		
		
		
		//game_m.test();
	}
	
	
	
	public void setButtonsPot(boolean possible[]) {
		view_m.setButtonsPot(possible);
	}
	
	public void drawTile(LinkedList<Tile> tiles, int position) {
		view_m.setTile(tiles, position);
		
	}
	
	public LinkedList<Tile> getTilesToView(int index){
		System.out.print("controller - ");
		return game_m.getTilesToView(index);
	}
}
