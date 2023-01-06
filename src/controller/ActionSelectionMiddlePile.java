package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import model.Game;
import model.Tile;

public class ActionSelectionMiddlePile implements ActionListener {
	private final Game model;
	private final int index;

	public ActionSelectionMiddlePile(Game gameRef, int index) {
		this.index = index;
		this.model = gameRef;
	}
	
	public void open() {
		model.getInformationForPopUp();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		LinkedList<Tile> toSend = model.modifyMiddlePile(index);
		model.sendSelectionToBord(toSend);
		model.sendCompleteMiddlePileToView(true);
		open();
	}
}
