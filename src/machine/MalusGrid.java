package machine;

public class MalusGrid {
	private Tile[] line;
	private int[] penalty;
	
	private LineSelection selection_m;
	private int current_index;
	
	public MalusGrid(LineSelection selection_p){
		current_index=0;
		
		
		
		penalty = new int[7];
		initPenalty();
	
		line = new Tile[7];
		
		for(int i = 0; i<7; i++) {
			line[i] = new Tile();
		}
		
		selection_m = selection_p;
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
		for(int i = 0; i<7; i++) {
			if(line[i].getOccupied()) {
				System.out.print(line[i].getColorEnum()+" ");
			}else {
				System.out.print("- ");
			}
		}
		System.out.println();
	}
	
	public void clear() {
		for(int i =0; i<current_index; i++) {
			line[i].setOccupiedFalse();
		}
		
		current_index = 0;
	}
	
	public Tile[] getLine() {
		return this.line;
	}
	
	
}
