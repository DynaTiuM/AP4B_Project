package menu;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import game.Game;
import game.GameFrame;

@SuppressWarnings("serial")
public class MenuFrame implements WindowProperties {

	// Declare frame, scroll, button, label, list, and 
	// numberPlayersList as instance variables
	private JFrame frame;
	private JScrollPane scroll;
	private JButton button;
	private JLabel label;
	private JList<Integer> list;
	private DefaultListModel<Integer> numberPlayersList;
	private int numPlayers;
	private boolean clicked = false;
	
	public MenuFrame() {
		// Initialize frame and set its properties
		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WINDOW_MENU);
		frame.setLocationRelativeTo(null);
		
		// Initialize numberPlayersList and add elements to it
		numberPlayersList = new DefaultListModel<>(); // sample usage
	    for(int i = 2; i < 5; i++)
	    	numberPlayersList.addElement(i);
	    
	    // Initialize list and set its model to numberPlayersList
	    list = new JList<>(numberPlayersList);
	    
	    // Initialize button and label
	    button = new JButton();
	    label = new JLabel("How many do you want to play ?", JLabel.CENTER);
	    
	    // Initialize listScroller and set its view to list
	    JScrollPane listScroller = new JScrollPane(list);
	    
	    // Add listScroller, button, and label to the frame
	    frame.add(listScroller);
	    frame.add(button);
	    frame.add(label);
	    
	    // Set the font for label and list
	    Font font = new Font("Tahoma", Font.BOLD,20);
	    label.setFont(font);
	    list.setFont(font);
	    button.setFont(font);
	    
	    // Set the text and bounds for button
	    button.setText("Validate");
	    button.setBounds(WINDOW_WIDTH/2-115, WINDOW_HEIGHT/2, 275, 100);
	    
	    // Set the bounds for listScroller
	    listScroller.setBounds(WINDOW_WIDTH/2-170, WINDOW_HEIGHT/2, 40, 100);
	    
	    // Add an action listener to the button
	    button.addActionListener(e -> {
	    	
	    	// If the button has been clicked, create a new GameFrame 
	    	// with the selected number of players
	    	// and dispose of the current frame
	    	
	    	if(clicked) {
	    		new GameFrame(numPlayers);
	    		frame.dispose();
	    	}
	    });
	    
	    // Add a mouse listener to the list
	    list.addMouseListener(new MouseAdapter(){
		    @Override 
		    public void mousePressed(MouseEvent e) {
		    	// Set clicked to true and set numPlayers 
		    	// to the selected value in the list
		    	clicked = true;
		    	numPlayers = list.getSelectedValue();
		    }
		});
	    
	    // Set the selection mode and layout orientation for the list
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    list.setLayoutOrientation(JList.VERTICAL);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}	
}
