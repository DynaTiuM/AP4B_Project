package View;

import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JButton;

import controller.ActionSelectionMiddlePile;
import model.Tile;

// La classe Pot représente une collection de piles de tuiles dans un jeu
public class Pot {
    // Tableau contenant les piles de tuiles
    private final Pile[] piles;
    // Pile du milieu
    private final MiddlePile middlePile;
  
    // Référence vers la vue principale
    private final View view_m;
 

    // Constructeur prenant en entrée la position du pot sur l'écran et le nombre de piles à créer
    public Pot(Position position, int numberOfPiles, View ref) {
	  
	    // Enregistrement de la référence vers la vue principale
	    view_m = ref;
	  

        // Création du tableau de piles de la bonne longueur
        piles = new Pile[numberOfPiles];
    
        // Calcul de la position centrale de la fenêtre
        int centerX = position.getX();

        // Calcul de la taille des piles
        int pileSize = Bord.RECT_SIZE * 4;

        // Calcul des coordonnées x et y du coin supérieur gauche de la première pile
        int firstPileX = centerX - pileSize;

        int yPosition = pileSize / 2;

        // Création des piles
        for (int i = 0; i < numberOfPiles; i++) {

            if (i % 2 == 0 && i != 0) {
                yPosition += pileSize;
            }

            // Création de la pile et enregistrement dans le tableau
            piles[i] = new Pile(new Position(firstPileX + (i % 2) * pileSize, yPosition), view_m, i);
        }
    
        // Création de la pile du milieu et enregistrement dans l'attribut middlePile
        middlePile = new MiddlePile(this, new Position(firstPileX, yPosition + pileSize));
  }
  
    // Méthode ajoutant une tuile à la vue principale
    public void addT(Tile_View tile) {
	  view_m.getPanel().addT(tile);
  }
  
    public void addB(JButton button) {
	  view_m.getPanel().addB(button);
  }
  
    public ActionSelectionMiddlePile actionSelectionMiddlePile(int ID) {
	  return view_m.actionSelectionMiddlePile(ID);
  }

    // Méthode dessinant les piles
    public void draw(Graphics g) {
        // Dessin des piles
        for (Pile pile : piles) {
            pile.draw(g);
        }
    }
  
  	public void initiateButtons() {
		for(Pile pile : piles) {
			pile.initiateButtons();
		}
	}

    public void updatePile(LinkedList<Tile> to_update, int index) {
        piles[index].updatePile(to_update);
    }

    public void updateMiddlePile(LinkedList<Tile> to_update, int previous_index, boolean delete) {
        middlePile.updatePile(to_update, previous_index, delete);
    }

	public void removeT(Tile_View tile) {
		view_m.getPanel().removeT(tile);
	}
	
	public void removeB(JButton button) {
		view_m.getPanel().removeB(button);
	}
}