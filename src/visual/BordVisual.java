package visual;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BordVisual extends JPanel implements WindowProperties{
	private Position position_m;
	
	private GridVisual[] grid_visual_m;
	private LineSelectionVisual[] line_visual_m;
	
	int nb_players_m;
	
	private Color bord;
	
	BordVisual(int nbPlayers){
		
		
		nb_players_m = nbPlayers;
		
		grid_visual_m = new GridVisual[nbPlayers];
		line_visual_m  = new LineSelectionVisual[nbPlayers];
		
		
		for(int i =0; i<nbPlayers; i++) {
			switch(i) {
			case 0 :
				grid_visual_m[i] = new GridVisual(new Position(0,0));
				line_visual_m[i] = new LineSelectionVisual(new Position(0,0));
				break;
			case 1 :
				grid_visual_m[i] = new GridVisual(new Position(0,1));
				line_visual_m[i] = new LineSelectionVisual(new Position(0,1));
				break;
			case 2 :
				grid_visual_m[i] = new GridVisual(new Position(1,0));
				line_visual_m[i] = new LineSelectionVisual(new Position(1,0));
				break;
			case 3 :
				grid_visual_m[i] = new GridVisual(new Position(1,1));
				line_visual_m[i] = new LineSelectionVisual(new Position(1,1));
				break;
			}
			
		}
		
		
		System.out.print("BordVisual");
		
		this.setBounds(0,0,800,800);
		this.setVisible(true);
		
		bord = new Color(193,189,180);
		repaint();
		
	}
	
	public void draw(Graphics g) {
		
		for(int i =0; i<nb_players_m;i++) {
			g.setColor(bord);
			
			
			grid_visual_m[i].draw(g);
			line_visual_m[i].draw(g);
		}
		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//super.paintComponent(g);
		System.out.print("draw BordVisual");
		draw(g);
	}

}
