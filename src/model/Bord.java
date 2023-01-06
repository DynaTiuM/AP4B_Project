package model;

import java.util.HashMap;
import java.util.LinkedList;

import View.Position;


//ATTENTION DEUX METHODES TEST SONT TOUJOURS DANS LE CODE

//Classe qui représente le plateau de jeu d'un joueur.
//Le plateau est composé de 5 lignes, d'une grille de malus et d'une grille de motifs.
public class Bord {

	private final Line[] playGrid;
	private final Malus malusGrid;
	private final Pattern patternGrid;

	// Référence du "Game" auquel appartient le "Bord".
	private final Game gameRef;
	
	// Identifiant du joueur associé à ce "Bord"
	private final int playerID;

	// Liste des "Tile" dans la main du joueur
	private LinkedList<Tile> playerHand;
	
	private boolean nextFirstPlayer;
	
	
	// Constructeur de la classe Bord.
	// Initialise les différents éléments du plateau de jeu (lignes, grille de malus, grille de motifs).4
	// Prends l'identifiant du joueur et la référence de "Game"
	public Bord(int number, Game ref) {

		nextFirstPlayer = false;
		
		playerID = number;
		gameRef = ref;
		
		malusGrid = new Malus(this);
		patternGrid = new Pattern(this);
		
		playGrid = new Line[5];
		for(int i=0; i<5; i++) {
			playGrid[i] = new Line(malusGrid, patternGrid, i+1, this);
		}

		this.playerHand = new LinkedList<>();
	}
	
	
	// modifie la main du joueur 
	public void setHand(LinkedList<Tile> tiles) {
		playerHand.clear();
		playerHand = tiles;
	}
	
	public LinkedList<Tile> getHand() {
		return playerHand;
	}
	
	public Line[] getLines() {
		return playGrid;
	}
	
	public Tile[] getMalus() {
		return malusGrid.getLine();
	}

	// permet d'afficher la main du joueur
	public void displayHand() {
		System.out.print("Hand : ");
		for(Tile p: playerHand) System.out.print(p.getColorEnum() + " ");
		System.out.println();
		
	}
	
	// permet d'afficher les "Line" et la "malus_grid"
	public void display() {
		for(Line p: playGrid) p.display();
		malusGrid.display();

	}

	// permet de poser les "Tile" de la main du joueur sur la ligne choisie
	public void playHandIndex(int index) {
		playGrid[index].addChoice(playerHand);
		this.gameRef.nextPlayer();
	}
	
	
	// fonction appelée en fin de manche 
	public void endOfSet() {
		boolean update = false;
		// vide toute les "Line" qui sont pleines 
		for(Line line: playGrid) {
			if(line.checkFull()) {
				gameRef.sendToBag(line.clear());

				update = true;
			}
		}
		
		// calcul le malus et remet les "Tile" de malus dans le "Bag"
		if(!this.malusGrid.isEmpty()) {
			patternGrid.scoreMalus(malusGrid.computateMalus());
			gameRef.sendToBag(malusGrid.clear());
		}

		
		if(update) {
			patternGrid.sendPattern();
		}
		
		
		this.gameRef.clearMalusView(playerID);

	}
	
	// envoie le pattern à la view
	public Tile[][] getPatternToView() {

		return patternGrid.getGrid();
	}

	public void sendToBag(Tile p) {
		gameRef.sendToBag(p);
	}


	public void updateViewLine(LinkedList<Tile> toSend, int previousIndex, int i, boolean modified) {
	
		if(modified) {
			this.gameRef.updateViewLine(toSend, previousIndex, i, malusGrid.getLine());
		}else {
			this.gameRef.updateViewLine(toSend, previousIndex, i);
		}
		
	}

	public void updatePatternView(HashMap<Tile, Position> toSend) {
		this.gameRef.updatePatternView(this.playerID, toSend);
	}
	
	public void updateMalus() {
		this.malusGrid.addTile(playerHand);
		this.gameRef.updateMalusToView(malusGrid.getLine());
		this.gameRef.nextPlayer();
	}

	public void calculateEndOfGameBonuses() {
		patternGrid.calculateEndOfGameBonuses();
	}
	
	public int getScore() {
		return this.patternGrid.getScore();
	}

	public int getID() {

		return this.playerID;
	}


	public void sendMalusFirst(Tile first) {
		nextFirstPlayer = true;
		malusGrid.addTile(first);
		gameRef.sendMalusFirstToView(malusGrid.getPrevious());
	}

	public boolean checkEnd(){
		return patternGrid.checkEndGame();
	}


	public boolean getNextFirst() {
		
		return nextFirstPlayer;
	}
	
	public void resetNextFirst() {
		nextFirstPlayer = false;
	}
	
}
