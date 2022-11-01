package menu;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import game.Game;

@SuppressWarnings("serial")
public class MenuFrame {

	private JFrame frame;
	private JScrollPane scroll;
	private JButton button;
	private JLabel label;
	private JList<Integer> list;
	private DefaultListModel<Integer> numberPlayersList;
	private int numPlayers;
	private boolean clicked = false;
	
	public MenuFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setSize(new Dimension(700,700));
	 
		numberPlayersList = new DefaultListModel<>(); // sample usage
	    for(int i = 2; i < 5; i++)
	    	numberPlayersList.addElement(i);
	    
	    list = new JList<>(numberPlayersList);
	    button = new JButton();
	    label = new JLabel();
	    
	    list.setFont(new Font("Tahoma", Font.BOLD,20));
	    
	    button.setBounds(10, 10, 60, 30);
	    
	    button.addActionListener(e -> {
	    	if(clicked) {
	    		new Game(numPlayers);
	    		frame.dispose();
	    	}
	    });
	    
	    label.setText("How many do you want to play ?");
	    label.setBounds(10,10,60,60);
	    
	    list.addMouseListener(new MouseAdapter(){
		    @Override 
		    public void mousePressed(MouseEvent e) {
		    	clicked = true;
		    	numPlayers = list.getSelectedValue();
		    }
		});
	    
	    list.setLocation(500, 500);
	    
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    list.setLayoutOrientation(JList.VERTICAL);
	    
	    JScrollPane listScroller = new JScrollPane(list);
	    listScroller.setBounds(100, 100, 40, 100);
	    
	    frame.add(listScroller);
	    frame.add(button);
	    frame.add(label);	   
	}	
}
