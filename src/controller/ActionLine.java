package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.View;
import model.Game;

public class ActionLine implements ActionListener {
	
	private final int lineSupervised;
	private final Game model;
	private final View viewRef;
	
	public ActionLine(Game ref, int ID, View view) {
		this.model = ref;
		this.viewRef = view;
		this.lineSupervised = ID;
	}
	
	public void close() {
		viewRef.closePopup();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.lineSelected(lineSupervised);
		close();
	}

}
