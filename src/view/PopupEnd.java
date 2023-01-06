package view;


import controller.ActionEnd;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

//Class used to display the scores of players as well as the winner at the end of the game
public class PopupEnd extends JPanel {

    public PopupEnd(View view, model.Bord[] bords) {
    	
    	//basic configurations
        setLayout(null);
        int WIDTH = 500;
        int HEIGHT = 500;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JButton quit = new JButton();
        quit.setBounds((int)(WIDTH / 1.8), HEIGHT - HEIGHT /5, WIDTH /4, HEIGHT /10);
        quit.setText("Quitter");
        quit.setBorder(BorderFactory.createLineBorder(new Color(64, 129, 166), 3));
        quit.setBackground(new Color(246, 237, 227));

        ActionEnd actionQuit = view.actionEnd(1, this);

        quit.addActionListener(actionQuit);

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

            createLabel(x, 160, (scores.size() - i) + ".jpg", ID, score);
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

        ActionEnd actionNewGame = view.actionEnd(0, this);

        playAgain.addActionListener(actionNewGame);
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


        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(texture)));

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
}
