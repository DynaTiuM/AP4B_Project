package controller;

import view.View;
import model.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionMalus implements ActionListener {
	
	private final Game model;
	private final View viewRef;
	
	public ActionMalus(Game ref, View view) {
		this.model = ref;
		this.viewRef = view;
	}
	
	public void close() {
		viewRef.closePopup();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.updateMalusModel();
		close();
	}

}
