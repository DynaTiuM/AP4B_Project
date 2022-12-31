package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import View.View;
import model.Game;
import model.MiddlePile;
import model.Pile;
import model.Tile;

public class ActionSelectionPile implements ActionListener {
	
	private LinkedList<Tile> tiles;
	private Game model;
	private int ID;
	private View view_m;
	
	public ActionSelectionPile(Game ref, int number, View ref_view) {
		this.model = ref;
		this.ID = number;
		this.view_m = ref_view;
		this.tiles = new LinkedList<Tile>();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Pile pile = model.getPile(ID);
		for(Tile t : pile.getTiles()) {
			tiles.add(t);
		}
	
		new ActionDisplayPile(view_m, tiles, ID);
	}

}
