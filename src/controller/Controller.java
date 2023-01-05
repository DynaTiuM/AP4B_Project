package controller;

import java.util.HashMap;
import java.util.LinkedList;

import View.Position;
import View.View;
import model.*;

public class Controller {
	private Game game_m;
	private View view_m;
	
	public Controller(int numPlayers)  {
		view_m = new View(this, numPlayers);
		game_m = new Game(this, numPlayers);
		
		initialiseButtonsPiles();
		
		try {

            Thread.sleep(1000);
        } catch (InterruptedException e) {
        	 e.printStackTrace();
        }
		
		//game_m.test();

	}
	
	public void initialiseButtonsPiles() {
		view_m.initiateButtons();
	}

	public void updatePile(LinkedList<Tile> to_update, int position) {
		view_m.updatePile(to_update, position);
	}

	public void updateViewLine(LinkedList<Tile> to_send, int previous_index, int i, int current_player, Tile[] malus) {
		view_m.updateViewLine(to_send, previous_index, i, current_player, malus);
	}
	
	public void updateMiddlePileView(LinkedList<Tile> to_send, int previous_index, boolean delete) {
		view_m.updateMiddlePile(to_send, previous_index, delete);
	}
	
	public void updatePatternView(int playerID, HashMap<Tile, Position> to_send) {
		view_m.updatePattern(playerID, to_send);
	}
	
	public void updateMalusView(Tile[] malus, int current_player) {
		view_m.updateMalus(malus, current_player);
	}

	public void clearMalusView(int playerID){
		view_m.clearMalus(playerID);
	}
	
	public void updatePopup(Tile[][] pattern, Tile[] malus, Line[] grid, Tile hand) {
		view_m.updatePopup(pattern, malus, grid, hand);
	}

	public int getScore(int playerID){
		return game_m.getScore(playerID);
	}
	
	public ActionSelectionTile actionSelectionTile(int ID, int numberPile) {
		return new ActionSelectionTile(game_m, ID, numberPile, view_m);
	}
	public ActionSelectionMiddlePile actionSelectionMiddlePile(int ID) {
		return new ActionSelectionMiddlePile(game_m, ID, view_m);
	}
	
	public ActionLine actionLine(int ID) {
		return new ActionLine(game_m, ID, view_m);
	}
	
	public int getCurrentPlayer() {
		return game_m.getCurrentPlayer();
	}
	
	public ActionMalus actionMalus() {
		return new ActionMalus(game_m, view_m);
	}

	public void updateViewLine(LinkedList<Tile> to_send, int previous_index, int i, int current_player) {
		view_m.updateViewLine(to_send, previous_index, i, current_player);
	}

	public void sendMalusFirstToView(int previous, int current_player) {
		view_m.sendMalusFirstToView(previous, current_player);
	}

	public void displayEndOfGame(model.Bord[] bords) {
		view_m.displayEndOfGame(bords);

	}

	public void stopGame(){
		game_m = null;
	}
}
