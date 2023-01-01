package model;

import java.util.LinkedList;

public class Pile {
	
	private Tile[] tiles;
	
	private LinkedList<Tile> tiles_bord;
	private LinkedList<Tile> tiles_middle;
	
	private Pot pot_ref;
	private MiddlePile middle_ref;
	private Game game_ref;
	
	private int index;
	
	
	
	public Pile(Game game, MiddlePile middle, Pot pot, int index) {
		this.index = index;
		tiles = new Tile[4];
		//for(int i =0; i<4; i++) tiles[i] = new Tile();
		
		tiles_bord = new LinkedList<Tile>();
		tiles_middle = new LinkedList<Tile>();
		
		pot_ref = pot;
		middle_ref = middle;
		game_ref = game;
	}
	
	
	// indique si la pile contient des tuiles
	public boolean hasContent() {
		return tiles[0]!=null;
	}
	
	
	// ajoute une tuile à la pile à l'index spécifié
	public void setContent(Tile to_add, int index) {
		tiles[index] = to_add;
	}
	
	// envoie la liste des tuiles de la pile au jeu
	public void sendContentList() {
		
		LinkedList<Tile> to_send = new LinkedList<Tile>();
		if(tiles[0]!=null) {
			for(Tile p: tiles) {
				to_send.add(p);
				System.out.print(p.getColorEnum() + " - ");
			}
		} else {
			to_send.add(null);
			System.out.print("nothing");
		}
		
		System.out.println("Start of sendContentList : pile");
		game_ref.sendContentList(to_send, index);
		
	}
	
	
	// renvoie le tableau de tuiles de la pile
	public Tile[] getContent(){
		return this.tiles;
	}
	
	
	// sélectionne les tuiles de la pile de la même couleur que la tuile choisie, et envoie les autres tuiles au milieu de la table
	public void getSelection(Tile chosen) {
		for(int i =0; i<4; i++) {
			if(tiles[i].getColor() == chosen.getColor()) {
				tiles_bord.add(tiles[i]);
			} else tiles_middle.add(tiles[i]);
			
			tiles[i] = null;
		}
		sendToMiddle();
		sendToBordTest();
		sendContentList();
	}
	
	
	// envoie les tuiles sélectionnées au milieu de la table
	private void sendToMiddle() {
		this.middle_ref.addContent(tiles_middle);
	}
	
	
	// envoie les tuiles sélectionnées au bord de la table 
	private void sendToBord() {
		game_ref.sendSelectiontoBord(tiles_bord);
	}
	
	
	// exécute le scénario de sélection de tuiles pour tester le fonctionnement de la pile
	public void test() {
		//creer un scenar
		getSelection(tiles[0]);
		
	}
	
	
	// envoie les tuiles sélectionnées au bord de la table pour les tests
	private void sendToBordTest() {
		game_ref.sendSelectiontoBordTest(tiles_bord);
		//System.out.println("sent");
	}


	// affiche les tuiles de la pile
	public void display() {
		
		int i = 0;
		while(i <2) {
			if(tiles[i]!=null) {
				System.out.print(tiles[i].getColorEnum() + " ");
			} else {
				System.out.print("null ");
			}
			i++;
		}
		
		System.out.println();
		
		while(i <4) {
			if(tiles[i]!=null) {
				System.out.print(tiles[i].getColorEnum() + " ");
			} else {
				System.out.print("null ");
			}
			i++;
		}
		
		
		System.out.println("\n---");
		
		//System.out.println(tiles[0].getColorEnum() + " " + tiles[1].getColorEnum() + "\n" + tiles[2].getColorEnum() + " " + tiles[3].getColorEnum() + "\n" );
		
	}
	
	public boolean checkPossible() {
	  return tiles[0]!=null;
	}
	
}
