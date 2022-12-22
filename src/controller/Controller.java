package controller;

import model.Game;

public class Controller {
	private Game game_m;
	
	public Controller() {
		game_m = new Game(this, 4);
	}
}
