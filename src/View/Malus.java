package View;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.*;

import controller.ActionMalus;
import model.Tile;

public class Malus {
	private int RECT_WIDTH;
	private int RECT_HEIGHT;
  private Position position;
  private JButton button;
  private Font font;
  
  private Tile_View[] tiles;
  
  private View view_m;

  public Malus(Position position, View view_ref, int RECT_SIZE) {
	
	view_m = view_ref;  
	
	tiles = new Tile_View[6];
    this.position = position;
    this.button = new JButton();
    
    RECT_WIDTH = (int)(RECT_SIZE*1.5);
    RECT_HEIGHT = (int)(RECT_SIZE*2);
    font = new Font("Arial", Font.BOLD, RECT_WIDTH/4);
  }

  public void draw(Graphics g) {


    ImageIcon icon = new ImageIcon("src\\Images\\Malus.png");
    if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
      // There was an error loading the image
    } else {
      // The image was successfully loaded
      Image malus = icon.getImage();
      for(int i = 0; i <= 6; i++) {
        g.drawImage(malus, position.getX() + (int)(RECT_WIDTH *i), position.getY(), RECT_WIDTH, RECT_HEIGHT, null);
      }
      }
    //Modification de la couleur et de la police de g
    g.setFont(font);
    g.setColor(Color.RED);
    // Afficher les cases numérotées -1
    g.drawString("-1", position.getX() + (int)(RECT_WIDTH*0.35), position.getY() + RECT_HEIGHT/3);
    g.drawString("-1", position.getX() + (int)(RECT_WIDTH*1.35), position.getY() + RECT_HEIGHT/3);
    // Afficher les cases numérotées -2
    g.drawString("-2", position.getX() + (int)(RECT_WIDTH * 2.35), position.getY() + RECT_HEIGHT/3);
    g.drawString("-2", position.getX() + (int)(RECT_WIDTH * 3.35), position.getY() + RECT_HEIGHT/3);
    g.drawString("-2", position.getX() + (int)(RECT_WIDTH * 4.35), position.getY() + RECT_HEIGHT/3);
    // Afficher les cases numérotées -3
    g.drawString("-3", position.getX() + (int)(RECT_WIDTH * 5.35), position.getY() + RECT_HEIGHT/3);
    g.drawString("-3", position.getX() + (int)(RECT_WIDTH * 6.35), position.getY() + RECT_HEIGHT/3);
    
    //On remet g à le couleur de base;
    g.setColor(Color.BLACK);
    }

  public void setButton(boolean value) {
    button.setVisible(value);
  }

public void updateViewLine(LinkedList<Tile> linkedList, int previous_index_2) {
	int temp = previous_index_2;
	System.out.println("malus_view");
	for(Tile p: linkedList) {
		switch (p.getColorEnum()){
		  case O: tiles[temp] = new Orange(new Position(position.getX() + 5 + (int)(RECT_WIDTH *temp), position.getY() + (int)(RECT_HEIGHT /2.5))); 
		  	break;
		  case M: tiles[temp] = new Purple(new Position(position.getX() + 5 +(int)(RECT_WIDTH *temp), position.getY() + (int)(RECT_HEIGHT /2.5)));
		  	break;
		  case B: tiles[temp] = new Blue(new Position(position.getX() + 5 +(int)(RECT_WIDTH *temp), position.getY() + (int)(RECT_HEIGHT /2.5)));
		  	break;
		  case Y: tiles[temp] = new Yellow(new Position(position.getX() + 5 +(int)(RECT_WIDTH *temp), position.getY() + (int)(RECT_HEIGHT /2.5)));
		  	break;
		  case G: tiles[temp] = new Green(new Position(position.getX() + 5 + (int)(RECT_WIDTH *temp), position.getY() + (int)(RECT_HEIGHT /2.5)));
			  break;
		  }
		  System.out.print(p.getColorEnum() + " / ");
		  if(tiles[temp] != null) view_m.getPanel().addT(tiles[temp]);
		  temp++;
	}
	System.out.println("\nend of malus_view");
	
}
	
	public JButton getMalusButton() {
		ActionMalus action = view_m.actionMalus();
		button.addActionListener(action);
		
		return button;
	}

	public void updateMalusView() {
		for(Tile_View tile : tiles) {
			System.out.println("FOUND TILE : " + tile);
			if(tile != null) view_m.getPanel().removeT(tile);
		}
	}
}