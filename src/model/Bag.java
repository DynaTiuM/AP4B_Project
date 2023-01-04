package model;

import java.awt.Color;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;


//déclaration de la classe "Bag"
public class Bag {
	
	
	private static LinkedList<Tile> tiles;
	private Pile[] allPiles;
	private Pot pot_ref;

	
	// constructeur de la classe "Bag"
	public Bag(Pile[] piles, Pot pot) {
		pot_ref = pot;
		allPiles = piles;
		initialiseTiles(); // on initialise les tiles du bag
		this.distributeContents(); // on remplit les piles
	}
	
	
	// retourne une "Tile" du "Bag" de manière aléatoire
	public static Tile getRandomTile() {
		Random rd = new Random();
		
		int tileIndex = rd.nextInt(tiles.size());
		
		Tile tile = tiles.get(tileIndex);
		tiles.remove(tileIndex);
		
		return tile;
	}
	
	
	// initialise les "Tile" dans le "Bag" avec 20 "Tile" de chaque couleur
	private void initialiseTiles() {
		tiles = new LinkedList<Tile>();

		// tableau des couleurs possibles pour les tuiles
		Color[] colors = {Color.MAGENTA, Color.ORANGE, Color.GREEN, Color.BLUE, Color.YELLOW};
		
		for (int i = 0; i < 20; i++) {
			// on ajoute une tuile de chaque couleur dans la liste chainée de tuiles
			for (Color color : colors) {
				tiles.add(new Tile(color));
			}
		}
		
		// on mélange aléatoirement les tuiles de la liste chainée de tuiles deux fois
		Collections.shuffle(tiles);
		Collections.shuffle(tiles);
	}
	
	
	// remplit les "Pile" en prenant les dernières "Tile"
	public void distributeContents() {
		System.out.println("distribution");
		Tile last;
		
		for(Pile p: allPiles) {
			for(int i = 0; i < 4; i++) {
				last = tiles.getLast();
				//System.out.println(tiles.getLast().getColorEnum());
				p.setContent(last, i);
				tiles.remove(last);
			}
			p.sendContentList();
		}
	}
	
	
	// Re-remplit le "Bag" 
	public void getTilesBack(LinkedList<Tile> tiles_toadd) {
		
		for(Tile p: tiles_toadd) {
			System.out.print(p.getColorEnum() + " ");
			tiles.add(p);
		}
		
		//Collections.shuffle(tiles);
		System.out.println("\nnb of tiles : " + tiles.size());
	}


	public void getTilesBack(Tile p) {
		tiles.add(p);
		System.out.print(p.getColorEnum() + " ");
		System.out.println("\nnb of tiles : " + tiles.size());
	}
	
	
}
