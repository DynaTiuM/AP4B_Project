package view;

//Class used to simplify determining the coordinates of one of the components of the ViewPanel
public class Position {
	
	  //Coordinates x an y
	  private final int x;
	  private final int y;

	  public Position(int x, int y) {
	    this.x = x;
	    this.y = y;
	  }

	  public int getX() {
	    return x;
	  }

	  public int getY() {
	    return y;
	  }
	}