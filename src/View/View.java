package View;

import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.Timer;

import controller.ActionLine;
import controller.ActionMalus;
import controller.ActionSelectionMiddlePile;
import controller.ActionSelectionTile;
import controller.Controller;
import menu.MenuFrame;
import model.Line;
import model.Tile;

public class View extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int HEIGHT = 720;
    private static final int WIDTH = 1080;

    private final Bord[] bords;
    private final Pot potRef;
    private final Controller controllerRef;

    private ViewPanel contentPanel;

    /**
     * Create the frame.
     */
    public View(Controller controller, int numberOfPlayers) {	 //View(Controller ref, int numberOfPlayers)
        this.controllerRef = controller;
        //controller_m = ref;
        // Set the background color of the frame to white
        setBackground(new Color(255, 255, 255));
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Empecher l'utilisateur de redimensioner la fenetre
        setResizable(false);
        //Afficher la fenetre au centre de l'ï¿½crant
        setLocationRelativeTo(null);

        setTitle("Azul");
        URL url1 = getClass().getResource("..\\Images\\Azul.png");
        assert url1 != null;
        Image icon = Toolkit.getDefaultToolkit().getImage(url1);

        URL url2 = getClass().getResource("..\\Images\\Table.png");
        assert url2 != null;
        ImageIcon iconTable = new ImageIcon(url2);
        // Set the frame's icon
        setIconImage(icon);
        bords = new Bord[numberOfPlayers];
        bords[0] = new Bord(new Position(30, 20), this, 0);
        bords[1] = new Bord(new Position(WIDTH - Bord.BORD_SIZE - 50, 20), this, 1);

        if (numberOfPlayers > 2) {
            bords[2] = new Bord(new Position(30, HEIGHT -Bord.BORD_SIZE - 50), this, 2);
        }
        if (numberOfPlayers > 3) {
            bords[3] = new Bord(new Position(WIDTH - Bord.BORD_SIZE - 50, HEIGHT -Bord.BORD_SIZE - 50), this, 3);
        }
        // Create the Pot object
        potRef = new Pot(new Position(WIDTH/2, HEIGHT/2), (numberOfPlayers * 2) + 1, this);

        if (iconTable.getImageLoadStatus() == MediaTracker.ERRORED) {
            System.out.println("ERRO LOADING IMAGE BACKGROUND " + MediaTracker.ERRORED);
        } else {
            // The image was successfully loaded
            URL url = getClass().getResource("..\\Images\\Table.png");
            assert url != null;
            Image image = Toolkit.getDefaultToolkit().getImage(url);
            contentPanel = new ViewPanel(controllerRef, image, bords, potRef, this);
            setContentPane(contentPanel);
        }


        this.setVisible(true);
    }

    public void updatePile(LinkedList<Tile> toUpdate, int index) {
        potRef.updatePile(toUpdate, index);
    }

    public void displayEndOfGame(model.Bord[] bords){
        this.getPanel().displayEndOfGame(bords);
    }

    public ViewPanel getPanel() {
        return this.contentPanel;
    }

    public void updateViewLine(LinkedList<Tile> toSend, int previousIndex, int i, int currentPlayer, Tile[] malus) {
        bords[currentPlayer].updateViewLine(toSend, previousIndex, i, malus);
    }

    public void updateMiddlePile(LinkedList<Tile> toSend, int previousIndex, boolean delete) {
        potRef.updateMiddlePile(toSend, previousIndex, delete);
    }
    public void updatePattern(int playerID, HashMap<Tile, Position> toSend) {
        bords[playerID].updatePattern(toSend);
    }

    public void updateMalus(Tile[] malus, int currentPlayer) {
        bords[currentPlayer].updateMalus(malus);
    }

    public void clearMalus(int playerID){
        bords[playerID].clearMalus();
    }
    
    public void updatePopup(Tile[][] pattern, Tile[] malus, Line[] grid, Tile hand) {
    	contentPanel.updateBordPopUp(pattern, malus, grid, hand);
    }
    
    public void closePopup() {
    	contentPanel.closePopUp();
    }
    
    public void initiateButtons() {
    	potRef.initiateButtons();
    }
    public int getScore(int playerID) {
        return controllerRef.getScore(playerID);
    }
    
    public ActionSelectionTile actionSelectionTile(int ID, int numberPile) {
    	return controllerRef.actionSelectionTile(ID, numberPile);
    }
    public ActionSelectionMiddlePile actionSelectionMiddlePile(int ID) {
    	return controllerRef.actionSelectionMiddlePile(ID);
    }
    
    public ActionLine actionLine(int ID) {
    	return controllerRef.actionLine(ID);
    }
    
    public ActionMalus actionMalus() {
    	return controllerRef.actionMalus();
    }

	public void updateViewLine(LinkedList<Tile> toSend, int previousIndex, int i, int currentPlayer) {
		bords[currentPlayer].updateViewLine(toSend, previousIndex, i);
	}

	public void sendMalusFirstToView(int previous, int currentPlayer) {
		bords[currentPlayer].sendMalusFirstToView(previous);
		
	}

    public void stopGame(){
        controllerRef.stopGame();
    }
}


class ViewPanel extends JPanel {
    /**
     *
     */
    private final View view_ref;
    private static final long serialVersionUID = 1L;
    // Image to display
    private final Image image;
    private final Bord[] bords;
    private final Pot pot;
    private PopupPanel panel;
    private final Controller controllerRef;
    private final Font font;

// Constructor that takes in image, bords array, and pot object
    public ViewPanel(Controller controller, Image image, Bord[] bords, Pot pot, View view_ref) {
        this.view_ref = view_ref;
        this.image = image;
        this.bords = bords;
        this.pot = pot;
        this.setLayout(null);
        this.controllerRef= controller;
        font = new Font("Arial", Font.BOLD, 30);    
    }

    public void closePopUp() {
    	panel.closePopUp();
	}

	public void addT(Tile_View tile) {
        this.add(tile);
        this.repaint();
    }
    
    public void addB(JButton button) {
    	this.add(button);
    	this.repaint();
    }

    public void removeT(Tile_View tile) {
    	
        this.remove(tile);
        this.repaint();
    }
    
    public void removeB(JButton button) {
    	this.remove(button);
    	this.repaint();
    }


  //Cette fonction est appelï¿½e pour afficher en grand le Bord du joueur actif avec des boutons
    public void updateBordPopUp(Tile[][] pattern, Tile[] malus, Line[] grid, Tile hand) {
    	// Crï¿½er un panel pour afficher le bord en grand

        panel = new PopupPanel(view_ref, pattern, malus, grid, hand);
        JDialog dialog = new JDialog((JFrame)null, "", true);
        dialog.setUndecorated(true);
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        panel.setLayout(new BorderLayout());

        // Afficher la fenï¿½tre pop-up avec le panel contenant le bord en grand

        //JOptionPane.showMessageDialog(null, panel, "Bord en grand", JOptionPane.PLAIN_MESSAGE);


    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        // Draw image
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        // Draw bords
        for (Bord bord : bords) {
            bord.draw(g);
        }
        pot.draw(g);

        //Modification de la couleur et de la police de g
        g.setFont(font);
        g.setColor(Color.WHITE);
        try{
            g.drawString("Tour du joueur " + (controllerRef.getCurrentPlayer() + 1), 413, 35);
        }catch (Exception e) {
            System.out.println("Game not initialized !");
        }
        //On remet g a le couleur de base;
        g.setColor(Color.BLACK);
    }

    public void displayEndOfGame(model.Bord[] bords) {
        JPanel panel = new PopupEnd(view_ref, bords);
        JDialog dialog = new JDialog((JFrame)null, "", true);
        dialog.setUndecorated(true);
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        panel.setLayout(new BorderLayout());
    }
}

class PopupEnd extends JPanel {

    private final View view;

    public PopupEnd(View view, model.Bord[] bords) {
        this.view = view;

        setLayout(null);
        int WIDTH = 500;
        int HEIGHT = 500;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JButton quit = new JButton();
        quit.setBounds((int)(WIDTH / 1.8), HEIGHT - HEIGHT /5, WIDTH /4, HEIGHT /10);
        quit.setText("Quitter");
        quit.setBorder(BorderFactory.createLineBorder(new Color(64, 129, 166), 3));
        quit.setBackground(new Color(246, 237, 227));


        quit.addActionListener(e -> quit());

        this.add(quit);

        JButton playAgain = new JButton();
        playAgain.setBounds((int)(WIDTH / 5.2), HEIGHT - HEIGHT /5, WIDTH /4, HEIGHT /10);
        playAgain.setText("Rejouer");
        playAgain.setBorder(BorderFactory.createLineBorder(new Color(64, 129, 166), 3));
        playAgain.setBackground(new Color(246, 237, 227));

        HashMap<Integer, Integer> scores = new HashMap<>();

        for(model.Bord b : bords){
            scores.put(b.getID(), b.getScore());
        }
        scores = triAvecValeur(scores);
        int x = WIDTH - 110;

        if(scores.size() == 3) {
            x = WIDTH - 230;
        }
        else if(scores.size() == 2) {
            x = WIDTH - 350;
        }

        int i = 0;
        for(Map.Entry<Integer, Integer> entry : scores.entrySet()) {
            int ID = entry.getKey();
            int score = entry.getValue();

            createLabel(x, 160, "..\\Images\\UVs\\"+ (scores.size() - i) + ".jpg", ID, score);
            i++;
            x -= 120;
        }


        JLabel end = new JLabel();
        end.setText("FIN DU JEU !");
        end.setForeground(new Color(64, 129, 166));

        end.setFont(new Font("Serif", Font.BOLD, 40));

        end.setBounds(124, 40, 400, 60);

        this.add(end);
        end.setVisible(true);


        playAgain.addActionListener(e -> newGame());
        this.add(playAgain);
    }
    public static HashMap<Integer, Integer> triAvecValeur( HashMap<Integer, Integer> map ){
        List<Map.Entry<Integer, Integer>> list = new LinkedList<>( map.entrySet() );
        list.sort(Map.Entry.comparingByValue());

        HashMap<Integer, Integer> map_apres = new LinkedHashMap<>();
        for(Map.Entry<Integer, Integer> entry : list)
            map_apres.put( entry.getKey(), entry.getValue() );

        return map_apres;
    }

    public void createLabel(int posX, int posY, String texture, int ID, int score) {
        JLabel uv = new JLabel();
        uv.setBounds(posX, posY, 80, 80);

        URL urlTexture = getClass().getResource(texture);
        assert urlTexture != null;
        ImageIcon icon = new ImageIcon(urlTexture);

        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);

        JLabel text = new JLabel();
        JLabel text2 = new JLabel();

        text.setText("Joueur " + (ID + 1));
        text2.setText("Score : " + score);


        text.setFont(new Font("Serif", Font.BOLD, 20));
        text2.setFont(new Font("Serif", Font.BOLD, 17));


        text.setBounds(posX, posY + 80, 80, 60);
        text2.setBounds(posX, posY + 110, 80, 60);

        if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
            // There was an error loading the image
            assert false : "Error during loading the texture";
        } else {
            // The image was successfully loaded
            uv.setIcon(icon);

        }
        this.add(uv);
        this.add(text);
        this.add(text2);
        uv.setVisible(true);
        text.setVisible(true);
    }

    private void newGame() {
        view.closePopup();
        view.stopGame();
        SwingUtilities.getWindowAncestor(this).dispose();
        view.dispose();
        new MenuFrame();
    }

    private void quit(){
        view.closePopup();
        view.stopGame();
        SwingUtilities.getWindowAncestor(this).dispose();
        view.dispose();
        System.exit(1);
    }
}


class PopupPanel extends JPanel {
    /**
     *
     */
    private static final int POPUP_BORD_SIZE = 500;
    private static final int POPUP_RECT_SIZE = 42;
    private static final long serialVersionUID = 1L;
    private final Image image;
    private final PlayGrid playGrid;
    private final Pattern pattern;
    private final Malus malus;
    private final JButton[] buttons;

    private final Position patternPosition = new Position(10 + POPUP_BORD_SIZE / 2, POPUP_BORD_SIZE / 3);
    private final Position gridPosition = new Position(POPUP_RECT_SIZE, POPUP_BORD_SIZE / 3);
    private final Position malusPosition = new Position(18, POPUP_BORD_SIZE - POPUP_BORD_SIZE / 6 - 20);

    // Constructor that takes in image, bords array, and pot object
    public PopupPanel(View view_ref, Tile[][] pattern, Tile[] malus, Line[] grid, Tile hand) {
        URL url = getClass().getResource("..\\Images\\Bord.png");
        image = Toolkit.getDefaultToolkit().getImage(url);
        setLayout(null);
        setPreferredSize(new Dimension(500, 500));
        this.playGrid = new PlayGrid(gridPosition, view_ref, POPUP_RECT_SIZE);
        this.pattern = new Pattern(patternPosition, view_ref,  POPUP_RECT_SIZE);
        this.malus = new Malus(malusPosition, view_ref, POPUP_RECT_SIZE);

        //Add the buttons of the Piles and middle pile
        JButton malusButton = this.malus.getMalusButton();
        this.add(malusButton);
      //Add the buttons of the Piles and middle pile
        this.buttons = playGrid.getPileButtons();

        URL url1 = getClass().getResource("..\\Images\\rolloverButtonLines.png");
        assert url1 != null;
        ImageIcon rolloverIcon = new ImageIcon(url1);

        URL url2 = getClass().getResource("..\\Images\\ButtonLines.png");
        assert url2 != null;
        ImageIcon icon = new ImageIcon(url2);

        // Ajout des boutons au JPanel
        

        ImageIcon lineRolloverIcon = new ImageIcon("src\\Images\\rolloverButtonLines.png");
        ImageIcon lineIcon = new ImageIcon("src\\Images\\ButtonLines.png");
        ImageIcon lineIcon2 = new ImageIcon("src\\Images\\ButtonLines2.png");
        ImageIcon malusIcon = new ImageIcon("src\\Images\\MalusButton.png");
        ImageIcon malusIcon2 = new ImageIcon("src\\Images\\MalusButton2.png");
        
        for (JButton button : this.buttons) {
            if (button != null) {
                this.add(button);
                if (lineRolloverIcon.getImageLoadStatus() == MediaTracker.ERRORED) {
                    // Erreur lors du chargement de l'image
                    assert false : "Can't load the image";
                  } else {
                      button.setRolloverIcon(lineRolloverIcon);
                  }

                button.setVisible(true);
                // Chargement de l'image de la tuile

                // V�rifie si l'image a pu �tre charg�e correctement
                if (lineIcon.getImageLoadStatus() == MediaTracker.ERRORED) {
                    assert false : "Can't load the image";
                    // Erreur lors du chargement de l'image
                } else {
                    button.setIcon(lineIcon);
                }
            }
        }

        URL url3 = getClass().getResource("..\\Images\\ButtonLines2.png");
        assert url3 != null;
        ImageIcon icon2 = new ImageIcon(url3);

        // Create the timer to blink the buttons
        // Toggle the visibility of the buttons every time the timer fires
        // Erreur lors du chargement de l'image
        // Timer to blink the buttons
        // Toggle the visibility of the buttons every time the timer fires
        Timer blinkTimer = new Timer(500, e -> {
            for (JButton lineButton : buttons) {
                if (lineButton.getIcon() == lineIcon2) {
                	
                	lineButton.setIcon(lineIcon);
                	malusButton.setIcon(malusIcon);
                    repaint();
                } else {
                    if (lineIcon.getImageLoadStatus() == MediaTracker.ERRORED) {
                        assert false : "Can't load the image";
                        // Erreur lors du chargement de l'image
                    } else {
                    	lineButton.setIcon(lineIcon2);
                    	malusButton.setIcon(malusIcon2);
                        repaint();
                    }
                }
            }
        });

        // Start the timer
        blinkTimer.start();

        updateBord(pattern, malus, grid, hand);
        
    }
    
    // Closing the popup after the player clicked on the button
    public void closePopUp() {
 	   SwingUtilities.getWindowAncestor(this).dispose();
	}

	public void updateBord(Tile[][] pattern, Tile[] malus, Line[] grid, Tile hand) {
    	
    	int offsetX = -POPUP_RECT_SIZE;
    	int offsetY = -POPUP_RECT_SIZE;
    	
    	// Printing tiles of pattern
    	for(int y = 0; y < 5; y++) {
    		for(int x = 0; x < 5; x++) {
    			if(pattern[y][x].getOccupied()){
                    switchEnum(pattern[y][x], offsetX, offsetY, 1, 1, patternPosition);
                }
                offsetX += POPUP_RECT_SIZE;
    		}
            offsetX = -POPUP_RECT_SIZE;
            offsetY += POPUP_RECT_SIZE;
    	}
    	
    	offsetX = - POPUP_RECT_SIZE + 12;
    	offsetY = 35 - POPUP_RECT_SIZE ;
    	
    	// Printing tiles of malus
    	for(Tile t : malus) {
    		if(t != null) {
    			switchEnum(t, offsetX, offsetY, 1, 1, malusPosition);
    			offsetX += 63;
    		}
    	}
    	
    	offsetX = 0;
    	offsetY = 0;
    	
    	// Printing tiles of grid
    	for(Line line : grid) {
        	int i = 1;
    		for(Tile t : line.getTiles()) {
    			if(t != null) {
    				switchEnum(t, offsetX, offsetY, 5 - i, line.getLength() - 1, gridPosition);
    				
    				// The color of the tile selected is different from the color of tiles on the line
    				// Or the line is already full :
    				// We need to disable the button of this line
                    if((!line.isPossible(hand) || line.checkFull())) {
    					buttons[line.getLength() - 1].setVisible(false);
    				}
        		}
    			i++;
    		}
    	}
        for(Line line : grid){
            if(line.isAlreadyOnPattern(hand)) {
                buttons[line.getLength() - 1].setVisible(false);
            }
        }
    }
    
    private void switchEnum(Tile t, int offsetX, int offsetY, int x, int y, Position position) {
    	Tile_View tile = null;    	

    	switch (t.getColorEnum()){
			case O: tile = new Orange(new Position(position.getX() + x * (POPUP_RECT_SIZE + offsetX), position.getY() + y * (POPUP_RECT_SIZE + offsetY)), true);
				break;
			case M: tile = new Purple(new Position(position.getX() + x * (POPUP_RECT_SIZE + offsetX), position.getY() + y * (POPUP_RECT_SIZE + offsetY)), true);
				break;
			case B: tile = new Blue(new Position(position.getX() + x * (POPUP_RECT_SIZE + offsetX), position.getY() + y * (POPUP_RECT_SIZE + offsetY)), true);
				break;
			case Y: tile = new Yellow(new Position(position.getX() + x * (POPUP_RECT_SIZE + offsetX), position.getY() + y * (POPUP_RECT_SIZE + offsetY)), true);
				break;
			case G: tile = new Green(new Position(position.getX() + x * (POPUP_RECT_SIZE + offsetX), position.getY() + y * (POPUP_RECT_SIZE + offsetY)), true);
				break;
			case MALUS: tile = new MalusTile(new Position(position.getX() + x * (POPUP_RECT_SIZE + offsetX), position.getY() + y * (POPUP_RECT_SIZE + offsetY)), true);
		}
		this.add(tile);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw image 
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        malus.draw(g);
        playGrid.draw(g);
        pattern.draw(g);

    }
}