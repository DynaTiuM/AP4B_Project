package view;

import java.awt.*;
import java.util.Objects;

import javax.swing.*;

import controller.ActionMalus;
import model.Tile;

public class Malus {
	private final int RECT_WIDTH;
	private final int RECT_HEIGHT;
	private final Position position;
	private final JButton button;
	private final Font font;

	private final Tile_View[] tiles;

	private final View viewRef;

	public Malus(Position position, View viewRef, int RECT_SIZE) {
		this.viewRef = viewRef;

		tiles = new Tile_View[7];
		this.position = position;
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
		//Modification de la couleur et de la police de g
		g.setFont(font);
		g.setColor(Color.RED);
		// Afficher les cases num�rot�es -1
		g.drawString("-1", position.getX() + (int)(RECT_WIDTH*0.35), position.getY() + RECT_HEIGHT/3);
		g.drawString("-1", position.getX() + (int)(RECT_WIDTH*1.35), position.getY() + RECT_HEIGHT/3);
		// Afficher les cases num�rot�es -2
		g.drawString("-2", position.getX() + (int)(RECT_WIDTH * 2.35), position.getY() + RECT_HEIGHT/3);
		g.drawString("-2", position.getX() + (int)(RECT_WIDTH * 3.35), position.getY() + RECT_HEIGHT/3);
		g.drawString("-2", position.getX() + (int)(RECT_WIDTH * 4.35), position.getY() + RECT_HEIGHT/3);
		// Afficher les cases num�rot�es -3
		g.drawString("-3", position.getX() + (int)(RECT_WIDTH * 5.35), position.getY() + RECT_HEIGHT/3);
		g.drawString("-3", position.getX() + (int)(RECT_WIDTH * 6.35), position.getY() + RECT_HEIGHT/3);

		//On remet g � le couleur de base;
		g.setColor(Color.BLACK);
	}

	public void setButton(boolean value) {
		button.setVisible(value);
	}

	public void updateViewLine(Tile[] malus) {
		clearMalus();
		int temp = 0;

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

				if(tiles[temp] != null) viewRef.getPanel().addT(tiles[temp]);

			}
			temp++;
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

		for(Tile_View tile : tiles) {
			if(tile != null) {

				viewRef.getPanel().removeT(tile);
			}
		}
		for(Tile_View tile : tiles) {
			tile = null;
		}
		viewRef.getPanel().repaint();
	}

	public void addMalusFirst(int previous) {
		tiles[previous] = new MalusTile(new Position(position.getX() + 5 + (RECT_WIDTH *previous), position.getY() + (int)(RECT_HEIGHT /2.5)));
		viewRef.getPanel().addT(tiles[previous]);
	}
}