package machine;

public class Game {
	
	private Machine machine_m;
	private Bord[] bord_m;
	
	public Game(Machine machine_p, int number_players) {
		machine_m = machine_p;
		
		bord_m = new Bord[number_players];
		for(int i =0; i<number_players; i++) {
			bord_m[i] = new Bord(this);
		}
		
	}
	
	public Bord[] getBords() {
		return this.bord_m;
	}
	
	public Bord getBord(int index) {
		return this.bord_m[index];
	}
	
}
