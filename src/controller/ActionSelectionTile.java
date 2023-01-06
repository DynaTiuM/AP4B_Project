package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Game;

public class ActionSelectionTile implements ActionListener {
	
	private final Game model;
	private final int ID;
	private final int numberPile;
	
	public ActionSelectionTile(Game ref, int ID, int numberPile) {
		this.model = ref;
		this.ID = ID;
		this.numberPile = numberPile;
	}
	
	public void open() {
		model.getInformationForPopUp();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		model.setTilesSelectedToHand(numberPile, ID);
		open();
	}

}
