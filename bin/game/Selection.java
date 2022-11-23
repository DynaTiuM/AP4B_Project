package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.text.html.HTMLDocument.Iterator;

public class Selection extends JPanel implements ActionListener{

	public JButton top_left;
	public JButton top_right;
	
	public JButton bottom_left;
	public JButton bottom_right;
	
	public JButton close_button;
	
	
	public JPanel top_leftj;
	public JPanel top_rightj;
	public JPanel bottom_leftj;
	public JPanel bottom_rightj;
	
	LinkedList<Tile> tiles_m;
	LinkedList<JPanel> panel_list;
	
	Border blackline;
	
	Pile pile_m;
	Selection(Pile pile_p){
		
	blackline = BorderFactory.createLineBorder(Color.black);
		
		pile_m = pile_p;
		tiles_m = pile_p.getTiles();
		
		System.out.println("bya");
		
		initialize();
		
	}
	
	
	private void initialize() {
		this.setLayout(null);
		this.setBounds(0,0,250,175);
		this.setBorder(blackline);
		//this.setBackground(Color.orange);
		
		
		addButton(top_left, 0,0);
		addButton(top_right, 30,0);
		addButton(bottom_left,0,30);
		addButton(bottom_right,30,30);
		
		close_button = new JButton();
		close_button.setBounds(230, 0, 20,20);
		close_button.setText("X");
		close_button.addActionListener(this);
		this.add(close_button);
		
		//addButton(close_button,230,0);
		
		/*top_left = new JButton();
		top_right = new JButton();
		
		bottom_left = new JButton();
		bottom_right = new JButton();
		
		top_left.setBounds(0,0,20,20);
		top_right.setBounds(30,0,20,20);
		bottom_left.setBounds(0, 30, 20,20);
		bottom_right.setBounds(30,30,20,20);
		
		top_left.addActionListener(this);
		top_right.addActionListener(this);
		bottom_left.addActionListener(this);
		bottom_right.addActionListener(this);
		
		this.add(top_right);
		this.add(top_left);
		this.add(bottom_left);
		this.add(bottom_right);
		
		*/
		
		
		/*top_leftj = new JPanel();
		top_rightj = new JPanel();
		
		bottom_leftj = new JPanel();
		bottom_rightj = new JPanel();*/
		
		/*panel_list = new LinkedList<JPanel>();
		
		panel_list.add(top_leftj);
		panel_list.add(top_rightj);
		panel_list.add(bottom_leftj);
		panel_list.add(bottom_rightj);*/
		
	}
	
	public void setArray(LinkedList<Tile> tiles_p) {
		tiles_m = tiles_p;
	}
	
	
	public void draw(Graphics g) {
		ListIterator<Tile> iter =(ListIterator<Tile>) tiles_m.iterator();
		//int i = 1;
		/*for(JPanel p: panel_list) {
			p.setBackground(iter.next().color_m);
			p.setBounds((i%2)*20, (i%2) * 20, 20,20);
			p.setBorder(getBorder());
			p.setOpaque(true);
			p.setVisible(true);
			this.add(p);
			System.out.println("draw selec");
			i++;
		}*/
		
	
		addPanel(top_rightj, 1, 1, iter.next());
		addPanel(top_leftj, 1, 2, iter.next());
		addPanel(bottom_rightj, 2, 1, iter.next());
		addPanel(bottom_leftj, 2, 2, iter.next());
		
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton test;
		test =(JButton) e.getSource();
		
		if(test.getText() == "X") {
			pile_m.closeSelcetion();
		}else {
			System.out.println("Selection Made");
			pile_m.selectionEvent();
		}
		
		
		
		
	}
	
	private void addButton(JButton button, int x, int y) {
		button = new JButton();
		button.setBounds(x,y,20,20);
		button.addActionListener(this);
		this.add(button);
	}
	
	private void addPanel(JPanel panel, int x, int y, Tile tile) {
		panel = new JPanel();
		panel.setBackground(tile.color_m);
		panel.setBounds(x*20, y* 20, 20,20);
		panel.setBorder(blackline);
		panel.setOpaque(true);
		panel.setVisible(true);
		this.add(panel);
	}
	
}
