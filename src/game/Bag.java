package game;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Bag {
	
	private static LinkedList<Tile> tiles;
	
	public Bag() {
		initialiseTiles();
	}
	
	public static Tile getRandomTile() {
		Random rd = new Random();
		
		int tileIndex = rd.nextInt(tiles.size());
		
		Tile tile = tiles.get(tileIndex);
		tiles.remove(tileIndex);
		
		return tile;
	}
	
	private void initialiseTiles() {
		int i = 0;
		while(i < 20) {
			tiles.add(new Black());
			i++;
		}
		while(i < 40) {
			tiles.add(new Yellow());
			i++;
		}
		while(i < 60) {
			tiles.add(new Red());
			i++;
		}
		while(i < 80) {
			tiles.add(new Blue());
			i++;
		}
		while(i < 100) {
			tiles.add(new White());
			i++;
		}
		
		Collections.shuffle(tiles);
	}

}
