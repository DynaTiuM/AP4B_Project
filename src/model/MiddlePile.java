package model;

import java.awt.Color;
import java.util.LinkedList;

public class MiddlePile {

	private LinkedList<Tile> tiles;
	private LinkedList<Tile> selection;
	
	private Pot pot_ref;
	
	private boolean first;
	
	public MiddlePile(Pot ref) {
		
		tiles = new LinkedList<Tile>();
		selection = new LinkedList<Tile>();
		
		pot_ref = ref;
		
		first = false;
		
		
	}
	
	
	public void addContent(LinkedList<Tile> to_add) {
		tiles.addAll(to_add);
		to_add.clear();
	}
	
	public LinkedList<Tile> getContent(){
		return tiles;
	}
	
	public LinkedList<Tile> getSelection(Color color) {
		selection.clear();
		for(Tile p: tiles) if(p.getColor() == color) selection.add(p);
		
		return selection;
	}
	
	public boolean isFirst() {
		return this.first;
	}
	
	public void display() {
		System.out.print("Middle : ");
		for(Tile p: tiles) System.out.print(p.getColorEnum() + " ");
		System.out.println();
	}
	
}
