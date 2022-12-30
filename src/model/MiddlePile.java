package model;

import java.awt.Color;
import java.util.LinkedList;


//Classe qui représente une pile de "Tile"
public class MiddlePile {
	
	private int previous_index = 0;

	// Liste de "Tile" qui constitue la pile
	private LinkedList<Tile> tiles;
	
	// Liste de "Tile" qui est utilisée pour stocker une sélection de "Tile" en fonction de leur couleur
	private LinkedList<Tile> selection;
	
	// Référence à un objet Pot
	private Pot pot_ref;
	
	// Indique si on a déjà pris des "Tile" dans la pile
	private boolean first;
	
	
	// Constructeur qui prend une référence à un objet Pot en paramètre
	public MiddlePile(Pot ref) {
		
		tiles = new LinkedList<Tile>();
		selection = new LinkedList<Tile>();
		
		pot_ref = ref;
		
		first = false;
		
	}
	
	
	// Ajoute une liste de tuiles à la pile
	public void addContent(LinkedList<Tile> to_add) {
		tiles.addAll(to_add);
		this.pot_ref.sendAddedTilesToView(to_add, previous_index);
		previous_index += to_add.size();
		to_add.clear();
	}
	
	// Renvoie la pile
	public LinkedList<Tile> getContent(){
		return tiles;
	}
	
	
	// Renvoie une sélection de tuiles de la pile en fonction de leur couleur
	public LinkedList<Tile> getSelection(Color color) {
		selection.clear();
		for(Tile p: tiles) if(p.getColor() == color) selection.add(p);
		
		return selection;
	}
	
	
	// Renvoie la valeur de first
	public boolean isFirst() {
		return this.first;
	}
	
	
	// Affiche le contenu de la pile dans la console
	public void display() {
		System.out.print("Middle : ");
		for(Tile p: tiles) System.out.print(p.getColorEnum() + " ");
		System.out.println();
	}
	
}