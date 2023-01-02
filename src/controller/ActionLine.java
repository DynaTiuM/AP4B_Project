package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Game;

public class ActionLine implements ActionListener {
	
	private int current_player;
	private int lineSupervied;
	private Game model;
	
	public ActionLine(Game ref) {
		this.model = ref;
		this.current_player = this.model.getCurrentPlayer();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
