package model;


import java.util.Collections;
import java.util.LinkedList;


//déclaration de la classe "Bag"
public class Bag {
	private static LinkedList<Tile> tiles;
	private final Pile[] allPilesRef;
	
	// constructeur de la classe "Bag"
	public Bag(Pile[] piles) {
		allPilesRef = piles;
		initialiseTiles(); // on initialise les tiles du bag
		this.distributeContents(); // on remplit les piles
	}

	// initialise les "Tile" dans le "Bag" avec 20 "Tile" de chaque couleur
	private void initialiseTiles() {
		tiles = new LinkedList<>();

		// tableau des couleurs possibles pour les tuiles
		ColorEnum[] colors = {ColorEnum.M, ColorEnum.O, ColorEnum.G, ColorEnum.B, ColorEnum.Y};
		
		for (int i = 0; i < 20; i++) {
			// on ajoute une tuile de chaque couleur dans la liste chainée de tuiles
			for (ColorEnum color : colors) {
				tiles.add(new Tile(color));
			}
		}
		
		// on mélange aléatoirement les tuiles de la liste chainée de tuiles deux fois
		Collections.shuffle(tiles);
		Collections.shuffle(tiles);
	}
	
	
	// remplit les "Pile" en prenant les dernières "Tile"
	public void distributeContents() {
		Collections.shuffle(tiles);
		Tile last;
		
		for(Pile p: allPilesRef) {
			if(tiles.size()>=4) {
				for(int i = 0; i < 4; i++) {
					last = tiles.getLast();
					p.setContent(last, i);
					tiles.remove(last);
				}
				p.sendContentList();
			}else {
				break;
			}
			
		}
	}
	
	// Re-remplit le "Bag" 
	public void getTilesBack(LinkedList<Tile> tilesToAdd) {
		tiles.addAll(tilesToAdd);
	}


	public void getTilesBack(Tile p) {
		tiles.add(p);
	}
}
