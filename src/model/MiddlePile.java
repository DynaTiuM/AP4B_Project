package model;


import java.util.LinkedList;


//Classe qui représente une pile de "Tile"
public class MiddlePile {
	
	private int previousIndex = 0;

	// Liste de "Tile" qui constitue la pile
	private final LinkedList<Tile> tiles;

	// Référence à un objet Pot
	private final Pot potRef;
	
	// Indique si on a déjà pris des "Tile" dans la pile
	private boolean first;
	
	
	// Constructeur qui prend une référence à un objet Pot en paramètre
	public MiddlePile(Pot ref) {
		
		tiles = new LinkedList<>();

		potRef = ref;
		
		setFirst();
		
	}
	
	// Ajoute une liste de tuiles à la pile
	public void addContent(LinkedList<Tile> toAdd) {
		tiles.addAll(toAdd);
		this.potRef.sendAddedTilesToView(toAdd, previousIndex, false);
		previousIndex += toAdd.size();

		toAdd.clear();
	}
	
	// Renvoie la pile
	public LinkedList<Tile> getTiles(){
		return tiles;
	}
	
	public void sendCompletePileToView(boolean bool) {
		previousIndex = 0;
		this.potRef.sendAddedTilesToView(tiles, previousIndex, bool);
		previousIndex += tiles.size();
	}
	
	//Function called when we choose a tile from MiddlePile
	
	public LinkedList<Tile> modifyMiddlePile(int index) {
		Tile tileRef = tiles.get(index);
		LinkedList<Tile> toSend = new LinkedList<>();
		LinkedList<Tile> tmp = new LinkedList<>();
		
		//If it is the first time this round a tile is selected here, the malus tile will immediatly be sent to the current player's malus line
		
		if(first) {
			first = false;
			potRef.sendMalusFirst(tiles.getFirst());
			tiles.removeFirst();
		}
		
		
		
		for(Tile tile : this.tiles) {
			if(tile.getColorEnum() == tileRef.getColorEnum()) {
				toSend.add(tile);
				tmp.add(tile);
			}
		}
		
		for(Tile tile : tmp) {
			tiles.remove(tile);
		}
		
		return toSend;
	}

	//checks if MiddleLine is empty, used to check if play is possible
	
	public int isEmpty() {
		
		//if the MiddlePile is empty, or contains just one tile but this tile is the malus tile, middle pile will be seen as empty
		
		if(tiles.isEmpty()||(tiles.size() == 1 && tiles.getFirst().getColorEnum() == ColorEnum.MALUS)) {
			return 1;
		}else {
			return 0;
		}
	}
	
	//puts the malus tile back in MiddlePile

	public void setFirst() {
		first = true;
		tiles.addFirst(new Tile(ColorEnum.MALUS));
		this.sendCompletePileToView(false);
	}
	
	//displays the MiddleLine in the console
	
	public void display() {
		System.out.print("Middle : ");
		for(Tile p: tiles) System.out.print(p.getColorEnum() + " ");
		System.out.println();
	}
	
}
