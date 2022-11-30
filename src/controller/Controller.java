package controller;

import machine.Machine;
import visual.GameFrame;
import visual.MenuFrame;

public class Controller {
	private Machine machine_m;
	private MenuFrame menu_m;
	private GameFrame game_frame_m;
	
	
	public Controller(){
		machine_m = new Machine(this);
		menu_m = new MenuFrame(this);
		
	}
	
	
	public void newFrame(int players) {
		game_frame_m = new GameFrame(players);
		machine_m.setNewGame(players);
	}
	
	
	public void sendBord() {
		machine_m.sendBord(0);
	}
}
