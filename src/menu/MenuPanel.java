package menu;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class MenuPanel extends JPanel implements ActionListener {
	
	// Constructor for the MenuPanel class
	MenuPanel() {
		// Set the size of the panel
		this.setPreferredSize(new Dimension(800,800));
		
		// Set the background color of the panel
		this.setBackground(new Color(105,154,233));
		
		// Set the panel to be focusable
		this.setFocusable(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
