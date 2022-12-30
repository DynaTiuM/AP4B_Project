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
	
	
	public boolean hasContent() {
		return tiles[0]!=null;
	}
	
	public void setContent(Tile to_add, int index) {
		tiles[index] = to_add;
	}
	
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
	
	public Tile[] getContent(){
		return this.tiles;
	}
	
	public void getSelection(Tile chosen, int number) {
		tiles_middle.clear();
		tiles_bord.clear();
		
		for(int i = 0; i < 4; i++) {
			if(tiles[i].getColor() == chosen.getColor()) {
				tiles_bord.add(tiles[i]);
			} else tiles_middle.add(tiles[i]);
			
			tiles[i] = null;
		}
		
		if (tiles_middle != null && tiles_middle.getFirst() != null) sendToMiddle();
		
		sendToBordTest(number);
		sendContentList();
		
	}
	
	private void sendToMiddle() {
		this.middle_ref.addContent(tiles_middle);
	}
	
	private void sendToBord() {
		game_ref.sendSelectiontoBord(tiles_bord);
	}
	
	public void test(int number) {
		//creer un sc�nar
		getSelection(tiles[number], number);
	}
	
	private void sendToBordTest(int number) {
		game_ref.sendSelectiontoBordTest(tiles_bord, number);
		//System.out.println("sent");
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
	
	public boolean checkPossible() {
	  return tiles[0]!=null;
	}
	
}