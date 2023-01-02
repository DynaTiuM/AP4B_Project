package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import View.View;
import model.Game;
import model.Pile;
import model.Tile;

public class ActionDisplayPile implements ActionListener {
	
	private View view_m;
	private LinkedList<Tile> tiles;
	private int ID;
	
	public ActionDisplayPile(View ref, LinkedList<Tile> tiles, int ID) {
		this.view_m = ref;
		this.tiles = tiles;
		
		this.ID = ID;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
