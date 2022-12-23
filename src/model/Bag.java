package model;

import java.awt.Color;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;



public class Bag {
	
	
private static LinkedList<Tile> tiles;
private Pile[] allPiles;
	
	public Bag(Pile[] piles) {
		allPiles = piles;
		initialiseTiles();
		this.distributeContents();
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
		
		tiles = new LinkedList<Tile>();
		
		while(i < 20) {
			tiles.add(new Tile(Color.black));
			
			i++;
		}
		while(i < 40) {
			tiles.add(new Tile(Color.ORANGE));
			
			i++;
		}
		while(i < 60) {
			tiles.add(new Tile(Color.red));
			
			i++;
		}
		while(i < 80) {
			tiles.add(new Tile(Color.BLUE));
			
			i++;
		}
		while(i < 100) {
			tiles.add(new Tile(Color.WHITE));
			
			i++;
		}
		
		Collections.shuffle(tiles);
		Collections.shuffle(tiles);
		
	}
	
	public void distributeContents() {
		
		Tile last;
		
		for(Pile p: allPiles) 
			for(int i =0; i<4; i++) {
				last = tiles.getLast();
				//System.out.println(tiles.getLast().getColorEnum());
				p.setContent(last, i);
				tiles.remove(last);
			}
		
		
	}
	
	public void getTilesBack(LinkedList<Tile> tiles) {
		tiles.addAll(tiles);
		Collections.shuffle(tiles);
	}
	
	
}