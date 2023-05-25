package main.java.me.spaghetti;

import javax.swing.*;
import java.awt.*;

public class chessEngine {

    static int tileSize = 80;

    JPanel[] panel = new JPanel[64];
    char[] panelColor = new char[64];
    boolean[] panelIsRedHighlighted = new boolean[64];
    MyFrame frame = new MyFrame("logicalChess", (8*tileSize), (8*tileSize));


    char[] piecePositions = {
            'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r',
            'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p',
            '0', '0', '0', '0', '0', '0', '0', '0',
            '0', '0', '0', '0', '0', '0', '0', '0',
            '0', '0', '0', '0', '0', '0', '0', '0',
            '0', '0', '0', '0', '0', '0', '0', '0',
            'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P',
            'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'
    };

    private void redHighlight(int panelNumber) {

        if(!panelIsRedHighlighted[panelNumber]) {
            if (panelColor[panelNumber] == 'W') {
                panel[panelNumber].setBackground(new Color(0x9d392e));
            } else {
                panel[panelNumber].setBackground(new Color(0x7d1f1f));
            }
            panelIsRedHighlighted[panelNumber] = true;
        } else {
            if(panelColor[panelNumber] == 'W') {
                panel[panelNumber].setBackground(new Color(0x7c4c3e));
            } else {
                panel[panelNumber].setBackground(new Color(0x512a2a));
            }
            panelIsRedHighlighted[panelNumber] = false;
        }


    }


    private void refreshBoard() {
        for(int l = 0; l < 64; l++) {
            String fileName;
            if(Character.isUpperCase(piecePositions[l])) {
                fileName = "src/main/resources/"+((piecePositions[l])+".png");
            } else {
                fileName = "src/main/resources/"+((piecePositions[l])+"2.png");
            }

            ImageIcon image = new ImageIcon(fileName);
            Image startImage = image.getImage(); // transform it
            Image newImg = startImage.getScaledInstance( (tileSize/10)*7, (tileSize/10)*7, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            image.setImage(newImg); // transform it back

            panel[l].setPreferredSize(new Dimension(tileSize, tileSize));

            panel[l].removeAll();
            JLabel label = new JLabel(image);
            panel[l].add(label, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        }
    }

    chessEngine() {

        for(int i = 0; i < 64; i++) {
            panelIsRedHighlighted[i] = false;
            panel[i] = new JPanel();
            panel[i].setLayout(new BorderLayout());
        }




        boolean white = true;

        frame.setLayout(new GridLayout(8, 8));
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                int k = (i*8)+j;
                if(white) {
                    panel[k].setBackground(new Color(0x7c4c3e));
                    panelColor[k] = 'W';
                } else {
                    panel[k].setBackground(new Color(0x512a2a));
                    panelColor[k] = 'B';
                }
                frame.add(panel[k]);
                white= !white;
            }
            white= !white;
        }

        refreshBoard();

        for(int i = 0; i < 64; i++) {
            redHighlight(i);
            redHighlight(i);
        }

    }

    public static void main(String[] args) {
        new chessEngine();
    }
}