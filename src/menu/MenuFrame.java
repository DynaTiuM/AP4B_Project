package menu;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import game.Game;
import game.GameFrame;

@SuppressWarnings("serial")
public class MenuFrame implements WindowProperties {

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
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(WINDOW_MENU);
	 
		numberPlayersList = new DefaultListModel<>(); // sample usage
	    for(int i = 2; i < 5; i++)
	    	numberPlayersList.addElement(i);
	    
	    list = new JList<>(numberPlayersList);
	    button = new JButton();
	    label = new JLabel("How many do you want to play ?", JLabel.CENTER);
	    JScrollPane listScroller = new JScrollPane(list);
	    
	    frame.add(listScroller);
	    frame.add(button);
	    frame.add(label);
	    Font font = new Font("Tahoma", Font.BOLD,20);
	    label.setFont(font);
	    list.setFont(font);
	    button.setFont(font);
	    
	    button.setText("Validate");
	    button.setBounds(WINDOW_WIDTH/2-115, WINDOW_HEIGHT/2, 275, 100);
	    listScroller.setBounds(WINDOW_WIDTH/2-170, WINDOW_HEIGHT/2, 40, 100);
	    
	    button.addActionListener(e -> {
	    	if(clicked) {
	    		new GameFrame(numPlayers);
	    		frame.dispose();
	    	}
	    });
	    
	    list.addMouseListener(new MouseAdapter(){
		    @Override 
		    public void mousePressed(MouseEvent e) {
		    	clicked = true;
		    	numPlayers = list.getSelectedValue();
		    }
		});
	    
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    list.setLayoutOrientation(JList.VERTICAL);
	}	
}
