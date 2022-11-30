package machine;

import controller.Controller;

public class Machine {

	Game game_m;
	Controller controller_m;
	
	public Machine(Controller controller_p) {
		controller_m = controller_p;
		
	}
	
	public void setNewGame(int nb_players) {
		game_m = new Game(this, nb_players);
	}
	
	
	
}
