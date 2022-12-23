package controller;

import java.util.LinkedList;

import model.Game;
import model.Line;
import model.Pile;
import model.Tile;

public class Controller {
	private Game game_m;
	private GamePanel view;
	private int current_player;
	
	private ActionSelectionPile actionSelectionPile;
	private ActionDisplayMiddlePile actionDisplayMiddlePile;
	private ActionSelectionMiddlePile actionSelectionMiddlePile;
	private ActionDisplayPile actionDisplayPile;
	private ActionLine actionLine;
	private ActionMalus actionMalus;
	
	public Controller() {
		game_m = new Game(this, 4);
		actionMalus = new ActionMalus(game_m);
		actionSelectionPile = new ActionSelectionPile(game_m);
	}
	
	public int getCurrentPlayer() {
		return this.game_m.getCurrentPlayer();
	}
	
	
	public void sendMalusToView(int current_player, Tile[] malus) {
		view.getBord(current_player).getMalus.draw(malus);
	}
	
	public void sendGridToView(int current_player, Tile[][] grid) {
		view.getBord(current_player).getGrid.draw(grid);
	}
	
	public void sendLinesToView(int current_player, Line[] lines) {
		view.getBord(current_player).getPlayGrid.draw(lines);
	}
	
	public void sendScoreToView(int current_player, int score) {
		view.getScore.draw(score);
	}
	
	public void sendMiddlePileToView(LinkedList<Pile> middlePile) {
		view.getPot().getMiddlePile().draw(middlePile);
	}
	
	public void sendPilesToView(Pile[] piles) {
		view.getPot().draw(piles);
	}
	
	public void updateCurrentPlayer() {
		this.current_player = this.game_m.getCurrentPlayer();
	}
}
