package model;

import controller.Controller;

public class Game {
	
	private Pot pot;
	private Bord[] players;
	private Controller controller;
	
	private int current_player;
	
	public Game(Controller ref, int nb_player) {
		players = new Bord[4];
		this.current_player = 0;
		for(int i = 0; i<4; i++) players[i] = new Bord(i, this);
		
		pot = new Pot(4, this);
	}

	// TODO
	private int determineWinner() {
		/*for(Bord player : players) {
			
		}*/
		return 0;
	}
	
	public int getCurrentPlayer() {
		return this.current_player;
	}
	
	public Bord getPlayer(int current) {
		return players[current];
	}
	
	public Controller getController() {
		return this.controller;
	}
	
	public void sendToBag() {
		
	}
	
	public void sendMalus(Tile[] malus) {
		players[this.current_player].sendMalus(malus);
	}
	
}
