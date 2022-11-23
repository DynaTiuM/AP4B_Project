package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
	
	JLayeredPane supperposition_m;
	GamePanel game_panel_m;
	Selection selection_m;
	
	public GameFrame(int num){
		System.out.println("v");
		
		
		
		initPane(num);
		
		
		
		initFrame(num);
	}

	
	private void initPane(int num) {
		
		
		supperposition_m = new JLayeredPane();
		game_panel_m = new GamePanel(num, supperposition_m);
		supperposition_m.setBounds(new Rectangle(0,0,500,800));
		supperposition_m.add(game_panel_m, Integer.valueOf(0));
		
		
		/*JPanel paneltest = new JPanel();
		paneltest.setBounds(new Rectangle(0,0,500,500));
		paneltest.setBackground(Color.ORANGE);
		paneltest.setVisible(true);
		paneltest.setOpaque(true);
		supperposition_m.add(paneltest, Integer.valueOf(1));*/
		
		
		supperposition_m.setVisible(true);
		
		this.add(supperposition_m);
	}
	
	private void initFrame(int num) {
		
		//this.add(game_panel_m);
		
		this.setVisible(true);
		
		this.setSize(new Dimension(800,800));
		//this.pack();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	public GamePanel getPanel() {
		return this.game_panel_m;
	}
	
	public JLayeredPane getLayeredPane() {
		return this.supperposition_m;
	}
	
	public Selection getSelection() {
		return this.selection_m;
	}
	
}
