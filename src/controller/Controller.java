package controller;

import java.util.HashMap;
import java.util.LinkedList;

import view.PopupEnd;
import view.Position;
import view.View;
import model.*;

public class Controller {
	private Game gameRef;
	private final View viewRef;
	
	public Controller(int numPlayers)  {
		viewRef = new View(this, numPlayers);
		gameRef = new Game(this, numPlayers);
		
		initialiseButtonsPiles();
		
		try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        	 e.printStackTrace();
        }
			}
	
	public void initialiseButtonsPiles() {
		viewRef.initiateButtons();
	}
	
	
	//Update Pile number position on the view
	public void updatePile(LinkedList<Tile> toUpdate, int position) {
		viewRef.updatePile(toUpdate, position);
	}

	
	//Update Line number i of the currentPlayer on the View
	// also updates the Malus grid of the player
	public void updateViewLine(LinkedList<Tile> toSend, int previousIndex, int i, int currentPlayer, Tile[] malus) {
		viewRef.updateViewLine(toSend, previousIndex, i, currentPlayer, malus);
	}
	
	//update the MiddlePile on the view
	//if delete is true, the content of Middle Pile will be deleted from the view
	public void updateMiddlePileView(LinkedList<Tile> toSend, int previousIndex, boolean delete) {
		viewRef.updateMiddlePile(toSend, previousIndex, delete);
	}
	
	//update the Pattern grid of player number playerID on the view
	//this method will only send the Tiles that were added at the end of the current round
	//the Hasmap associate each tile with its position of the Pattern grid of the player
	public void updatePatternView(int playerID, HashMap<Tile, Position> toSend) {
		viewRef.updatePattern(playerID, toSend);
	}
	
	//update the Malus grid of the current player on the View
	public void updateMalusView(Tile[] malus, int currentPlayer) {
		viewRef.updateMalus(malus, currentPlayer);
	}

	//empties view equivalent of the Malus grid of player number playerID 
	public void clearMalusView(int playerID){
		viewRef.clearMalus(playerID);
	}
	
	//send the Pattern grid, the Malus grid as well as the Lines of the current player to build a Popup
	//this popup is a bigger version of the current player's bord
	//this popup possesses buttons allowing the current player to choose where to put his selection of tiles
	public void updatePopup(Tile[][] pattern, Tile[] malus, Line[] grid, Tile hand) {
		viewRef.updatePopup(pattern, malus, grid, hand);
	}

	//get the score of player number playerId
	public int getScore(int playerID){
		return gameRef.getScore(playerID);
	}
	

	
	//used to determine the current_player
	public int getCurrentPlayer() {
		return gameRef.getCurrentPlayer();
	}

	
	

	//Update the Line number i of the current_player
	public void updateViewLine(LinkedList<Tile> toSend, int previousIndex, int i, int currentPlayer) {
		viewRef.updateViewLine(toSend, previousIndex, i, currentPlayer);
	}

	//Used when a player is the first to choose a pile from the middle
	public void sendMalusFirstToView(int previous, int currentPlayer) {
		viewRef.sendMalusFirstToView(previous, currentPlayer);
	}

	//Used to display the score of players as well as the winner on a Popup
	public void displayEndOfGame(model.Bord[] bords) {
		viewRef.displayEndOfGame(bords);
	}

	//Called after the end of a game 
	public void stopGame(){
		gameRef = null;
	}
	
	//Functions to return various ActionListeners
	
	public ActionSelectionTile actionSelectionTile(int ID, int numberPile) {
		return new ActionSelectionTile(gameRef, ID, numberPile);
	}
	public ActionSelectionMiddlePile actionSelectionMiddlePile(int ID) {
		return new ActionSelectionMiddlePile(gameRef, ID);
	}
	
	public ActionLine actionLine(int ID) {
		return new ActionLine(gameRef, ID, viewRef);
	}

	public ActionEnd actionEnd(int ID, PopupEnd popupEnd) {
		return new ActionEnd(viewRef, popupEnd, ID);
	}
	
	public ActionMalus actionMalus() {
		return new ActionMalus(gameRef, viewRef);
	}
}
