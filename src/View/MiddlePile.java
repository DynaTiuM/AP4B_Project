package View;

import java.awt.Graphics;
import javax.swing.JButton;

public class MiddlePile {
	
	private static final int RECT_SIZE = Bord.RECT_SIZE;
	private Position position;
	private JButton button;

	public MiddlePile(Position position) {
		this.position = position;
		this.button = new JButton();
	}

	int yPosition = 0;
	  
	public void draw(Graphics g) {
		// Draw the pile at the specified position
		for (int i = 0; i < 28; i++) {
			
			if (i != 0 && i % 7 == 0) {
				yPosition += RECT_SIZE;
			}
			
			g.drawRect(position.getX() + RECT_SIZE * (i % 7), position.getY() + yPosition, RECT_SIZE, RECT_SIZE);
		}
	}

	public void setButton(boolean value) {
		button.setVisible(value);
	}
	
}