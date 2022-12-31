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
		
		view_m.initiateButtons();
		
		try {

            Thread.sleep(1000);
        } catch (InterruptedException e) {
        	 e.printStackTrace();
        }
		
		//game_m.test();
		view_m.showBordInPopUp();
		//game_m.endOfSet();

	}
	
	public void setButtonsPot(boolean possible[]) {
		view_m.setButtonsPot(possible);
	}
	
	public void drawTile(LinkedList<Tile> tiles, int position) {
		view_m.setTile(tiles, position);
		
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
	
	public void updatePatternView(int playerID, HashMap<Tile, Position> to_send) {
		view_m.updatePattern(playerID, to_send);
	}
	
	public void updateMalus(int playerID) {
		view_m.updateMalus(playerID);
	}
	
	public ActionSelectionPile actionSelectionPile(int number) {
		return new ActionSelectionPile(game_m, number, view_m);
	}
	
}
