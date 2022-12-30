package model;

import java.util.HashMap;
import java.util.LinkedList;

import View.Position;
import controller.Controller;

// ATTENTION : dans game on crée toujours 4 bord est-ce normal ?
// Toujours une fonction test

// déclaration de la classe Game
public class Game {
	
	// Centre de la table 
	private Pot pot;
	
	// Plateau de jeu pour chaque joueur 
	private Bord[] players;
	
	// Le contrôleur qui gère le déroulement du jeu
	private Controller controller;
	
	// L'indice du joueur actuellement actif
	private int current_player;
	
	
	// Constructeur qui prend en paramètre un contrôleur et le nombre de joueurs
	public Game(Controller ref, int nb_player) {
		
		current_player = 0;
		controller = ref;
		
		// initialise les Bord avec le nombre de joueurs
		players = new Bord[4];
		for(int i = 0; i<4; i++) players[i] = new Bord(i, this);
		
		// initialise le Pot en fonction du nombre de joueurs 
		pot = new Pot(4, this);
		
	}
	
	
	// Envoie une liste de tuiles à la main du joueur actuel
	public void sendSelectiontoBord(LinkedList<Tile> tiles) {
		players[current_player].setHand(tiles);
	}
	
	
	// Envoie une tuile à la main du joueur actuel
	public void sendSelectiontoBord(Tile tile) {
		players[current_player].setHand(tile);
	}
	
	
	
	
	// Envoie une liste de tuiles au Bag
	public void sendToBag(LinkedList<Tile> tiles) {
		pot.sendToBag(tiles);
	}
	
	
	// Indique la fin d'un tour de jeu et passe au joueur suivant
	public void endOfSet() {
		
		// Appelle la méthode endOfSet() de chaque joueur
		for(Bord p: players) {
			p.endOfSet();
		}

		// passe au joueur suivant 
		current_player++;
	
	}
	
	public void test() {
		pot.test(0);
		pot.test(1);
		pot.test(2);
		pot.test(3);
		//controller.setButtonsPot(pot.checkPossible());*/
		
	}
	
	public void testShuffle() {
		pot.testShuffle();
	}

	
	
	public void sendSelectiontoBordTest(LinkedList<Tile> tiles_bord, int number) {
		players[number].test(tiles_bord);
	}

	public Tile[][] getPatternToView() {
		// TODO Auto-generated method stub
		return players[current_player].getPatternToView();
	}

	public void sendContentList(LinkedList<Tile> to_send, int index) {
		controller.updatePile(to_send, index);
		
	}

	public void updateViewLine(LinkedList<Tile> to_send, int previous_index, int i, LinkedList<Tile> linkedList, int previous_index_2) {
		controller.updateViewLine(to_send, previous_index, i, current_player, linkedList, previous_index_2);
	}
	
	public void updateMiddlePileView(LinkedList<Tile> to_add, int previous_index) {
		controller.updateMiddlePileView(to_add, previous_index);
	}
	
	public void updatePatternView(HashMap<Tile, Position> to_send, int playerID) {
		controller.updatePatternView(to_send, playerID);
	}
	
	public void updateMalusView(int playerID) {
		controller.updateMalusView(playerID);
	}
	
	
	
	
}
