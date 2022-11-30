package machine;

import java.awt.Color;
import java.util.LinkedList;

public class LineSelection {

	private Line[] lines_m;
	
	private Grid grid_m;
	private MalusGrid malus_grid_m;
	private Bord bord_m;
	
	
	public LineSelection(Bord bord_p) {
		
		bord_m = bord_p;
		lines_m = new Line[5];
		
		grid_m = new Grid(this);
		malus_grid_m = new MalusGrid(this);
		
		for(int i =0; i<5; i++) {
			lines_m[i] = new Line(malus_grid_m);
			lines_m[i].initArray(i+1);
		}
		
		
		
		addChoiceLine(new Tile(Color.BLACK),2);
		addChoiceLine(new Tile(Color.BLACK),2);
		//addChoiceLine(new Tile(Color.BLACK),2);
		//addChoiceLine(new Tile(Color.BLACK),2);
		
		
		addChoiceLine(new Tile(Color.ORANGE),0);
		
		//addChoiceLine(new Tile(Color.RED), 1);
		addChoiceLine(new Tile(Color.ORANGE),1);
		
		LinkedList<Tile> test = new LinkedList<Tile>();
		
		for (int i =0; i<10; i++) {
			test.add(new Tile(Color.ORANGE));
		}
		
		addChoiceLine(test,1);
		display();
		checkLinesFull();
		
		
		
		//getMalus();
		
		display();
		
		
	}
	
	
	
	public void checkColorLine(int index, Tile tile_p) {
		lines_m[index].checkColor(tile_p);
	}
	
	public void addColorLine(int index, Tile tile_p) {
		lines_m[index].addColor(tile_p);
	}
	
	public void setTileIndexLine(int index, Tile tile_p, int index2) {
		lines_m[index].setTileIndex(index2, tile_p);
	}
	
	public Tile[] getLine(int index) {
		return lines_m[index].getTiles();
	}
	
	public void addChoiceLine(LinkedList<Tile> choice, int index) {
		lines_m[index].addChoice(choice);
	}
	
	public void addChoiceLine(Tile choice, int index) {
		lines_m[index].addChoice(choice);
	}
	
	public void display() {
		//System.out.println("Display :");
		grid_m.display();
		for(int i =0; i<5; i++) {
			lines_m[i].display();
			
		}
		malus_grid_m.display();
	}
	
	public void sendGrid(int index, Line to_send) {
		//System.out.println("sending line");
		grid_m.determineSendingPlace(index, to_send.getTileIndex(0));;
	}
	
	public void checkLinesFull() {
		System.out.println("checking");
		for(int i =0; i<5; i++) {
			if(lines_m[i].checkFull()) {
				sendGrid(i, lines_m[i]);
				lines_m[i].clear();
			}
		}
		
		getMalus();
	}
	
	public void getMalus() {
		grid_m.substract(malus_grid_m.computateMalus());
		malus_grid_m.clear();
	}
	
	public Line[] getLineSelection() {
		return this.lines_m;
	}
	
	
	
}


