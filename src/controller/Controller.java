package controller;

import java.util.HashMap;
import java.util.LinkedList;

import View.Position;
import View.View;
import model.Game;
import model.Tile;

public class Controller {
	private Game game_m;
	private View view_m;
	
	public Controller()  {
		view_m = new View(this, 4);
		game_m = new Game(this, 4);
		
		try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        	 e.printStackTrace();
        }
		
		game_m.test();
		game_m.endOfSet();
	}
	
	public void setButtonsPot(boolean possible[]) {
		view_m.setButtonsPot(possible);
	}
	
	public void drawTile(LinkedList<Tile> tiles, int position) {
		view_m.setTile(tiles, position);
		
	}
	
	public Tile[][] getPatternToView(){
		return game_m.getPatternToView();
	}
	
	public void updatePile(LinkedList<Tile> to_update, int position) {
		view_m.updtatePile(to_update, position);
	}

	public void updateViewLine(LinkedList<Tile> to_send, int previous_index, int i, int current_player, LinkedList<Tile> linkedList, int previous_index_2) {
		view_m.updateViewLine(to_send, previous_index, i, current_player, linkedList, previous_index_2);
	}
	
	public void updateMiddlePileView(LinkedList<Tile> to_send, int previous_index) {
		view_m.updateMiddlePile(to_send, previous_index);
	}
	
	public void updatePatternView(HashMap<Tile, Position> to_send, int playerID) {
		view_m.updatePattern(to_send, playerID);
	}
	
	public void updateMalusView(int current_player) {
		view_m.updateMalus(current_player);
	}
	
}
