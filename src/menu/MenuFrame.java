package menu;

import controller.Controller;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Objects;

import javax.swing.*;

public class MenuFrame implements WindowProperties {

	// Declare frame, scroll, button, label, list, and
	// numberPlayersList as instance variables
	private final JFrame frame;
	private final JList<Integer> list;
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
		DefaultListModel<Integer> numberPlayersList = new DefaultListModel<>(); // sample usage
		for(int i = 2; i < 5; i++)
			numberPlayersList.addElement(i);

		// Initialize list and set its model to numberPlayersList
		list = new JList<>(numberPlayersList);

		JLabel imageLabel = new JLabel();

		ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Azul.png")));

		Image image = icon.getImage();
		Image newimg = image.getScaledInstance(300, 200,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		imageLabel.setIcon(icon);

		imageLabel.setBounds(250, 10, 300, 200);

		imageLabel.setVisible(true);

		frame.add(imageLabel);


		// Initialize button and label
		JButton button = new JButton();
		JLabel label = new JLabel("A combien souhaitez-vous jouer ?", JLabel.CENTER);
		label.setForeground(Color.white);

		frame.getContentPane().setBackground(new Color(35, 87, 43));

		// Initialize listScroller and set its view to list
		JScrollPane listScroller = new JScrollPane(list);
		ImageIcon playIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("PlayButton.png")));
		ImageIcon playIcon2 = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("PlayButton2.png")));
		button.setIcon(playIcon);

		// Add listScroller, button, and label to the frame
		frame.add(listScroller);
		frame.add(button);
		frame.add(label);

		// Set the font for label and list
		Font font = new Font("Serif", Font.PLAIN,23);
		label.setFont(font);
		list.setFont(font);
		button.setFont(font);

		// Set the text and bounds for button
		button.setBounds(WINDOW_WIDTH/2-80, (int)(WINDOW_HEIGHT/1.87), 200, 88);


		// Set the bounds for listScroller
		listScroller.setBounds(WINDOW_WIDTH/2-130, (int)(WINDOW_HEIGHT/1.9), 40, 100);

		// Add an action listener to the button
		button.addActionListener(e -> {

			// If the button has been clicked, create a new GameFrame
			// with the selected number of players
			// and dispose of the current frame

			if(clicked) {
				new Controller(numPlayers);
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

		Timer blinkTimer = new Timer(500, e -> {
			if (button.getIcon() == playIcon2) {

				button.setIcon(playIcon);

			} else {
				button.setIcon(playIcon2);
				//repaint();
			}


		});

		// Start the timer
		blinkTimer.start();

	}
}