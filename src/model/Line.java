package model;

import java.util.LinkedList;



public class Line {

	private Tile[] linearray;
	private int current_index;
	private int size;
	private boolean[] color_presence;

	
	private ColorEnum current_color;
	private Malus malus_m;
	
	private LinkedList<Tile> to_send;
	
	public Line(Malus malus_p, int size) {
		malus_m = malus_p;
		current_index=0;
		color_presence = new boolean[5];
		current_color = null;
		for(int i = 0; i<5;i++) {
			color_presence[i]=false;
		}
		
		initArray(size);
		
		
		
	}
	
	public void addChoice(LinkedList<Tile> tiles) {
		
		int i = current_index;
		for(Tile p: tiles) {
			if(i<size) {
					//addColor(p);
				setTileIndex(i, p);
				i++;
			}else {
				malus_m.addTile(p);
			}
			current_index = i;
				
		}
		
		
	}
	
	public boolean checkFull() {
		
		int check = 0;
		for(int i=0; i<size; i++) {
			if(linearray[i].getColor()!=null) check++;
		}
		return check == size;
	}
	
	public void addChoice(Tile tile) {
		
			setTileIndex(current_index, tile);
			
			current_index++;
		
	}
	
	public void addColor(Tile tile_p) {
		
		color_presence[tile_p.getColorEnum().ordinal()] = true;
	}
	
	public boolean isPossible(Tile tile) {
		return !checkColor(tile) &&(tile.getColorEnum()==current_color || current_color == null);
	}
	
	public boolean checkColor(Tile tile_p) {
		return color_presence[tile_p.getColorEnum().ordinal()];
	}
	
	public Tile getTileIndex(int index) {
		return linearray[index];
	}
	
	public void setTileIndex(int index, Tile tile_p){
		if(index<size) {
			current_color = tile_p.getColorEnum();
			linearray[index].setColor(tile_p.getColor());
			linearray[index].setOccupiedTrue();
			//System.out.print("added ");
		}else {
			malus_m.addTile(tile_p);
		}
		
	}

	private void initArray(int index) {
		linearray = new Tile[index];
		/*for(int i =0; i<index; i++) {
			linearray[i] = new Tile();
		}*/
		size = index;
	}
	
	public Tile[] getTiles() {
		return this.linearray;
	}
	
	public void display() {
		for (int i = 0; i<size;i++) {
			if(linearray[i].getOccupied()) {
				System.out.print(linearray[i].getColorEnum() + " "  );
			}else {
				System.out.print("- ");
			}
		}
		//System.out.println("  index : " + current_index);
		System.out.println();
		//System.out.println("--------------------------------------------");
		
	}
	
	public LinkedList<Tile> clear() {
		to_send.clear();
		
		for(int i =0; i<size; i++) {
			linearray[i].setOccupiedFalse();
			to_send.add(linearray[i]);
		}
		
			
		current_color = null;
		current_index = 0;
		
		return to_send;
	}
	
	public Tile[] getLine() {
		return this.linearray;
	}
	
	
	
}
