package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;
import model.Line;
import model.Tile;

public class ViewPanel extends JPanel {
    /**
     *
     */
    private final View viewRef;
    private static final long serialVersionUID = 1L;
    // Image to display
    private final Image image;
    private final Bord[] bords;
    private final Pot pot;
    private PopupPanel panel;
    private final Controller controllerRef;
    private final Font font;

    // Constructor that takes in image, bords array, and pot object
    public ViewPanel(Controller controller, Image image, Bord[] bords, Pot pot, View viewRef) {
        this.viewRef = viewRef;
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


  //Creates a PopUp, displaying the board of the current player in a bigger size
    public void updateBordPopUp(Tile[][] pattern, Tile[] malus, Line[] grid, Tile hand) {
    	

        panel = new PopupPanel(viewRef, pattern, malus, grid, hand);
        JDialog dialog = new JDialog((JFrame)null, "", true);
        dialog.setUndecorated(true);
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        panel.setLayout(new BorderLayout());

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

        //changes g's font and COlor
        g.setFont(font);
        g.setColor(Color.WHITE);
        try{
            g.drawString("Tour du joueur " + (controllerRef.getCurrentPlayer() + 1), 413, 35);
        }catch (Exception e) {
            System.out.println("Game not initialized !");
        }
        //Sets g back to black
        g.setColor(Color.BLACK);
    }

    //display PopPup at the end of the game, showing who won and what score each player got
    public void displayEndOfGame(model.Bord[] bords) {
        JPanel panel = new PopupEnd(viewRef, bords);
        JDialog dialog = new JDialog((JFrame)null, "", true);
        dialog.setUndecorated(true);
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        panel.setLayout(new BorderLayout());
    }
}
