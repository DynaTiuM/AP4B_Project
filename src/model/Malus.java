package model;

import java.util.LinkedList;

public class Malus {

	private final Tile[] line;
	private final int[] penalty;
	
	private final LinkedList<Tile> toSend;
	
	private final Bord bordRef;

	//allows us to know the progress of Malus
	private int currentIndex;
	
	//allows us to know the progress of Malus before modifications
	private int previousIndex;
	
	public Malus(Bord bord){
		currentIndex = 0;
		previousIndex = 0;
		
		toSend = new LinkedList<>();
		
		penalty = new int[7];
		initPenalty();
	
		line = new Tile[7];
		
		bordRef = bord;
	}
	
	public void addTile(LinkedList<Tile> tiles) {
		previousIndex = currentIndex;

		for(Tile p: tiles) {
			if(currentIndex < 7) {
				line[currentIndex] = p;
				currentIndex++;
			}else {
				bordRef.sendToBag(p);
			}
		}
	}
	
	public void addTile(Tile tile) {
		if(currentIndex < 7) {
			line[currentIndex] = tile;
			previousIndex = currentIndex;
			currentIndex++;
		}else {
			if(tile.getColorEnum()!=ColorEnum.MALUS) bordRef.sendToBag(tile);
		}
	}
	
	//associate the cases of Malus with their values
	
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
		int badPoints = 0;
		
		for(int i = 0; i<currentIndex; i++) {
			badPoints += penalty[i];
		}
		
		
		return badPoints;
	}
	
	//empties malus and sends content to the bag for re use

	public LinkedList<Tile> clear() {
		
		toSend.clear();
		
		for(int i = 0; i < currentIndex; i++) {
			
			//makes it so Malus cant be sent to path
			if(line[i].getColorEnum()!=ColorEnum.MALUS) toSend.add(line[i]);

			line[i] = null;
		}
		
		currentIndex = 0;
		
		return toSend;
	}
	
	public Tile[] getLine() {
		return this.line;
	}

	public int getPrevious() {		
		return previousIndex;
	}
	
	public void setPrevious(int previous) {
		this.previousIndex = previous;
	}
	
	public boolean isEmpty() {
		//if the first line is empty, then the entire line is empty
		return line[0] == null;
	}
	
	//display Malus in the console
	
	public void display() {
		System.out.println("malus");
		
		int i =0;
		
		while(i<currentIndex) {
			System.out.print(line[i].getColorEnum() + " ");
			i++;
		}
		
		while(i< 7) {
			System.out.print("- ");
			i++;
		}
		
		System.out.println();
	}

	
	
}
