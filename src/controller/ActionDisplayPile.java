package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Game;

public class ActionDisplayPile implements ActionListener {
	
	private int current_player;
	private Game model;
	
	public ActionDisplayPile(Game ref) {
		this.model = ref;
		this.current_player = this.model.getCurrentPlayer();
	}
	
	private void open() {
		
	}
	
	private void close() {
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
