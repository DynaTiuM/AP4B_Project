package visual;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;



public class GameFrame extends JFrame {
	
	private JLayeredPane supperposition_m;
	private BordVisual bords_m;
	
	public GameFrame(int num) {
		
		initPane(num);
		initFrame();
	}
	
	
	private void initPane(int num) {
		
		
		supperposition_m = new JLayeredPane();
		
		bords_m = new BordVisual(num);
		
		
		//game_panel_m = new GamePanel(num, supperposition_m);
		supperposition_m.setBounds(0,0,800,800);
		supperposition_m.add(bords_m, Integer.valueOf(0));
		//supperposition_m.add(game_panel_m, Integer.valueOf(0));
		
		supperposition_m.setVisible(true);
		
		this.add(supperposition_m);
	}
	
private void initFrame() {
		
			
		this.setVisible(true);
		
		this.setSize(new Dimension(800,800));
		//this.pack();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

	}
	
}
