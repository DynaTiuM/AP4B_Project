package machine;

import java.awt.Color;

public class Grid {

	private Tile[][] grid_m;
	
	private int score;
	private boolean end_trigger;
	
	private LineSelection selection_m;
	
	public Grid(LineSelection selection_p){
		grid_m = new Tile[5][5];
		end_trigger = false;
		score = 0;
		
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<5; j++) {
				grid_m[i][j] = new Tile();
			}
		}
		
		
		grid_m[0][0].setColor(Color.BLUE);
		grid_m[0][1].setColor(Color.ORANGE);
		grid_m[0][2].setColor(Color.RED);
		grid_m[0][3].setColor(Color.BLACK);
		grid_m[0][4].setColor(Color.WHITE);
		
		/*grid_m[0][0].setColorEnum(ColorEnum.blue);
		grid_m[0][1].setColorEnum(ColorEnum.orange);
		grid_m[0][2].setColorEnum(ColorEnum.red);
		grid_m[0][3].setColorEnum(ColorEnum.black);
		grid_m[0][4].setColorEnum(ColorEnum.white);*/
		
		Color[] new_colorpos = new Color[5];

		
		
		for(int l = 0; l < 4; l++) {
			for(int c = 0; c < 5; c++) {
				if(c + 1 >= 5) {
					new_colorpos[0] = grid_m[l][c].getColor();
				}else {
					new_colorpos[c+1] = grid_m[l][c].getColor();
				}
			}
			for(int i = 0; i<5;i++) {
				grid_m[l+1][i].setColor(new_colorpos[i]);				
			}
		
			
		}
		
		
		
		//display();
		
		selection_m = selection_p;
	}
	
	public void determineSendingPlace(int index, Tile to_place) {
		int i =0;
		while(grid_m[index][i].getColorEnum()!=to_place.getColorEnum()) i++;
		
		calculateScore(index, i, to_place);
	}
	
	private void calculateScore(int index, int indexy, Tile to_add) {
		
		//let's suppose we won't encounter any segmenation fault
		grid_m[index][indexy].setOccupiedTrue();
		
		int x = index;
		int y = indexy;
		
		int total = 1;
		
		x = index-1;
		
		while(x > -1) {
			if(grid_m[x][indexy].getOccupied()) {
				total++;
				x = x-1;
				//System.out.print(x + " ");
			}else {
				break;
			}
		}
		
		x = index + 1;
		
		while(x <5) {
			if(grid_m[x][indexy].getOccupied()) {
				total++;
				x++;
				//System.out.print(x + " ");
			}else {
				break;
			}
		}
		
		y = indexy-1;
		
		while(y > -1) {
			if(grid_m[index][y].getOccupied()) {
				total++;
				y = y - 1;
				//System.out.print(y + " ");
			}else {
				break;
			}
		}
		
		y = indexy+1;
		
		while(y <5) {
			if(grid_m[index][y].getOccupied()) {
				total++;
				y++;
				//System.out.print(y + " ");
			}else {
				break;
			}
		}
		
		score +=total;
		System.out.println("updated score : " + score);
		checkLine(index);
		checkColumn(indexy);
		
		//this.display();
		
	}
	
	public void substract(int malus) {
		score -= malus;
		if(score<0) score = 0;
		System.out.println("Score with malus : " + score);
	}
	
	public void display() {
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<5; j++) {
				if(grid_m[i][j].getOccupied()) {
					System.out.print( grid_m[i][j].getColorEnum() +" ");
				}else {
					System.out.print("_ ");
				}
				
				
			}
			System.out.println();
		}
	}
	
	public void checkLine(int index){
		int check = 0;
		
		for(int i =0; i<5; i++) {
			if(grid_m[index][i].getOccupied()) check++;
		}
		
		if(check ==5) {
			score +=2;
			end_trigger = true;
			System.out.println("ENDGAME TRIGGER");
			System.out.println("updated score : "+score);
		}
	}
	
	public void checkColumn(int index) {
		int check = 0;
		
		for(int i =0; i<5; i++) {
			if(grid_m[i][index].getOccupied()) check++;
		}
		
		if(check ==5) {
			score +=7;
		}
	}
	
	public Tile[][] getGrid() {
		return this.grid_m;
	}
	
}
