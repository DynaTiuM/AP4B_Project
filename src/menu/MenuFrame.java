package menu;

import java.awt.Dimension;

import javax.swing.*;

@SuppressWarnings("serial")
public class MenuFrame extends JFrame {

	JScrollPane scroll;
	
	public MenuFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setSize(new Dimension(700,700));
	 
		DefaultListModel<Integer> number_players = new DefaultListModel<>(); // sample usage
	    for(int i = 2; i < 5; i++)
	    	number_players.addElement(i);
	    
	    JList<Integer> list = new JList<>(number_players);
	    
	    list.setLocation(500, 500);
	    
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    list.setLayoutOrientation(JList.VERTICAL);
	    
	    JScrollPane listScroller = new JScrollPane(list);
	    listScroller.setBounds(40, 50, 30, 60);
	    
	    this.add(listScroller);
	          
	}
}
