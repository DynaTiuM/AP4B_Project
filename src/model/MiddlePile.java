package model;

import java.awt.Color;
import java.util.LinkedList;


//Classe qui représente une pile de "Tile"
public class MiddlePile {
	
	private int previous_index = 0;

	// Liste de "Tile" qui constitue la pile
	private final LinkedList<Tile> tiles;
	
	// Liste de "Tile" qui est utilisée pour stocker une sélection de "Tile" en fonction de leur couleur
	private final LinkedList<Tile> selection;
	
	// Référence à un objet Pot
	private final Pot pot_ref;
	
	// Indique si on a déjà pris des "Tile" dans la pile
	private boolean first;
	
	
	// Constructeur qui prend une référence à un objet Pot en paramètre
	public MiddlePile(Pot ref) {
		
		tiles = new LinkedList<>();
		selection = new LinkedList<>();
		
		pot_ref = ref;
		
		setFirst();
		
	}
	
	// Ajoute une liste de tuiles à la pile
	public void addContent(LinkedList<Tile> to_add) {
		tiles.addAll(to_add);
		this.pot_ref.sendAddedTilesToView(to_add, previous_index, false);
		previous_index += to_add.size();
		
		
		to_add.clear();
	}
	
	// Renvoie la pile
	public LinkedList<Tile> getTiles(){
		return tiles;
	}
	
	public void sendCompletePileToView(boolean bool) {
		previous_index = 0;
		this.pot_ref.sendAddedTilesToView(tiles, previous_index, bool);
		previous_index += tiles.size();
	}
	
	public LinkedList<Tile> modifyMiddlePile(int index) {
		Tile tile_ref = tiles.get(index);
		LinkedList<Tile> toSend = new LinkedList<>();
		LinkedList<Tile> tmp = new LinkedList<>();
		
		if(first) {
			first = false;
			pot_ref.sendMalusFirst(tiles.getFirst());
			tiles.removeFirst();
			System.out.println("SENT MALUS TILE");
		}
		
		
		for(Tile tile : this.tiles) {
			if(tile.getColor() == tile_ref.getColor()) {
				toSend.add(tile);
				tmp.add(tile);
			}
		}
		
		for(Tile tile : tmp) {
			tiles.remove(tile);
		}
		
		return toSend;
	}
	
	// Renvoie une sélection de tuiles de la pile en fonction de leur couleur
	public LinkedList<Tile> getSelection(Color color) {
		System.out.println("FIRST HIHIUHIUHIUHIUHIUHUHIUHIUHIUHIUHIUHIUHIUHIUHIUHIUHIUHIUHIUHIUHIUHIUHIU " + false);
		
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
	
	
	
	public int isEmpty() {
		if(tiles.isEmpty()) {
			return 1;
		}else {
			return 0;
		}
	}
	
	public boolean isEmptyView() {
		return tiles.isEmpty();
	}
	
	public void setFirst() {
		first = true;
		tiles.addFirst(new Tile(Color.gray));
		this.sendCompletePileToView(false);
	}
	
}
