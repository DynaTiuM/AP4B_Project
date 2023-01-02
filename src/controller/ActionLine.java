package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.View;
import model.Game;

public class ActionLine implements ActionListener {
	
	private int lineSupervised;
	private Game model;
	private View view_m;
	
	public ActionLine(Game ref, int ID, View view) {
		this.model = ref;
		this.view_m = view;
		this.lineSupervised = ID;
	}
	
	public void close() {
		view_m.closePopup();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.lineSelected(lineSupervised);
		close();
	}

}
