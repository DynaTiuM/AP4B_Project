package model;

import java.awt.Color;
import java.util.HashMap;

import View.Position;



public class Pattern {

	private Tile[][] grid;
	private Color[] new_colorpos;
	private HashMap<Tile, Position> newTiles;
	private Bord bord_ref;
	
	private boolean end_trigger;
	
	private int score;
	
	public Pattern(Bord bord) {

		newTiles = new HashMap<Tile, Position>();
		this.bord_ref = bord;
		grid = new Tile[5][5];
		
		new_colorpos = new Color[5];

		grid[0][0] = new Tile(Color.MAGENTA);
		grid[0][1] = new Tile(Color.BLUE);
		grid[0][2] = new Tile(Color.YELLOW);
		grid[0][3] = new Tile(Color.GREEN);
		grid[0][4] = new Tile(Color.ORANGE);
		
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
	
	public void scoreMalus(int malus) {
		score = score - malus;
		if (score < 0) score = 0;
	}
	
	public void determineSendingPlace(int index, Tile to_place) {
		int i = 0;
		while(i < 5) {
			System.out.print("index : " + index + ", i : " + i);
			if(grid[index][i].getColorEnum() != to_place.getColorEnum()) 
				i++;
			else {
				if(!grid[index][i].getOccupied()) {
					newTiles.put(to_place, new Position(i, index));
					System.out.println("new Tiles : Pattern | " + newTiles + "Position : " + i + index);
				}
				calculateScore(index, i, to_place);
				break;
			}
		}

		
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
			if(checkLineFull(i)) score+=2;
			if(checkColumnFull(i)) score +=7;
			
			System.out.println("End of Game bonuses updated score " + score);
		}
	}
	
	public void checkEndGame() {
		for(int i =0; i <5; i++) {
			if(checkLineFull(i)) {
				this.bord_ref.endOfGame();
				break;
			}
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
		
		System.out.println("\nscore : " + score);
	}
	
	public boolean checkLineFull(int index){
		int check = 0;
		
		for(int i =0; i<5; i++) {
			if(grid[index][i].getOccupied()) check++;
		}
			
		return check == 5;
	}
	
	public boolean checkColumnFull(int index) {
		int check = 0;
		
		for(int i =0; i<5; i++) {
			if(grid[i][index].getOccupied()) check++;
		}
		
		return check == 5;
	}
	
	public Tile[][] getGrid() {
		return this.grid;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void sendPattern() {
		bord_ref.updatePatternView(this.newTiles);
	}
	
	
}
