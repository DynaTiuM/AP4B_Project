package controller;

import View.View;
import model.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionMalus implements ActionListener {
	
	private final Game model;
	private final View view_m;
	
	public ActionMalus(Game ref, View view) {
		this.model = ref;
		this.view_m = view;
	}
	
	public void close() {
		view_m.closePopup();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.updateMalusModel();
		close();
	}

}
