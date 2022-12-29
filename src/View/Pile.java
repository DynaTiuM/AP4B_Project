package View;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Tile;
import model.ColorEnum;

public class Pile {
	private static final int RECT_SIZE = Bord.RECT_SIZE;
  private Position position;
  private JButton button;
  
  private Tile_View[] tiles;
 
  
  private View view_m;
  
  private JPanel panel;
  
  int number;
  
  private int passage;

  public Pile(Position position, View view_ref, int number) {
	  this.number = number;
	 
	 view_m = view_ref;
	 
	 
	  
    this.position = position;
    this.button = new JButton();
    this.button.setBounds(position.getX(), position.getY(), 25, 25);
    tiles = new Tile_View[4];
    
   
  }
  
 

  public void draw(Graphics g) {
	  
	 int i =0;
	 
	  ImageIcon icon = new ImageIcon("src\\Images\\Pile.png");
	    if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
	        System.out.println("CANT LOAD IMAGE");
	      } else {
	        // The image was successfully loaded
	        Image pile = icon.getImage();
	        g.drawImage(pile, position.getX(), position.getY(), RECT_SIZE*3, RECT_SIZE*3, null);

	        }
	    
	    
	    //System.out.println("test ");

	    // Cr�e 4 tuiles vertes � l'emplacement de la pile
	    /*Green tile1 = new Green(new Position(position.getX(), position.getY()));
	    Green tile2 = new Green(new Position(position.getX() + (RECT_SIZE * 2), position.getY()));
	    Green tile3 = new Green(new Position(position.getX(), position.getY() + (RECT_SIZE * 2)));
	    Green tile4 = new Green(new Position(position.getX() + (RECT_SIZE * 2), position.getY() + (RECT_SIZE * 2)));*/
	    
	    
	    // Dessine les 4 tuiles vertes
	    /*tile1.draw(g);
	    tile2.draw(g);
	    tile3.draw(g);
	    tile4.draw(g);*/
	  }
  
  public boolean updateTile(LinkedList<Tile> to_iterate) {
	  
	  int x = 0; 
	  int y = 0;
	  int i = 0;
	  
	  
	 
	  if(to_iterate.getFirst()!=null) {
		  
		  
		  for(Tile p: to_iterate) {
			  switch (p.getColorEnum()){
			  case O: tiles[i] = new Orange(new Position(position.getX() + x * (RECT_SIZE * 2), position.getY() + y * (RECT_SIZE * 2))); 

			  	break;
			  case B: tiles[i] = new Purple(new Position(position.getX() + x * (RECT_SIZE * 2), position.getY() + y * (RECT_SIZE * 2)));
			  	break;
			  case Bl: tiles[i] = new Blue(new Position(position.getX() +x * (RECT_SIZE * 2), position.getY() + y * (RECT_SIZE * 2)));
			  	break;
			  case Y: tiles[i] = new Yellow(new Position(position.getX() + x * (RECT_SIZE * 2), position.getY() + y * (RECT_SIZE * 2)));
			  	break;
			  case G: tiles[i] = new Green(new Position(position.getX() + x * (RECT_SIZE * 2), position.getY() + y * (RECT_SIZE * 2)));
				  break;
			  }
			  System.out.print(p.getColorEnum() + " / ");
			 view_m.getPanel().addT(tiles[i]);			  
			  
			  
			  switch (i) {
			  case 0: x++;
			  	break;
			  case 1: x = 0;
			  y++;
			  	break;
			  case 2: x++;
			  	break;
			  }
			  
			  i++;
			  
			  
		}
		  
	  }
		  
	  
	  return to_iterate.getFirst()!=null;
	  
  }

  public void setButton(boolean value) {
    button.setVisible(value);
    System.out.println("button");
  }



public void updatePile(LinkedList<Tile> to_update) {
	
	ViewPanel temp = this.view_m.getPanel();
	
	int i =0;
	if(!updateTile(to_update)) {
		//System.out.println("\n" +number + "test bari");
		for(Tile_View p: tiles) {
			temp.removeT(p);
		}
		//temp.revalidate();
		temp.repaint();
    }
    
    
	
}




  
 
}