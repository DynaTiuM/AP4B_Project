package model;

import java.util.Arrays;
import java.util.LinkedList;

public class Pile {
	
	private final Tile[] tiles;
	
	private final LinkedList<Tile> bordTiles;
	private final LinkedList<Tile> middleTiles;

	private final MiddlePile middleRef;
	private final Game gameRef;
	
	private final int index;
	
	
	
	public Pile(Game game, MiddlePile middle, int index) {
		this.index = index;

		tiles = new Tile[4];

		
		bordTiles = new LinkedList<>();
		middleTiles = new LinkedList<>();

		middleRef = middle;
		gameRef = game;
	}
	
	//method called when a tile on a Pile is pressed
	
	public void setTilesSelectedToHand(int index) {
		getSelection(tiles[index]);
	}
	
	//method called by the bag

	public void setContent(Tile toAdd, int index) {
		tiles[index] = toAdd;
	}
	
	//send the content to the view
	
	public void sendContentList() {
		LinkedList<Tile> toSend = new LinkedList<>();
		if(tiles[0] != null) {
			toSend.addAll(Arrays.asList(tiles));
		} else {
			toSend.add(null);
		}
		gameRef.sendContentList(toSend, index);

	}

	//check the tiles for tiles of the same color as the current one and send it to bord of current player, remaining tiles are sent to MiddlePile
	
	public void getSelection(Tile chosen) {
		middleTiles.clear();
		bordTiles.clear();
		
		for(int i = 0; i < 4; i++) {
			if(tiles[i].getColorEnum() == chosen.getColorEnum()) {
				bordTiles.add(tiles[i]);
			} else middleTiles.add(tiles[i]);

			tiles[i] = null;
		}

		if (middleTiles != null)  sendToMiddle();
		
		sendToBord();
		sendContentList();
		
	}
	
	private void sendToMiddle() {
		this.middleRef.addContent(middleTiles);
	}
	
	private void sendToBord() {
		gameRef.sendSelectionToBord(bordTiles);
	}

	public void display() {
		
		int i = 0;
		while(i < 2) {
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
	
	//checks if Tile is empty, is used to check if play is possible
	
	public int isEmpty() {
		
		//if the first tile is not present, there can't be any other tiles
		
		if(tiles[0] == null) {
			return 1;
		}else {
			return 0;
		}
	}
	
}
