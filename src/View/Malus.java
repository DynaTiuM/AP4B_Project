package View;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.*;

import controller.ActionMalus;
import model.Tile;

public class Malus {
	private int RECT_SIZE;
  private Position position;
  private JButton button;
  
  private Tile_View[] tiles;
  
  private View view_m;

  public Malus(Position position, View view_ref, int RECT_SIZE) {
	
	view_m = view_ref;  
	
	tiles = new Tile_View[6];
    this.position = position;
    this.button = new JButton();
    button.setBounds((int)(position.getX() + RECT_SIZE * 9.5), position.getY() + RECT_SIZE/2, RECT_SIZE, RECT_SIZE);
    
    this.RECT_SIZE = RECT_SIZE;
  }

  public void draw(Graphics g) {
    // Afficher les cases numérotées -1
    g.drawString("-1", position.getX(), position.getY());
    g.drawString("-1", position.getX() + RECT_SIZE, position.getY());
    // Afficher les cases numérotées -2
    g.drawString("-2", position.getX() + RECT_SIZE * 2, position.getY());
    g.drawString("-2", position.getX() + RECT_SIZE * 3, position.getY());
    g.drawString("-2", position.getX() + RECT_SIZE * 4, position.getY());
    // Afficher les cases numérotées -3
    g.drawString("-3", position.getX() + RECT_SIZE * 5, position.getY());
    g.drawString("-3", position.getX() + RECT_SIZE * 6, position.getY());

    ImageIcon icon = new ImageIcon("src\\Images\\Malus.png");
    if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
      // There was an error loading the image
    } else {
      // The image was successfully loaded
      Image malus = icon.getImage();
      for(int i = 0; i < 6; i++) {
        g.drawImage(malus, position.getX() + (int)(RECT_SIZE *1.5*i), position.getY(), (int)(RECT_SIZE*1.5), (int) RECT_SIZE*2, null);
      }
      }
    }

  public void setButton(boolean value) {
    button.setVisible(value);
  }

public void updateViewLine(LinkedList<Tile> linkedList, int previous_index_2) {
	int temp = previous_index_2;
	System.out.println("malus_view");
	for(Tile p: linkedList) {
		switch (p.getColorEnum()){
		  case O: tiles[temp] = new Orange(new Position(position.getX() + (int)(RECT_SIZE *2*temp), position.getY())); 
		  	break;
		  case M: tiles[temp] = new Purple(new Position(position.getX() + (int)(RECT_SIZE *2*temp), position.getY()));
		  	break;
		  case B: tiles[temp] = new Blue(new Position(position.getX() + (int)(RECT_SIZE *2*temp), position.getY()));
		  	break;
		  case Y: tiles[temp] = new Yellow(new Position(position.getX() + (int)(RECT_SIZE *2*temp), position.getY()));
		  	break;
		  case G: tiles[temp] = new Green(new Position(position.getX() + (int)(RECT_SIZE *2*temp), position.getY()));
			  break;
		  }
		  System.out.print(p.getColorEnum() + " / ");
		 view_m.getPanel().addT(tiles[temp]);
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
			if(tile != null) {
				view_m.getPanel().removeT(tile);
			}
		}
	}
	
	public void updateMalusView(LinkedList<Tile> to_send, int previous_index) {
		int i = 0;
		while(tiles[i] != null) i++;
		for(Tile p: to_send) {
			if(i < tiles.length) {
				switch (p.getColorEnum()){
			  case O: tiles[i] = new Orange(new Position(position.getX() + (int)(RECT_SIZE *2*i), position.getY())); 
			  	break;
			  case M: tiles[i] = new Purple(new Position(position.getX() + (int)(RECT_SIZE *2*i), position.getY()));
			  	break;
			  case B: tiles[i] = new Blue(new Position(position.getX() + (int)(RECT_SIZE *2*i), position.getY()));
			  	break;
			  case Y: tiles[i] = new Yellow(new Position(position.getX() + (int)(RECT_SIZE *2*i), position.getY()));
			  	break;
			  case G: tiles[i] = new Green(new Position(position.getX() + (int)(RECT_SIZE *2*i), position.getY()));
				  break;
			  }
			 view_m.getPanel().addT(tiles[i]);
			 i++;
			}
			else {
				return;
			}
			
		}
	}


}