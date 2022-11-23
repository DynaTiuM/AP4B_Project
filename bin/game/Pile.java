package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.lang.Math;
import javax.swing.*;

public class Pile implements ActionListener {
	final static int GAME_WIDTH = 800;
	final static int BORD_SIZE = GAME_WIDTH/6*2;
	
	final static int PILE_SIZE = (int) Math.sqrt(2*Math.pow((BORD_SIZE/10)*2, 2));
	private LinkedList<Tile> tiles;
	private Position position_m;
	
	private JPanel panel_m;
	
	public JButton seeOptions_m;
	
	private JLayeredPane pane_m;
	
	//why not have the pile class be a JButton that implements ActionListener ?
	private JButton take_tiles;
	
	private Selection selection_m;
	
	Pot pot_m;
	
	private boolean active;
	
	private JPanel top_leftj;
	
	
	int xposition_tile;
	int yposition_tile;
	int xposition_circle;
	int yposition_circle;
	
	
	public Pile(Position position_p, JLayeredPane pane_p, JPanel panel_p, Pot pot_p) {
		active = false;
		
		
		tiles = new LinkedList<Tile>();
		tiles.add(new Black());
		tiles.add(new Red());
		tiles.add(new Blue());
		tiles.add(new White());
		
		
		position_m = position_p;
		
		
		xposition_tile = BORD_SIZE +10 + position_m.getX() * (PILE_SIZE + BORD_SIZE/10 + 3);
		yposition_tile = 102 + position_m.getY() * (PILE_SIZE + BORD_SIZE/10);
		
		xposition_circle = xposition_tile - (PILE_SIZE/2) + BORD_SIZE/10;
		yposition_circle = yposition_tile - (PILE_SIZE/2) + BORD_SIZE/10;
		
		
		pot_m = pot_p;
		
		pane_m = pane_p;
		panel_m = panel_p;
		
		initiateButton();
	}
	
	public void randomDistribution() {
		
		for(int i = 0; i < 4; i++) {
			tiles.add(Bag.getRandomTile());
		}
	}
	
	private void initiateButton() {
		seeOptions_m = new JButton();
		seeOptions_m.setBounds(new Rectangle(xposition_tile +(BORD_SIZE/10)*5/2, yposition_tile + (BORD_SIZE/10)/2, BORD_SIZE/10, BORD_SIZE/10));
		
		seeOptions_m.addActionListener(this);
		panel_m.add(seeOptions_m);
	}
	
	public void draw(Graphics g) {
		
		
		
		
		g.setColor(Color.CYAN);
		g.fillOval(xposition_circle , yposition_circle , PILE_SIZE, PILE_SIZE);
	
		g.setColor(Color.BLACK);
		//for thicker lines
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1));
		g2.drawOval(xposition_circle , yposition_circle , PILE_SIZE, PILE_SIZE);
		
		int i = 0;
		int y = 0;
		for(Tile p: tiles) {
			
			g.setColor(p.color_m);
			g.fillRect(xposition_tile+ i *(BORD_SIZE/10), yposition_tile + y*(BORD_SIZE/10), BORD_SIZE/10, BORD_SIZE/10);
			g.setColor(Color.black);
			g.drawRect(xposition_tile+ i *(BORD_SIZE/10), yposition_tile + y*(BORD_SIZE/10), BORD_SIZE/10, BORD_SIZE/10);
			if(i<1) {
				i++;
			}else {
				i=0;
				y++;
			}
			
		}
		
		if(active) {
			selection_m.draw(g);
			//selection_m.setBackground(Color.pink);
			System.out.println("active");
		}
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
	
		
		
		selection_m = new Selection(this);
		active = true;
		pane_m.add(selection_m, Integer.valueOf(7));
		pot_m.disablePiles();
		
	}
	
	public void selectionEvent() {
		System.out.println("hey:: " + position_m.getX() + " " + position_m.getY());
		closeSelcetion();
	}
	
	
	
	public void closeSelcetion() {
		pane_m.remove(selection_m);
		pane_m.repaint();
		active = false;
		pot_m.reSablePiles();
	}

	public LinkedList<Tile> getTiles(){
		return this.tiles;
	}
	
	public JButton getButton() {
		return this.seeOptions_m;
	}
	
	
	
	public void addLabelsSelection(JPanel panel) {
		pane_m.add(panel, Integer.valueOf(7));
		pane_m.repaint();
	}
	
	
	

}