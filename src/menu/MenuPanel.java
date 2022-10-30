package menu;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class MenuPanel extends JPanel implements ActionListener {
	
	MenuPanel() {
		this.setPreferredSize(new Dimension(800,800));
		this.setBackground(new Color(105,154,233));
		this.setFocusable(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
