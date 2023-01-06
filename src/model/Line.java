package model;

import java.util.LinkedList;


// Classe représentant une "Line"
public class Line {

    // Tableau de "Tile" représentant la "Line"
	private Tile[] linearray;
	
    // Première case encore libre de la "Line"
	private int current_index;
	private int previous_index;
	
    // Taille de la "Line"
	private int size;

    // Tableau de booléens indiquant si chaque couleur est présente ou non dans la ligne
	private final boolean[] color_presence;

    // Couleur courante de la ligne	
	private ColorEnum current_color;
	
    // Instance de la classe Malus
	private final Malus malus_m;
	
    // Instance de la classe Pattern
	private final Pattern pattern_m;
		
	// 
	private final LinkedList<Tile> to_send;
	
	private final Bord bord_m;
	private final int length;
	
	
	// Constructeur de la classe Line
	public Line(Malus malus_p, Pattern pattern_ref, int size, Bord ref) {
		
		bord_m = ref;
		
		malus_m = malus_p;
		
		pattern_m = pattern_ref;
		
		current_index = 0;
		previous_index = 0;
		
		color_presence = new boolean[5];
		
		current_color = null;
		this.length = size;
		
		to_send = new LinkedList<>();
		
		for(int i = 0; i<5;i++) {
			color_presence[i]=false;
		}
		
		// initialise la "Line"
		initArray(size);
		
	}
	
	public int getLength() {
		return this.length;
	}
	
	// initialise la "Line"
	private void initArray(int index) {
		linearray = new Tile[index];
		size = index;
	}

	// permet d'ajouter une liste de "Tile" à la "Line"
	public void addChoice(LinkedList<Tile> tiles) {
		
		int previous_malus = malus_m.getPrevious();
		boolean modified = false;
		
		int i = current_index;
		for(Tile p: tiles) {
			if(i < size) {
				setTileIndex(i, p);
				i++;
			}else { // les "Tile" en trop sont mise dans le malus 
			
				
				malus_m.addTile(p);
				modified = true;
				
			}
		}
		
		previous_index = current_index;

		current_index = i;

		malus_m.setPrevious(previous_malus);
		
		updateViewLine(modified);
	}
	
	private void updateViewLine(boolean modified) {

		LinkedList<Tile> to_send = new LinkedList<>();

		for(int i = previous_index; i<current_index; i++) {
			to_send.add(linearray[i]);
		}

		this.bord_m.updateViewLine(to_send, previous_index, this.size - 1, modified);
	}
	
	// check si la ligne est pleine
	public boolean checkFull() {
		return current_index == size;
	}

	// check s'il est possible de placer un ou plusieurs "Tile" de la couleur de celle en parametre dans le "Line"
	public boolean isPossible(Tile tile) {
		return !checkColor(tile) && (tile.getColorEnum()==current_color || current_color == null);
	}

	public boolean isAlreadyOnPattern(Tile tile){
		return pattern_m.isAlreadyOnPattern(size, tile);
	}
	
	// check si la couleur a déjà été mise dans cette "Line"
	public boolean checkColor(Tile tile_p) {
		return color_presence[tile_p.getColorEnum().ordinal()];
	}

	// permet d'ajouter une "Tile" à l'index donné
	public void setTileIndex(int index, Tile tile_p){
		
		if(index<size) {
			current_color = tile_p.getColorEnum();
			linearray[index] = tile_p;

	
		} else {
			malus_m.addTile(tile_p);
		}
	}

	
	// renvoie le tableau de "Tile" de la "Line"
	public Tile[] getTiles() {
		return this.linearray;
	}
	
	public Tile[] getLine() {
		return this.linearray;
	}
	
	
	// affiche la "Line"
	public void display() {
		int i =0;
		while(i<this.current_index) {
			System.out.print(linearray[i].getColorEnum() + " ");
			i++;
		}
		
		while(i<size) {
			System.out.print("- ");
			i++;
		}
		
		System.out.println();
		//System.out.println("--------------------------------------------");
		
	}
	
	
	// permet de clear la "Line" pour être prêt à acceuillir d'autres "Tile"
	public LinkedList<Tile> clear() {
		
		to_send.clear();
		
		pattern_m.determineSendingPlace(size - 1, linearray[0]);
		
		linearray[0] = null;
		
		for(int i =1; i<size; i++) {
			to_send.add(linearray[i]);
			linearray[i] = null;
		}
		
		current_color = null;
		current_index = 0;
		
		return to_send;
	}

}
