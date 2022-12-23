package model;

import java.util.LinkedList;

import controller.Controller;

public class Game {
	
	private Pot pot;
	private Bord[] players;
	private Controller controller;
	
	private int current_player;
	
	public Game(Controller ref, int nb_player) {
		
		current_player = 0;
		
		players = new Bord[4];
		for(int i = 0; i<4; i++) players[i] = new Bord(i, this);
		
		pot = new Pot(4, this);
		
		test();
	}
	
	public void sendSelectiontoBord(LinkedList<Tile> tiles) {
		players[current_player].setHand(tiles);
	}
	
	public void sendSelectiontoBord(Tile tile) {
		players[current_player].setHand(tile);
	}
	
	public void sendToBag(LinkedList<Tile> tiles) {
		pot.sendToBag(tiles);
	}
	
	public void endOfSet() {
		
		
		for(Bord p: players) {
			p.endOfSet();
		}

		current_player++;
	
	
	}
	
	public void test() {
		pot.test();
		players[current_player].endOfSet();
		
	}

	public void sendSelectiontoBordTest(LinkedList<Tile> tiles_bord) {
		players[current_player].test(tiles_bord);
		
	}
	
	
	
}
