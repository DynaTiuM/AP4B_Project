package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import View.View;
import model.Game;
import model.MiddlePile;
import model.Pile;
import model.Tile;

public class ActionSelectionTile implements ActionListener {
	
	private Game model;
	private int ID;
	private int numberPile;
	
	public ActionSelectionTile(Game ref, int ID, int numberPile, View ref_view) {
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
