package game;

import java.awt.Graphics;


import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

// A Pot has many piles that each has 4 tiles. It also has a middle pile
public class Pot {
	
	private ArrayList<Pile> piles;
	private MiddlePile middlePile;
	
	private ListIterator<Pile> iterator_pile;
	
	private JPanel panel_m;
	private JLayeredPane pane_m;
	
	public Pot(int players, JLayeredPane pane_p, JPanel panel_p) {
		piles = new ArrayList<>();
		middlePile = new MiddlePile();
		pane_m = pane_p;
		panel_m = panel_p;
		//pane_m = frame_p.getLayeredPane();
		instanciatePiles(players);
		//iterator_pile = piles.listIterator();
	}
	
	private void instanciatePiles(int players) {
		int numberOfPiles = 0, i;
		
		numberOfPiles = 1 + players * 2;
		
		for( i = 0; i<(numberOfPiles-1)/2; i++) {
			piles.add(new Pile(new Position(0, i), pane_m, panel_m, this));
			piles.add(new Pile(new Position(1,i), pane_m, panel_m, this));
		}
		
		piles.add(new Pile(new Position(0,i), pane_m, panel_m, this));
		
	}
	

	
	public void initialisation() {
		for(Pile p : piles) {
			p.randomDistribution();
		}
	}
	
	
	public void draw(Graphics g) {
		for(Pile p : piles) {
			p.draw(g);
		}
	}
	
	public void disablePiles() {
		for(Pile p : piles) {
			p.getButton().setEnabled(false);
		}
	}
	
	public void reSablePiles() {
		for(Pile p : piles) {
			p.getButton().setEnabled(true);
		}
	}
}
