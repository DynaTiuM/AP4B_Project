package model;

import java.util.Arrays;
import java.util.LinkedList;


// Classe représentant une "Line"
public class Line {

    // Tableau de "Tile" représentant la "Line"
	private Tile[] lineArray;
	
    // Première case encore libre de la "Line"
	private int currentIndex;
	private int previousIndex;
	
    // Taille de la "Line"
	private int size;

    // Tableau de booléens indiquant si chaque couleur est présente ou non dans la ligne
	private final boolean[] colorPresence;

    // Couleur courante de la ligne	
	private ColorEnum currentColor;
	
    // Instance de la classe Malus
	private final Malus malusRef;
	
    // Instance de la classe Pattern
	private final Pattern patternRef;
		
	// 
	private final LinkedList<Tile> toSend;
	
	private final Bord bordRef;
	private final int length;
	
	
	// Constructeur de la classe Line
	public Line(Malus malus_p, Pattern pattern_ref, int size, Bord ref) {
		
		bordRef = ref;
		
		malusRef = malus_p;
		
		patternRef = pattern_ref;
		
		currentIndex = 0;
		previousIndex = 0;
		
		colorPresence = new boolean[5];
		
		currentColor = null;
		this.length = size;
		
		toSend = new LinkedList<>();
		
		for(int i = 0; i<5;i++) {
			colorPresence[i]=false;
		}
		
		// initialise la "Line"
		initArray(size);
		
	}
	
	public int getLength() {
		return this.length;
	}
	
	// initialise la "Line"
	private void initArray(int index) {
		lineArray = new Tile[index];
		size = index;
	}

	// permet d'ajouter une liste de "Tile" à la "Line"
	public void addChoice(LinkedList<Tile> tiles) {
		
		int previousMalus = malusRef.getPrevious();
		boolean modified = false;
		
		int i = currentIndex;
		for(Tile p: tiles) {
			if(i < size) {
				setTileIndex(i, p);
				i++;
			}else { // les "Tile" en trop sont mise dans le malus 
			
				
				malusRef.addTile(p);
				modified = true;
				
			}
		}
		
		previousIndex = currentIndex;

		currentIndex = i;

		malusRef.setPrevious(previousMalus);
		
		updateViewLine(modified);
	}
	
	private void updateViewLine(boolean modified) {
		LinkedList<Tile> toSend = new LinkedList<>(Arrays.asList(lineArray).subList(previousIndex, currentIndex));

		this.bordRef.updateViewLine(toSend, previousIndex, this.size - 1, modified);
	}
	
	// check si la ligne est pleine
	public boolean checkFull() {
		return currentIndex == size;
	}

	// check s'il est possible de placer un ou plusieurs "Tile" de la couleur de celle en parametre dans le "Line"
	public boolean isPossible(Tile tile) {
		return !checkColor(tile) && (tile.getColorEnum() == currentColor || currentColor == null);
	}

	public boolean isAlreadyOnPattern(Tile tile){
		return patternRef.isAlreadyOnPattern(size, tile);
	}
	
	// check si la couleur a déjà été mise dans cette "Line"
	public boolean checkColor(Tile tile) {
		return colorPresence[tile.getColorEnum().ordinal()];
	}

	// permet d'ajouter une "Tile" à l'index donné
	public void setTileIndex(int index, Tile tile){
		if(index < size) {
			currentColor = tile.getColorEnum();
			lineArray[index] = tile;
		} else {
			malusRef.addTile(tile);
		}
	}

	
	// renvoie le tableau de "Tile" de la "Line"
	public Tile[] getTiles() {
		return this.lineArray;
	}
	
	public Tile[] getLine() {
		return this.lineArray;
	}
	
	
	// affiche la "Line"
	public void display() {
		int i =0;
		while(i<this.currentIndex) {
			System.out.print(lineArray[i].getColorEnum() + " ");
			i++;
		}
		
		while(i<size) {
			System.out.print("- ");
			i++;
		}
		
		System.out.println();
	}
	
	
	// permet de clear la "Line" pour être prêt à acceuillir d'autres "Tile"
	public LinkedList<Tile> clear() {
		
		toSend.clear();
		
		patternRef.determineSendingPlace(size - 1, lineArray[0]);
		
		lineArray[0] = null;
		
		for(int i =1; i<size; i++) {
			toSend.add(lineArray[i]);
			lineArray[i] = null;
		}
		
		currentColor = null;
		currentIndex = 0;
		
		return toSend;
	}

}
