package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Game;

public class ActionMalus implements ActionListener {
	
	private int current_player;
	private Game model;
	
	public ActionMalus(Game ref) {
		this.model = ref;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	//	this.model.getCurrentPlayer(this.current_player).
	}

}
