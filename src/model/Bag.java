package model;


import java.util.Collections;
import java.util.LinkedList;


//déclaration de la classe "Bag"
public class Bag {
	
	//Tiles contained in the middle pile
	private static LinkedList<Tile> tiles;
	
	private final Pile[] allPilesRef;
	
	
	public Bag(Pile[] piles) {
		allPilesRef = piles;
		initialiseTiles(); // initialise the tiles
		this.distributeContents(); // fill the piles
	}

	// initialise Tiles in the bag with 20 Tiles of each color
	private void initialiseTiles() {
		tiles = new LinkedList<>();

		// ColorEnum array used to initiate the tiles
		ColorEnum[] colors = {ColorEnum.M, ColorEnum.O, ColorEnum.G, ColorEnum.B, ColorEnum.Y};
		
		for (int i = 0; i < 20; i++) {
			//for each iteration of if, we add a tile of each color
			for (ColorEnum color : colors) {
				tiles.add(new Tile(color));
			}
		}
		
		//shuffle the tiles twice
		Collections.shuffle(tiles);
		Collections.shuffle(tiles);
	}
	
	
	//fill Piles, can't fill a Pile if the number of remaining tiles is less than 4
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
