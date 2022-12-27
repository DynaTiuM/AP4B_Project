package model;

import java.util.LinkedList;

public class Malus {

	private Tile[] line;
	private int[] penalty;
	
	private LinkedList<Tile> to_send;
	
	private Bord bord_ref; 

	private int current_index;
	
	public Malus(Bord bord_p){
		current_index=0;
		
		to_send = new LinkedList<Tile>();
		
		penalty = new int[7];
		initPenalty();
	
		line = new Tile[7];
		
		/*for(int i = 0; i<7; i++) {
			line[i] = new Tile();
		}*/
		
		bord_ref = bord_p;
	}
	
	public void addTile(Tile to_add) {
		if(current_index<7) {
			line[current_index] = to_add;
			line[current_index].setOccupiedTrue();
			current_index++;
		}else {
			System.out.println("Too many to possibly add, sending to top of the box");
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
		
		System.out.println("clear malus");
		
		to_send.clear();
		
		
		
		for(int i =0; i<current_index; i++) {
			to_send.add(line[i]);
			line[i] = null;
		}
		
		current_index = 0;
		
		for(Tile p: to_send) System.out.print(p.getColorEnum() + " ");
		System.out.println();
		
		return to_send;
		
		
	}
	
	public Tile[] getLine() {
		return this.line;
	}
	
}
