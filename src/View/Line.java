package View;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.*;

import model.Tile;

// Classe représentant une ligne de Tiles dans l'interface graphique
public class Line {
  // Taille d'une tuile en pixels
	private int RECT_SIZE;
    private static int buttonIdCounter = 0;      
  private Position position;
  private int length;
  private JButton button;
  private Tile_View[] tiles;
  
  // Constructeur de la classe Line
  public Line(Position position, int length, View view_ref, int RECT_SIZE) {
    // Initialisation de la position et de la longueur de la ligne
	  this.RECT_SIZE = RECT_SIZE;
	  this.position = position;
    tiles = new Tile_View[length];
    this.length = length;
    // Création d'un nouveau bouton
    this.button = new JButton();
    button.setBounds(position.getX() - RECT_SIZE, position.getY(), RECT_SIZE, RECT_SIZE);
    button.setActionCommand("buttonPile" + Integer.toString(buttonIdCounter));
    
    JButton button = new JButton();
    ImageIcon icon = new ImageIcon("src\\Images\\ButtonLines.png");
    button.setIcon(icon);
        
    ImageIcon selectedIcon = new ImageIcon("selectedButtonLines.png");
    ImageIcon rolloverIcon = new ImageIcon("rolloverButtonLines.png");
    button.setSelectedIcon(selectedIcon);
    button.setRolloverIcon(rolloverIcon);
    buttonIdCounter++;

  }
  
  // Méthode qui retourne le tableau de tuiles de la ligne
  public Tile_View[] getTiles() {
    return tiles;
  }
  
  // Méthode qui retourne la longueur de la ligne
  public int getLength() {
    return this.length;
  }

  // Méthode qui dessine la ligne sur l'interface graphique
  public void draw(Graphics g) {
    // Chargement de l'image de la tuile
    ImageIcon icon = new ImageIcon("src\\Images\\Cube.png");
    // Vérifie si l'image a pu être chargée correctement
    if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
      // Erreur lors du chargement de l'image
    } else {
      // Image chargée correctement
      Image rect = icon.getImage();
      // Dessine chaque tuile de la ligne sur l'interface graphique
      for (int i = 0; i < length; i++) {
        g.drawImage(rect, position.getX() + (4 - i) * RECT_SIZE, position.getY(), RECT_SIZE, RECT_SIZE, null);
      }
    }
  }

  // Méthode qui modifie la visibilité du bouton associé à la ligne
  public void setButton(boolean value) {
    button.setVisible(value);
  }

  public void updateViewLine(LinkedList<Tile> to_send, int previous_index,  View view_ref) {
	  // Index de la prochaine tuile à ajouter dans le tableau de tuiles de la ligne
	  int temp = previous_index;
	  
	  // Pour chaque tuile à ajouter à la ligne
	  for(Tile p: to_send) {
	    // Création de la tuile de vue en fonction de sa couleur
	    switch (p.getColorEnum()) {
	      case O: tiles[temp] = new Orange(new Position(position.getX() + (4 - temp) * (RECT_SIZE ), position.getY())); 
	        break;
	      case M: tiles[temp] = new Purple(new Position(position.getX() +  (4 - temp) * (RECT_SIZE), position.getY()));
	        break;
	      case B: tiles[temp] = new Blue(new Position(position.getX() + (4 - temp) * (RECT_SIZE), position.getY()));
	        break;
	      case Y: tiles[temp] = new Yellow(new Position(position.getX() + (4 - temp) * (RECT_SIZE), position.getY()));
	        break;
	      case G: tiles[temp] = new Green(new Position(position.getX() + (4 - temp) * (RECT_SIZE), position.getY()));
	        break;
	    }
	    
	    // Affichage de la couleur de la tuile dans la console
	    System.out.print(p.getColorEnum() + " / ");
	    
	    // Ajout de la tuile de vue au panel de la vue principale
	    view_ref.getPanel().addT(tiles[temp]);
	 
	    // Incrémentation de l'index de la prochaine tuile à ajouter
	    temp++;
	  }
	
	}
  
  public JButton getButton() {
  	return button;
  }
	
}
  
 