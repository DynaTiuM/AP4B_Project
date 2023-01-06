package model;

import java.util.LinkedList;

public class Malus {

	private final Tile[] line;
	private final int[] penalty;
	
	private final LinkedList<Tile> to_send;
	
	private final Bord bord_ref;

	private int current_index;
	private int previous_index;
	
	public Malus(Bord bord_p){
		current_index = 0;
		previous_index = 0;
		
		to_send = new LinkedList<>();
		
		penalty = new int[7];
		initPenalty();
	
		line = new Tile[7];
		
		bord_ref = bord_p;
	}
	
	public void addTile(LinkedList<Tile> tiles) {
		previous_index = current_index;

		for(Tile p: tiles) {
			if(current_index < 7) {
				line[current_index] = p;

				
				
				current_index++;
			}else {
				bord_ref.sendToBag(p);
				//previous_index=7;
			}
		}
	}
	
	public void addTile(Tile tile_p) {
		if(current_index < 7) {
			line[current_index] = tile_p;
			
			previous_index = current_index;
			current_index++;
		}else {
			if(tile_p.getColorEnum()!=ColorEnum.MALUS) bord_ref.sendToBag(tile_p);
		}
	}
	
	private void initPenalty() {
		penalty[0]=1;
		penalty[1]=1;
		penalty[2]=2;
		penalty[3]=2;
		penalty[4]=2;
		penalty[5]=3;
		penalty[6]=3;
	}
	
	public int computateMalus() {
		int badpoints = 0;
		
		for(int i = 0; i<current_index; i++) {
			badpoints += penalty[i];
		}
		
		
		return badpoints;
	}
	
	public void display() {
		System.out.println("malus");
		
		int i =0;
		
		while(i<current_index) {
			System.out.print(line[i].getColorEnum() + " ");
			i++;
		}
		
		while(i< 7) {
			System.out.print("- ");
			i++;
		}
		
		System.out.println();
	}

	public LinkedList<Tile> clear() {
		
		to_send.clear();
		
		for(int i =0; i<current_index; i++) {
			if(line[i].getColorEnum()!=ColorEnum.MALUS) to_send.add(line[i]);

			line[i] = null;
		}
		
		current_index = 0;
		
		return to_send;
	}
	
	public Tile[] getLine() {
		return this.line;
	}

	public int getPrevious() {		
		return previous_index;
	}
	
	public void setPrevious(int previous) {
		this.previous_index = previous;
	}
	
	public boolean isEmpty() {
		return line[0] == null;
	}

	
	
}
