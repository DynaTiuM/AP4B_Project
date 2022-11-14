package game;
public class Game {
	
	private Pot pot;
	private Bord[] players;
	
	public Game(int numberPlayers) {
		pot = new Pot(numberPlayers);

		players = new Bord[numberPlayers];
		
		for(int i = 0; i < numberPlayers; i++) {
			switch(i) {
			case 0 :
				players[i] = new Bord(0, 0);
				break;
			case 1 :
				players[i] = new Bord(0, 1);
				break;
			case 2 :
				players[i] = new Bord(1, 0);
				break;
			case 3 :
				players[i] = new Bord(1, 1);
				break;
			}
		}
	}
	
	Bord getPlayer(int index) {
		return players[index];
	}
	
	Pot getPot() {
		return pot;
	}
}
