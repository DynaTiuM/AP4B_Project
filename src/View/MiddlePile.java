package View;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MiddlePile extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int RECT_SIZE = Bord.RECT_SIZE;
  private Position position;
  private JButton button;

  public MiddlePile(Position position) {
	  super();
	setPreferredSize(new Dimension(View.WIDTH / 3, View.HEIGHT / 2));
    setOpaque(false);
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
		//MalusTile malus = new MalusTile(new Position(position.getX() + RECT_SIZE * (i % 7), position.getY() + yPosition));
		//malus.draw(g);
	}
	
	MalusTile malus = new MalusTile(new Position(200, 0));
	malus.draw(g);
  }
  
  @Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    draw(g);
	    }    

  public void setButton(boolean value) {
    button.setVisible(value);
  }
}