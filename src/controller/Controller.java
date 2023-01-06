package controller;

import java.util.HashMap;
import java.util.LinkedList;

import View.PopupEnd;
import View.Position;
import View.View;
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

	public void updatePile(LinkedList<Tile> toUpdate, int position) {
		viewRef.updatePile(toUpdate, position);
	}

	public void updateViewLine(LinkedList<Tile> toSend, int previousIndex, int i, int currentPlayer, Tile[] malus) {
		viewRef.updateViewLine(toSend, previousIndex, i, currentPlayer, malus);
	}
	
	public void updateMiddlePileView(LinkedList<Tile> toSend, int previousIndex, boolean delete) {
		viewRef.updateMiddlePile(toSend, previousIndex, delete);
	}
	
	public void updatePatternView(int playerID, HashMap<Tile, Position> toSend) {
		viewRef.updatePattern(playerID, toSend);
	}
	
	public void updateMalusView(Tile[] malus, int currentPlayer) {
		viewRef.updateMalus(malus, currentPlayer);
	}

	public void clearMalusView(int playerID){
		viewRef.clearMalus(playerID);
	}
	
	public void updatePopup(Tile[][] pattern, Tile[] malus, Line[] grid, Tile hand) {
		viewRef.updatePopup(pattern, malus, grid, hand);
	}

	public int getScore(int playerID){
		return gameRef.getScore(playerID);
	}
	
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
	
	public int getCurrentPlayer() {
		return gameRef.getCurrentPlayer();
	}

	public ActionMalus actionMalus() {
		return new ActionMalus(gameRef, viewRef);
	}

	public void updateViewLine(LinkedList<Tile> toSend, int previousIndex, int i, int currentPlayer) {
		viewRef.updateViewLine(toSend, previousIndex, i, currentPlayer);
	}

	public void sendMalusFirstToView(int previous, int currentPlayer) {
		viewRef.sendMalusFirstToView(previous, currentPlayer);
	}

	public void displayEndOfGame(model.Bord[] bords) {
		viewRef.displayEndOfGame(bords);
	}

	public void stopGame(){
		gameRef = null;
	}
}
