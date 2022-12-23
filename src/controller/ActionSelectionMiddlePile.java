package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import model.Game;
import model.MiddlePile;
import model.Pile;
import model.Tile;

public class ActionSelectionMiddlePile implements ActionListener {
	
	private Pile pile;
	private MiddlePile middlePile;
	private int current_player;
	private Game model;
	private LinkedList<Tile> toSend;
	
	public ActionSelectionMiddlePile(Game ref) {
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
