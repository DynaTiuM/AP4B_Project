package machine;

public class Bord {
	private int score;
	
	private Game game_m;
	
	
	private LineSelection selection_m;
	
	public Bord(Game game_p) {
		
		game_m = game_p;
		selection_m = new LineSelection(this);
	}
}
