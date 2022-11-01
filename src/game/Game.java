package game;

public class Game {
	
	private Pot pot;
	private Bord[] players;
	
	public Game(int numberPlayers) {
		pot = new Pot(numberPlayers);

		players = new Bord[numberPlayers];
		
		for(int i = 0; i < numberPlayers; i++) {
			players[i] = new Bord();
			players[i].setPosition(i);
		}
		
		//new Bag();
	}
}
