package game;

public class Position {
	
	private	int x;
	private	int y;
	
	Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int request) {
		this.y = request;
	}
	
	public void setX(int request) {
		this.x = request;
	}
}
