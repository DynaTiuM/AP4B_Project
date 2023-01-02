package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import View.View;
import model.Game;
import model.MiddlePile;
import model.Pile;
import model.Tile;

public class ActionSelectionMiddlePile implements ActionListener {
	
	private Pile pile;
	private MiddlePile middlePile;
	private View view_m;
	private Game model;
	private int index;
	private LinkedList<Tile> toSend;
	
	public ActionSelectionMiddlePile(Game ref_game, int index, View ref) {
		this.view_m = ref;
		this.index = index;
		this.model = ref_game;
	}
	
	public void open() {
		model.getInformationForPopUp();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		toSend = model.modifyMiddlePile(index);
		model.sendSelectionToBord(toSend);
		model.sendCompleteMiddlePileToView(true);
		open();
	}
}
