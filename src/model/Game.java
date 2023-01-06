package model;

import java.util.HashMap;
import java.util.LinkedList;

import view.Position;
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
	private final Controller controllerRef;
	
	// L'indice du joueur actuellement actif
	private int currentPlayer;
	private final int nbPlayers;
	
	// Constructeur qui prend en paramètre un contrôleur et le nombre de joueurs
	public Game(Controller ref, int nbPlayers) {
		this.nbPlayers = nbPlayers;
		currentPlayer = 0;
		controllerRef = ref;
		
		// initialise les Bord avec le nombre de joueurs
		players = new Bord[nbPlayers];
		for(int i = 0; i < nbPlayers; i++) players[i] = new Bord(i, this);
		
		// initialise le Pot en fonction du nombre de joueurs 
		pot = new Pot(nbPlayers, this);
		
	}

	// Envoie une liste de tuiles à la main du joueur actuel
	public void sendSelectionToBord(LinkedList<Tile> tiles) {
		players[currentPlayer].setHand(tiles);
	}

	// The player clicked on a line on the popup :
	public void lineSelected(int lineNumber) {
		players[currentPlayer].playHandIndex(lineNumber);
	}
	
	// Indique la fin d'un tour de jeu et passe au joueur suivant
	public void endOfSet() {
		// Appelle la méthode endOfSet() de chaque joueur
		for(Bord p: players) {
			p.endOfSet();
		}
		pot.distributeContents();
		controllerRef.initialiseButtonsPiles();
		pot.setFirst();

		for(Bord p: players) {
			if(p.checkEnd()) {
				this.endOfGame();
				break;
			}
			
			if(p.getNextFirst()) {
				p.resetNextFirst();
				currentPlayer = p.getID();
				break;
			}
		}
	}

	public void setTilesSelectedToHand(int numberOfPile, int ID) {
		pot.setTilesSelectedToHand(numberOfPile, ID);
	}
	
	public LinkedList<Tile> modifyMiddlePile(int index) {
		return pot.modifyMiddlePile(index);
	}


	public void sendContentList(LinkedList<Tile> toSend, int index) {
		controllerRef.updatePile(toSend, index);
	}


	public void updateViewLine(LinkedList<Tile> toSend, int previousIndex, int i, Tile[] malus) {
		controllerRef.updateViewLine(toSend, previousIndex, i, currentPlayer, malus);
	}
	
	public void updateMiddlePileView(LinkedList<Tile> toAdd, int previousIndex, boolean delete) {
		controllerRef.updateMiddlePileView(toAdd, previousIndex, delete);
	}
	
	public void updatePatternView(int playerID, HashMap<Tile, Position> toSend) {
		controllerRef.updatePatternView(playerID, toSend);
	}
	
	public void updateMalusToView(Tile[] malus) {
		controllerRef.updateMalusView(malus, currentPlayer);
	}

	public void clearMalusView(int playerID){
		controllerRef.clearMalusView(playerID);
	}
	
	public void sendCompleteMiddlePileToView(boolean bool) {
		pot.sendCompleteMiddlePileToView(bool);
	}

	public void updateMalusModel() {
		players[currentPlayer].updateMalus();
	}

	public void sendToBag(Tile p) {
		pot.sendToBag(p);
	}

	// Envoie une liste de tuiles au Bag
	public void sendToBag(LinkedList<Tile> tiles) {
		pot.sendToBag(tiles);
	}
	
	//Methodes pour communiquer avec la vue
	public void getInformationForPopUp() {
		Tile[][] pattern = players[currentPlayer].getPatternToView();
		Line[] grid = players[currentPlayer].getLines();
		Tile[] malus = players[currentPlayer].getMalus();
		Tile hand = players[currentPlayer].getHand().get(0);
		
		updatePopUp(pattern, malus, grid, hand);
	}
	
	private void updatePopUp(Tile[][] pattern, Tile[] malus, Line[] grid, Tile hand) {
		controllerRef.updatePopup(pattern, malus, grid, hand);
	}

	public void nextPlayer() {
		this.currentPlayer++;
		
		if(currentPlayer == nbPlayers) {
			currentPlayer = 0;
		}

		if(pot.isPlayNotPossible()) {
			this.endOfSet();
			if(pot.isPlayNotPossible()) this.endOfGame();
		}
	}

	public void endOfGame() {
		int winningScore = 0;
		for(Bord p: players) {
			p.calculateEndOfGameBonuses();
			
			if(p.getScore() > winningScore) {
				winningScore = p.getScore();
			}
		}
		controllerRef.displayEndOfGame(players);
	}

	public void sendMalusFirst(Tile first) {
		players[currentPlayer].sendMalusFirst(first);
	}

	public void updateViewLine(LinkedList<Tile> toSend, int previousIndex, int i) {
		controllerRef.updateViewLine(toSend, previousIndex, i, currentPlayer);
	}

	public void sendMalusFirstToView(int previous) {
		controllerRef.sendMalusFirstToView(previous, currentPlayer);
		
	}
	
	//Methodes get
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public int getScore(int playerID) {
		return players[playerID].getScore();
	}
	
}
