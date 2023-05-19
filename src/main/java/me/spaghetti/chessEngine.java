package main.java.me.spaghetti;

import java.awt.*;

import javax.swing.*;

public class chessEngine {

    JPanel[] panel = new JPanel[64];
    //char[] piecePositions = new char[64];

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

    private void refreshBoard() {
        for(int l = 0; l < 64; l++) {
            String fileName;
            if(Character.isUpperCase(piecePositions[l])) {
                fileName = "src/main/resources/"+((piecePositions[l])+".png");
            } else {
                fileName = "src/main/resources/"+((piecePositions[l])+"2.png");
            }
            System.out.println(l);
            System.out.println(piecePositions[l]);
            System.out.println(fileName);

            ImageIcon image = new ImageIcon(fileName);
            Image bsImage = image.getImage(); // transform it
            Image newImg = bsImage.getScaledInstance(56, 56, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            ImageIcon image2 = new ImageIcon(newImg); // transform it back

            JLabel label = new JLabel(image2);
            //label.setText("Test text");
            panel[l].removeAll();
            panel[l].add(label);
        }
    }

    chessEngine() {

        MyFrame frame = new MyFrame("logicalChess",640,640);

        for(int h = 0; h < 64; h++) {
            panel[h] = new JPanel();
        }

        boolean white = true;

        frame.setLayout(new GridLayout(8, 8));
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                int k = (i*8)+j;
                if(white) {
                    panel[k].setBackground(new Color(0x7c4c3e));
                } else {
                    panel[k].setBackground(new Color(0x512a2a));
                }
                frame.add(panel[k]);
                white= !white;
            }
            white= !white;
        }

        refreshBoard();
        piecePositions[63] = 'R';
        refreshBoard();

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new chessEngine();
    }
}