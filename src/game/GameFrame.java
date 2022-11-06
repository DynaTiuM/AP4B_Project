package game;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
	public GameFrame(int num){
		this.add(new GamePanel(num));
		this.setVisible(true);
		this.pack();	
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

}
