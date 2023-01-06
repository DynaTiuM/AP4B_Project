package model;


import java.util.HashMap;

import view.Position;


public class Pattern {

	private final Tile[][] grid;
	private final HashMap<Tile, Position> newTiles;
	private final Bord bordRef;
	private int score;
	
	public Pattern(Bord bord) {

		newTiles = new HashMap<>();
		this.bordRef = bord;
		grid = new Tile[5][5];

		ColorEnum[] newColorPos = new ColorEnum[5];

		grid[0][0] = new Tile(ColorEnum.M);
		grid[0][1] = new Tile(ColorEnum.B);
		grid[0][2] = new Tile(ColorEnum.Y);
		grid[0][3] = new Tile(ColorEnum.G);
		grid[0][4] = new Tile(ColorEnum.O);
		
		for(int l = 0; l < 4; l++) {
			for(int c = 0; c < 5; c++) {
				if(c + 1 >= 5) {
					newColorPos[0] = grid[l][c].getColorEnum();
				}else {
					newColorPos[c+1] = grid[l][c].getColorEnum();
				}
			}
			for(int i = 0; i<5;i++) {
				grid[l+1][i] = new Tile(newColorPos[i]);				
			}
		}
	}
	
	public void scoreMalus(int malus) {
		score = score - malus;
		if (score < 0) score = 0;
	}
	
	public void determineSendingPlace(int index, Tile toPlace) {
		int i = 0;
		while(i < 5) {
			if(grid[index][i].getColorEnum() != toPlace.getColorEnum()) 
				i++;
			else {
				if(!grid[index][i].getOccupied()) {
					newTiles.put(toPlace, new Position(i, index));
				}
				calculateScore(index, i);
				break;
			}
		}
	}
	
	private void calculateScore(int index, int indexY) {
		
		//let's suppose we won't encounter any segmenation fault
		grid[index][indexY].setOccupiedTrue();
		
		boolean firstLine = false;
		boolean firstColumn = false;
		
		int x;
		int y;
		
		int total = 1;
		
		x = index-1;
		
		while(x > -1) {
			if(grid[x][indexY].getOccupied()) {
				firstLine = true;
				total++;
				x = x-1;
			}else {
				break;
			}
		}
		
		x = index + 1;
		
		while(x <5) {
			if(grid[x][indexY].getOccupied()) {
				firstLine = true;
				total++;
				x++;
			}else {
				break;
			}
		}
		
		
		
		y = indexY-1;
		
		while(y > -1) {
			if(grid[index][y].getOccupied()) {
				firstColumn = true;
				total++;
				y = y - 1;
			}else {
				break;
			}
		}
		
		y = indexY+1;
		
		while(y <5) {
			if(grid[index][y].getOccupied()) {
				firstColumn = true;
				total++;
				y++;
			}else {
				break;
			}
		}
		
		if(firstColumn && firstLine) total++;
		
		score +=total;
	}

	
	public void calculateEndOfGameBonuses() {
		for(int i =0; i<5; i++) {
			if(checkLineFull(i)) score+=2;
			if(checkColumnFull(i)) score +=7;
			
			System.out.println("End of Game bonuses updated score " + score);
		}
	}
	
	public boolean checkEndGame() {
		for(int i =0; i <5; i++) {
			if(checkLineFull(i)) {
				return true;
			}
		}

		return false;
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

	// Check if the tile's color is already on the pattern, in order to disable the Button of this line
	public boolean isAlreadyOnPattern(int size, Tile tile){
		for(int i = 0; i < 5; i++) {
			if(grid[size - 1][i].getColorEnum() == tile.getColorEnum() && grid[size - 1][i].getOccupied()) {
				return true;
			}
		}
		return false;
	}
	
	public Tile[][] getGrid() {
		return this.grid;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void sendPattern() {
		bordRef.updatePatternView(this.newTiles);
	}
}
