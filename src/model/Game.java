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
	private final Pot pot;
	
	// Plateau de jeu pour chaque joueur 
	private final Bord[] players;
	
	// Le contrôleur qui gère le déroulement du jeu
	private final Controller controller;
	
	// L'indice du joueur actuellement actif
	private int current_player;
	private final int nbPlayers;

	private int winner = -1;
	
	// Constructeur qui prend en paramètre un contrôleur et le nombre de joueurs
	public Game(Controller ref, int nb_player) {
		nbPlayers = nb_player;
		current_player = 0;
		controller = ref;
		
		// initialise les Bord avec le nombre de joueurs
		players = new Bord[nb_player];
		for(int i = 0; i < nb_player; i++) players[i] = new Bord(i, this);
		
		// initialise le Pot en fonction du nombre de joueurs 
		pot = new Pot(nb_player, this);
		
	}
	
	// Envoie une liste de tuiles à la main du joueur actuel
	public void sendSelectionToBord(LinkedList<Tile> tiles) {
		players[current_player].setHand(tiles);
	}

	// Envoie une tuile à la main du joueur actuel
	public void sendSelectionToBord(Tile tile) {
		players[current_player].setHand(tile);
	}
	
	// Envoie une liste de tuiles au Bag
	public void sendToBag(LinkedList<Tile> tiles) {
		pot.sendToBag(tiles);
	}
	
	// The player clicked on a line on the popup :
	public void lineSelected(int lineNumber) {
		players[current_player].playHandIndex(lineNumber);
		System.out.println("Played on line : " + lineNumber);
	}
	
	// Indique la fin d'un tour de jeu et passe au joueur suivant
	public void endOfSet() {
		// Appelle la méthode endOfSet() de chaque joueur
		for(Bord p: players) {
			p.endOfSet();
		}
		pot.distributeContents();
		controller.initialiseButtonsPiles();
		pot.setFirst();

		controller.displayEndOfGame(2, new int[]{0, 0, 0, 0});
	}

	public void testShuffle() {
		pot.testShuffle();
	}

	public void setTilesSelectedToHand(int numberOfPile, int ID) {
		pot.setTilesSelectedToHand(numberOfPile, ID);
	}
	
	public LinkedList<Tile> modifyMiddlePile(int index) {
		return pot.modifyMiddlePile(index);
	}
	
	public void sendSelectionToBordTest(LinkedList<Tile> tiles_bord) {
		players[current_player].test(tiles_bord);
	}

	public void sendContentList(LinkedList<Tile> to_send, int index) {
		controller.updatePile(to_send, index);
	}

	public void updateViewLine(LinkedList<Tile> to_send, int previous_index, int i, LinkedList<Tile> linkedList, int previous_index_2) {
		controller.updateViewLine(to_send, previous_index, i, current_player, linkedList, previous_index_2);
	}
	
	public void updateMiddlePileView(LinkedList<Tile> to_add, int previous_index, boolean delete) {
		controller.updateMiddlePileView(to_add, previous_index, delete);
	}
	
	public void updatePatternView(int playerID, HashMap<Tile, Position> to_send) {
		controller.updatePatternView(playerID, to_send);
	}
	
	public void updateMalusToView(LinkedList<Tile> to_send, int previous_index) {
		controller.updateMalusView(to_send, previous_index, current_player);
	}

	public void clearMalusView(int playerID){
		controller.clearMalusView(playerID);
	}
	
	public void sendCompleteMiddlePileToView(boolean bool) {
		pot.sendCompleteMiddlePileToView(bool);
	}

	public void updateMalusModel() {
		players[current_player].updateMalus();
	}

	public int getScore(int playerID) {
		return players[playerID].getScore();
	}
	
	public void getInformationForPopUp() {
		Tile[][] pattern = players[current_player].getPatternToView();
		Line[] grid = players[current_player].getLines();
		Tile[] malus = players[current_player].getMalus();
		Tile hand = players[current_player].getHand().get(0);
		
		updatePopUp(pattern, malus, grid, hand);
	}
	
	private void updatePopUp(Tile[][] pattern, Tile[] malus, Line[] grid, Tile hand) {
		controller.updatePopup(pattern, malus, grid, hand);
	}

	public void sendToBag(Tile p) {
		pot.sendToBag(p);
	}

	public void nextPlayer() {
		this.current_player++;
		
		if(current_player == nbPlayers) {
			current_player = 0;
		}
		
		System.out.println("NEXT PLAYER : " + current_player);
		
		if(!pot.isPlayPossible()) {
			this.endOfSet();
		}
	}
	
	public int getCurrentPlayer() {
		return current_player;
	}
	
	public void endOfGame() {
		//TODO
		int winning_score = 0;
		for(Bord p: players) {
			p.calculateEndOfGameBonuses();
			
			if(p.getScore()>winning_score) {
				winning_score = p.getScore();
				this.winner = p.getID();
			}
			
		}
		
		System.out.println("Winner : " + winner);
		int[] scores = new int[4];

		int i = 0;
		for(Bord bord : players) {
			scores[i] = bord.getScore();
			i++;
		}

		controller.displayEndOfGame(winner, scores);
	}

	public void sendMalusFirst(Tile first) {
		players[current_player].sendMalusFirst(first);
	}

	public void updateViewLine(LinkedList<Tile> to_send, int previous_index, int i) {
		controller.updateViewLine(to_send, previous_index, i, current_player);
		
	}

	public void sendSelectionToBordMiddle(LinkedList<Tile> toSend) {
		players[current_player].setHandMiddle(toSend);
		
	}

	public void sendMalusFirstToView(int previous) {
		controller.sendMalusFirstToView(previous, current_player);
		
	}
	
	
	
	
}
