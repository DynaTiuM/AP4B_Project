package model;

import java.awt.Color;



public class Pattern {

	private Tile[][] grid;
	private Color[] new_colorpos;
	
	private boolean end_trigger;
	
	private int score;
	
	public Pattern() {
		
		grid = new Tile[5][5];
		
		new_colorpos = new Color[5];

		grid[0][0] = new Tile(Color.blue);
		grid[0][1] = new Tile(Color.orange);
		grid[0][2] = new Tile(Color.red);
		grid[0][3] = new Tile(Color.black);
		grid[0][4] = new Tile(Color.white);
		
		for(int l = 0; l < 4; l++) {
			for(int c = 0; c < 5; c++) {
				if(c + 1 >= 5) {
					new_colorpos[0] = grid[l][c].getColor();
				}else {
					new_colorpos[c+1] = grid[l][c].getColor();
				}
			}
			for(int i = 0; i<5;i++) {
				grid[l+1][i] = new Tile(new_colorpos[i]);				
			}
		}
		
		
		end_trigger = false;
		
	}
	
	public void determineSendingPlace(int index, Tile to_place) {
		int i =0;
		while(grid[index][i].getColorEnum()!=to_place.getColorEnum()) i++;
		
		calculateScore(index, i, to_place);
	}
	
	private void calculateScore(int index, int indexy, Tile to_add) {
		
		//let's suppose we won't encounter any segmenation fault
		grid[index][indexy].setOccupiedTrue();
		
		int x = index;
		int y = indexy;
		
		int total = 1;
		
		x = index-1;
		
		while(x > -1) {
			if(grid[x][indexy].getOccupied()) {
				total++;
				x = x-1;
				//System.out.print(x + " ");
			}else {
				break;
			}
		}
		
		x = index + 1;
		
		while(x <5) {
			if(grid[x][indexy].getOccupied()) {
				total++;
				x++;
				//System.out.print(x + " ");
			}else {
				break;
			}
		}
		
		y = indexy-1;
		
		while(y > -1) {
			if(grid[index][y].getOccupied()) {
				total++;
				y = y - 1;
				//System.out.print(y + " ");
			}else {
				break;
			}
		}
		
		y = indexy+1;
		
		while(y <5) {
			if(grid[index][y].getOccupied()) {
				total++;
				y++;
				//System.out.print(y + " ");
			}else {
				break;
			}
		}
		
		score +=total;
		System.out.println("updated score : " + score);
		
		
		//this.display();
		
	}
	
	public void substract(int malus) {
		score -= malus;
		if(score<0) score = 0;
		System.out.println("Score with malus : " + score);
	}
	
	public void calculateEndOfGameBonuses() {
		for(int i =0; i<5; i++) {
			checkLineFull(i);
			checkColumnFull(i);
		}
	}
	
	public void display() {
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<5; j++) {
				if(grid[i][j].getOccupied()) {
					System.out.print( grid[i][j].getColorEnum() +" ");
				}else {
					System.out.print("_ ");
				}
				
				
			}
			System.out.println();
		}
	}
	
	public void checkLineFull(int index){
		int check = 0;
		
		for(int i =0; i<5; i++) {
			if(grid[index][i].getOccupied()) check++;
		}
		
		if(check ==5) {
			score +=2;
			end_trigger = true;
			System.out.println("ENDGAME TRIGGER");
			System.out.println("updated score : "+score);
		}
	}
	
	public void checkColumnFull(int index) {
		int check = 0;
		
		for(int i =0; i<5; i++) {
			if(grid[i][index].getOccupied()) check++;
		}
		
		if(check ==5) {
			score +=7;
		}
	}
	
	public Tile[][] getGrid() {
		return this.grid;
	}
	
	public int getScore() {
		return this.score;
	}
	
	
}
