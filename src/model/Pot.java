package model;

public class Pot {

	private Pile[] piles;
	private MiddlePile middle_pile;
	private Bag bag;
	
	private Game game_ref;
	
	public Pot(int numberOfPlayers, Game ref) {
		
		game_ref = ref;
		
		middle_pile = new MiddlePile(this);
		
		instanciatePiles(numberOfPlayers);
		
		bag = new Bag(piles);
	}
	
	
	private void instanciatePiles(int numberOfPlayers) {
		piles = new Pile[1 + numberOfPlayers * 2];
		for(int i = 0; i< 1 + numberOfPlayers * 2; i++) piles[i] = new Pile(game_ref, middle_pile, this);
	}
	
	public Pile getPileIndex(int index) {
		return piles[index];
	}
	
}
