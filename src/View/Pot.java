package View;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;

import controller.ActionSelectionMiddlePile;
import model.Tile;

// La classe Pot repr�sente une collection de piles de tuiles dans un jeu
public class Pot {
  // Attribut repr�sentant la position du pot sur l'�cran
  private Position position;
  // Tableau contenant les piles de tuiles
  private Pile[] piles;
  // Pile du milieu
  private MiddlePile middlePile;
  
  // R�f�rence vers la vue principale
  private View view_m;
 

  // Constructeur prenant en entr�e la position du pot sur l'�cran et le nombre de piles � cr�er
  public Pot(Position position, int numberOfPiles, View ref) {
	  
	// Enregistrement de la r�f�rence vers la vue principale
	 view_m = ref;
	  
    // Initialisation de l'attribut position
    this.position = position;
    // Cr�ation du tableau de piles de la bonne longueur
    piles = new Pile[numberOfPiles];
    
    // Calcul de la position centrale de la fen�tre
    int centerX = position.getX();

    // Calcul de la taille des piles
    int pileSize = Bord.RECT_SIZE * 4;

    // Calcul des coordonn�es x et y du coin sup�rieur gauche de la premi�re pile
    int firstPileX = centerX - pileSize;
    
    int yPosition = pileSize / 2;
    
    // Cr�ation des piles
    for (int i = 0; i < numberOfPiles; i++) {
    	
        if (i % 2 == 0 && i != 0) {
            yPosition += pileSize;
        }
    	
    	// Cr�ation de la pile et enregistrement dans le tableau
    	piles[i] = new Pile(new Position(firstPileX + (i % 2) * pileSize, yPosition), view_m, i);
    }
    
    // Cr�ation de la pile du milieu et enregistrement dans l'attribut middlePile
    middlePile = new MiddlePile(this, new Position(firstPileX, yPosition + pileSize));

  }
  
  // M�thode ajoutant une tuile � la vue principale
  public void addT(Tile_View tile) {
	  view_m.getPanel().addT(tile);
  }
  
  public void addB(JButton button) {
	  view_m.getPanel().addB(button);
  }
  
  public ActionSelectionMiddlePile actionSelectionMiddlePile(int ID) {
	  return view_m.actionSelectionMiddlePile(ID);
  }
  
  public void repaint() {
	  view_m.repaint();
  }

  // M�thode dessinant les piles
  public void draw(Graphics g) {
    // Dessin des piles
    for (Pile pile : piles) {
      pile.draw(g);
    }
    // Dessin de la pile du milieu
    middlePile.draw(g);
  }
  
  	public void initiateButtons() {
		for(Pile pile : piles) {
			pile.initiateButtons();
		}
	}

    public void setButtons(boolean value) {
        // Set the buttons for the piles
        int i = 0;
        for (Pile pile : piles) {
            pile.setButton(value);
            i++;
        }

        //la derni�re valeur de value c donc pour cheque que middle Pile est okay
        // Set the button for the middle pile
        //middlePile.setButton(value[i]);
    }

    public void setTile(LinkedList<Tile> tiles, int number) {
        //piles[number].redraw(tiles);
    }

    public void updatePile(LinkedList<Tile> to_update, int index) {
        piles[index].updatePile(to_update);
    }

    public void updateMiddlePile(LinkedList<Tile> to_update, int previous_index, boolean delete) {
        middlePile.updatePile(to_update, previous_index, delete);
    }
    
    public void updateMiddlePile(LinkedList<Tile> to_update) {
    	middlePile.updatePile(to_update);
    }

    public LinkedList<JButton> getTileButtons() {
        LinkedList<JButton> buttons = new LinkedList<JButton>();
        for (int i = 0; i < piles.length; i++) {
        	JButton[] pile_buttons = piles[i].getButtons();
        	for(JButton button : pile_buttons)
        		buttons.add(button);
        }
        return buttons;
    }

	public void removeT(Tile_View tile) {
		view_m.getPanel().removeT(tile);
	}
	
	public void removeB(JButton button) {
		view_m.getPanel().removeB(button);
	}
}