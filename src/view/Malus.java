package view;

import java.awt.*;
import java.util.Objects;

import javax.swing.*;

import controller.ActionMalus;
import model.Tile;

//Class used to display the Malus of a player on the View Panel
public class Malus {
	
	//Width and Height of a cell in the Malus Grid
	private final int RECT_WIDTH;
	private final int RECT_HEIGHT;
	
	//Position of the Malus Grid on the Panel
	private final Position position;
	
	//Button used to put tiles in Malus Grid
	private final JButton button;
	
	private final Font font;

	//Stores the tiles, to be able to access their references to remove them from the ViewPanel
	private final Tile_View[] tiles;

	//reference to the view
	private final View viewRef;

	
	public Malus(Position position, View viewRef, int RECT_SIZE) {
		this.viewRef = viewRef;

		tiles = new Tile_View[7];
		this.position = position;
		
		// Set the button to select Malus, this will be use in the popUp
		this.button = new JButton();
		this.button.setBounds(275, 38, 200, 50);
		ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("MalusButton.png")));

		button.setIcon(icon);

		RECT_WIDTH = (int)(RECT_SIZE * 1.5);
		RECT_HEIGHT = RECT_SIZE * 2;
		font = new Font("Arial", Font.BOLD, RECT_WIDTH/4);
	}

	public void draw(Graphics g) {
		ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Malus.PNG")));

		// Check if the Image was successfully loaded
		if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
			assert false : "Can't load the image : Malus";
			// There was an error loading the image
		} else {
			// The image was successfully loaded
			Image malus = icon.getImage();
			for(int i = 0; i <= 6; i++) {
				g.drawImage(malus, position.getX() + (RECT_WIDTH * i), position.getY(), RECT_WIDTH, RECT_HEIGHT, null);
			}
		}
		
		// Modify the color of g
		g.setFont(font);
		g.setColor(Color.RED);
		
		// Display the number of the cells 
		g.drawString("-1", position.getX() + (int)(RECT_WIDTH*0.35), position.getY() + RECT_HEIGHT/3);
		g.drawString("-1", position.getX() + (int)(RECT_WIDTH*1.35), position.getY() + RECT_HEIGHT/3);

		g.drawString("-2", position.getX() + (int)(RECT_WIDTH * 2.35), position.getY() + RECT_HEIGHT/3);
		g.drawString("-2", position.getX() + (int)(RECT_WIDTH * 3.35), position.getY() + RECT_HEIGHT/3);
		g.drawString("-2", position.getX() + (int)(RECT_WIDTH * 4.35), position.getY() + RECT_HEIGHT/3);

		g.drawString("-3", position.getX() + (int)(RECT_WIDTH * 5.35), position.getY() + RECT_HEIGHT/3);
		g.drawString("-3", position.getX() + (int)(RECT_WIDTH * 6.35), position.getY() + RECT_HEIGHT/3);

		// Modify back the color of g
		g.setColor(Color.BLACK);
	}

	public void setButton(boolean value) {
		button.setVisible(value);
	}

	//adds the Tiles in the malus Grid of Player
	public void updateViewLine(Tile[] malus) {
		//empties Malus
		clearMalus();
		
		int temp = 0;
		//parse the malus Grid of the Player
		for(Tile p: malus) {
			if(p != null){
				switch (p.getColorEnum()){
					case O: tiles[temp] = new Orange(new Position(position.getX() + 5 + (RECT_WIDTH *temp), position.getY() + (int)(RECT_HEIGHT /2.5)));
						break;
					case M: tiles[temp] = new Purple(new Position(position.getX() + 5 + (RECT_WIDTH *temp), position.getY() + (int)(RECT_HEIGHT /2.5)));
						break;
					case B: tiles[temp] = new Blue(new Position(position.getX() + 5 + (RECT_WIDTH *temp), position.getY() + (int)(RECT_HEIGHT /2.5)));
						break;
					case Y: tiles[temp] = new Yellow(new Position(position.getX() + 5 + (RECT_WIDTH *temp), position.getY() + (int)(RECT_HEIGHT /2.5)));
						break;
					case G: tiles[temp] = new Green(new Position(position.getX() + 5 + (RECT_WIDTH *temp), position.getY() + (int)(RECT_HEIGHT /2.5)));
						break;
					case MALUS : tiles[temp] = new MalusTile(new Position(position.getX() + 5 + (RECT_WIDTH *temp), position.getY() + (int)(RECT_HEIGHT /2.5)));
						break;

				}
				//add current Tile in the View Tile
				if(tiles[temp] != null) viewRef.getPanel().addT(tiles[temp]);

			}
			temp++;
			//function end if temp goes out of bounds
			if(temp >= 7) {
				break;
			}

		}


	}

	public JButton getMalusButton() {
		ActionMalus action = viewRef.actionMalus();
		button.addActionListener(action);

		return button;
	}

	public void clearMalus() {

		// For each tile of Malus
		for(Tile_View tile : tiles) {
			if(tile != null) {
				// Remove the Tile
				viewRef.getPanel().removeT(tile);
			}
		}
		for(Tile_View tile : tiles) {
			tile = null;
		}
		// Repaint to update the view
		viewRef.getPanel().repaint();
	}

	public void addMalusFirst(int previous) {
		tiles[previous] = new MalusTile(new Position(position.getX() + 5 + (RECT_WIDTH *previous), position.getY() + (int)(RECT_HEIGHT /2.5)));
		viewRef.getPanel().addT(tiles[previous]);
	}
}