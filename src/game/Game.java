package game;

public class Game {
	
	private Pot pot;
	private Bord[] players;
	
	public Game(int numberPlayers) {
		pot = new Pot(numberPlayers);

		players = new Bord[numberPlayers];
		
		int i = 0;
		
		for(Bord b : players) {
			i++;
			b.setPosition(i);
		}
		
		new Bag();
	}
}
