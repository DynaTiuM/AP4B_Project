package model;

import java.util.HashMap;
import java.util.LinkedList;

import View.Position;


//ATTENTION DEUX METHODES TEST SONT TOUJOURS DANS LE CODE

//Classe qui représente le plateau de jeu d'un joueur.
//Le plateau est composé de 5 lignes, d'une grille de malus et d'une grille de motifs.
public class Bord {
	
	private final Line[] play_grid;
	private final Malus malus_grid_m;
	private final Pattern pattern_grid_m;
	
	//TODO SUPPRESS
	private int TEST_LINE;
	
	// Référence du "Game" auquel appartient le "Bord".
	private final Game game_ref;
	
	// Identifiant du joueur associé à ce "Bord"
	private final int playerID;
	
	// Numéro de la "Line" actuellement sélectionnée
	private int current;
	
	// Liste des "Tile" dans la main du joueur
	private LinkedList<Tile> hand_of_player;
	
	private boolean next_first;
	
	
	// Constructeur de la classe Bord.
	// Initialise les différents éléments du plateau de jeu (lignes, grille de malus, grille de motifs).4
	// Prends l'identifiant du joueur et la référence de "Game"
	public Bord(int number, Game ref) {
		
		this.TEST_LINE = 0;
		
		next_first = false;
		
		current = 0;
		
		playerID = number;
		game_ref = ref;
		
		malus_grid_m = new Malus(this);
		pattern_grid_m = new Pattern(this);
		
		play_grid = new Line[5];
		for(int i=0; i<5; i++) {
			play_grid[i] = new Line(malus_grid_m, pattern_grid_m, i+1, this);
		}

		this.hand_of_player = new LinkedList<>();
	}
	
	
	// modifie la main du joueur 
	public void setHand(LinkedList<Tile> tiles) {
		hand_of_player.clear();
		hand_of_player = tiles;
	}
	
	public LinkedList<Tile> getHand() {
		return hand_of_player;
	}
	
	public Line[] getLines() {
		return play_grid;
	}
	
	public Tile[] getMalus() {
		return malus_grid_m.getLine();
	}
	
	public void test(LinkedList<Tile> tiles) {
		setHand(tiles);
		displayHand();
		playHandIndex(TEST_LINE);
		TEST_LINE++;
		if(current + 1 >= 4) {
	    	current = 0;
	    } else current++;
		display();
	}

	// permet de changer la main du joueur en y mettant qu'une seule "Tile"
	public void setHand(Tile tile) {
		hand_of_player.clear();
		hand_of_player.add(tile);
	}
	
	// permet d'afficher la main du joueur
	public void displayHand() {
		System.out.print("Hand : ");
		for(Tile p: hand_of_player) System.out.print(p.getColorEnum() + " ");
		System.out.println();
		
	}
	
	// permet d'afficher les "Line" et la "malus_grid"
	public void display() {
		for(Line p: play_grid) p.display();
		malus_grid_m.display();

	}

	// permet de poser les "Tile" de la main du joueur sur la ligne choisie
	public void playHandIndex(int index) {
		play_grid[index].addChoice(hand_of_player);
		this.game_ref.nextPlayer();
	}
	
	
	// fonction appelée en fin de manche 
	public void endOfSet() {
		boolean update = false;
		// vide toute les "Line" qui sont pleines 
		for(Line line: play_grid) {
			if(line.checkFull()) {
				game_ref.sendToBag(line.clear());

				update = true;
			}
		}
		
		// calcul le malus et remet les "Tile" de malus dans le "Bag"
		if(!this.malus_grid_m.isEmpty()) {
			pattern_grid_m.scoreMalus(malus_grid_m.computateMalus());
			game_ref.sendToBag(malus_grid_m.clear());
		}

		
		if(update) {
			pattern_grid_m.sendPattern();
		}
		
		
		this.game_ref.clearMalusView(playerID);

	}
	
	// envoie le pattern à la view
	public Tile[][] getPatternToView() {

		return pattern_grid_m.getGrid();
	}


	public void updateViewLine(LinkedList<Tile> to_send, int previous_index, int i, boolean modified) {
		// TODO Auto-generated method stub
		if(modified) {
			this.game_ref.updateViewLine(to_send, previous_index, i, malus_grid_m.getLine());
		}else {
			this.game_ref.updateViewLine(to_send, previous_index, i);
		}
		
	}

	public void updatePatternView(HashMap<Tile, Position> to_send) {
		this.game_ref.updatePatternView(this.playerID, to_send);
	}
	
	public void updateMalus() {
		this.malus_grid_m.addTile(hand_of_player);
		this.game_ref.updateMalusToView(malus_grid_m.getLine());
		this.game_ref.nextPlayer();
	}

	public void sendToBag(Tile p) {
		game_ref.sendToBag(p);
	}

	public void calculateEndOfGameBonuses() {
		pattern_grid_m.calculateEndOfGameBonuses();
	}
	
	public int getScore() {
		return this.pattern_grid_m.getScore();
	}

	public int getID() {

		return this.playerID;
	}


	public void sendMalusFirst(Tile first) {
		next_first = true;
		malus_grid_m.addTile(first);
		game_ref.sendMalusFirstToView(malus_grid_m.getPrevious());
	}

	public boolean checkEnd(){
		return pattern_grid_m.checkEndGame();
	}


	public boolean getNextFirst() {
		// TODO Auto-generated method stub
		return next_first;
	}
	
	public void resetNextFirst() {
		next_first = false;
	}
	
}
