package model;

import java.util.LinkedList;

import controller.Controller;

public class Game {
	
	private Pot pot;
	private Bord[] players;
	private Controller controller;
	private Pattern pattern;
	
	private int current_player;
	
	public Game(Controller ref, int nb_player) {
		this.current_player = 0;
		this.controller = ref;
		
		players = new Bord[4];
		for(int i = 0; i<4; i++) players[i] = new Bord(i, this);
		
		this.pot = new Pot(4, this);
		this.pattern = new Pattern();
		
		test();
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
	
	public void sendSelectionToBord(LinkedList<Tile> tiles) {
		players[current_player].setHand(tiles);
	}
	
	public void sendSelectionToBord(Tile tile) {
		players[current_player].setHand(tile);
	}
	
	public void sendToBag(LinkedList<Tile> tiles) {
		pot.sendToBag(tiles);
	}
	
	public void sendScore(int score) {
		this.pattern.addScore(score);
	}

	public void sendMalus(Tile[] malus) {
		players[this.current_player].sendMalus(malus);
	}
	
	public void sendWinner() {
		
	}
	
	public void sendGrid(Tile[][] grid) {
		players[this.getCurrentPlayer()].getPattern().setGrid(grid);
	}
	
	public void sendLines(Line[] lines) {
		players[this.getCurrentPlayer()].sendLines(lines);
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
