package game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class PanelA extends JPanel {

	PanelA(int pos){
		init(pos);
	}
	
	
	public void init(int pos) {
		this.setBounds(new Rectangle(0,0,50 + 50 * pos * pos,50));
		this.setFocusable(true);
		this.setLayout(null);
		
		switch(pos) {
		case 0: this.setBackground(Color.blue);
			break;
		case 1: this.setBackground(Color.orange);
			break;
		case 2: this.setBackground(Color.GREEN);
			break;
		}
		
		
		
		
		this.setVisible(true);
		this.setOpaque(true);
	}
}
