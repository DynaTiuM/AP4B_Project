package model;

import java.util.LinkedList;

public class Malus {

	private Tile[] line;
	private int[] penalty;
	
	private LinkedList<Tile> to_send;
	
	private Bord bord_ref; 

	private int current_index;
	private int previous_index;
	
	public Malus(Bord bord_p){
		current_index=0;
		previous_index = 0;
		
		to_send = new LinkedList<Tile>();
		
		penalty = new int[7];
		initPenalty();
	
		line = new Tile[7];
		
		bord_ref = bord_p;
	}
	
	public void addTile(LinkedList<Tile> tiles) {
		
		for(Tile p: tiles) {
			if(current_index<7) {
				line[current_index] = p;
				
				previous_index = current_index;
				current_index++;
			}else {
				bord_ref.sendToBag(p);
			}
		}
	}
	
	public void addTile(Tile tile_p) {
		
		if(current_index<7) {
			line[current_index] = tile_p;
			
			previous_index = current_index;
			current_index++;
		}else {
			System.out.println("Too many to possibly add, sending to top of the box");
			bord_ref.sendToBag(tile_p);
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
		
		this.bord_ref.updateMalus();
		
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
	
	public LinkedList<Tile> getContent(){
		to_send.clear();
		for(int i = 0; i< current_index; i++) 
			to_send.add(line[i]);
		
		return to_send;
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

	public int getPrevious() {
		// TODO Auto-generated method stub
		System.out.println("previous " + previous_index);
		return previous_index;
	}
	
	public void setPrevious(int previous) {
		this.previous_index = previous;
	}
	
	public boolean isEmpty() {
		return line[0]!=null;
	}

	
	
}
