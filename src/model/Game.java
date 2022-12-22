package model;

import controller.Controller;

public class Game {
	
	private Pot pot;
	private Bord[] players;
	private Controller controller;
	
	private int current_player;
	
	public Game(Controller ref, int nb_player) {
		players = new Bord[4];
		for(int i = 0; i<4; i++) players[i] = new Bord(i, this);
		
		pot = new Pot(4, this);
	}
}
