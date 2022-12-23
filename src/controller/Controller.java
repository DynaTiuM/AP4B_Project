package controller;

import model.Game;
import model.Line;
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
		view.getBord(current_player).displayMalus(malus);
	}
	
	public void sendGridToView(int current_player, Tile[][] grid) {
		view.getBord(current_player).displayGrid(grid);
	}
	
	public void sendLinesToView(int current_player, Line[] lines) {
		view.getBord(current_player).displayLines(lines);
	}
	
	public void sendScoreToView(int current_player, int score) {
		view.getBord(current_player).displayScore(score);
	}
	
	public void updateCurrentPlayer() {
		this.current_player = this.game_m.getCurrentPlayer();
	}
}
