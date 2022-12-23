package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import model.Game;
import model.MiddlePile;
import model.Pile;
import model.Tile;

public class ActionSelectionPile implements ActionListener {
	
	private Pile pile;
	private MiddlePile middlePile;
	private int current_player;
	private Game model;
	private LinkedList<Tile> to_send;
	
	public ActionSelectionPile(Game ref) {
		this.model = ref;
		this.current_player = this.model.getCurrentPlayer();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
