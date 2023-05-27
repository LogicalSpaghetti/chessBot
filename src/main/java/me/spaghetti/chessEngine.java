package main.java.me.spaghetti;

import javax.swing.*;
import java.awt.*;

public class chessEngine {

    static int tileSize = 80;

    static JPanel[] panel = new JPanel[64];
    static char[] panelColor = new char[64];
    static boolean[] panelIsRedHighlighted = new boolean[64];
    static boolean[] panelIsHighlighted = new boolean[64];
    static char[] panelPieceColor = new char[64];
    static MyFrame frame = new MyFrame("logicalChess", (8*tileSize), (8*tileSize));

    //true = white
    static boolean turn = true;

    static int pieceToMove = -1;


    static char[] piecePositions = {
            'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r',
            'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p',
            '0', '0', '0', '0', '0', '0', '0', '0',
            '0', '0', '0', '0', '0', '0', '0', '0',
            '0', '0', '0', '0', '0', '0', '0', '0',
            '0', '0', '0', '0', '0', '0', '0', '0',
            'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P',
            'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'
    };

    public static void movePiece(int panelNumber, int fromPos) {
        //just move them in piecePositions[] and refreshBoard[]
        //also change the values in panelPieceColor

        if(panelNumber == fromPos) {
            highlightClicked(panelNumber);
            return;
        }

        //checks if the new square isn't the same color
        if((panelPieceColor[panelNumber] == 'W' && turn) || (panelPieceColor[panelNumber] == 'B' && !turn)) {
            highlightClicked(panelNumber);
            return;
        }

        piecePositions[panelNumber] = piecePositions[fromPos];
        piecePositions[fromPos] = '0';
        panelPieceColor[panelNumber] = panelPieceColor[fromPos];
        panelPieceColor[fromPos] = ' ';

        pieceToMove = -1;
        turn = !turn;
        highlightClicked(fromPos);

        refreshBoard();
    }

    public static void panelClicked(int panelX, int panelY) {

        int panelNumber = (panelY*8)+panelX;
        //don't bother with valid move detection in here besides color checks to make sure you're not just highlighting a different piece

        //finds which space is highlighted, if any
        int highlightedSpace = -1;
        for(int i = 0; i < 64; i++) {
            if(panelIsHighlighted[i]) {
                highlightedSpace = i;
            }
        }

        //if there's already a highlighted space, it tries moving, else it highlights the selected space
        if(highlightedSpace > 0) {
            movePiece(panelNumber, highlightedSpace);
        } else {
            highlightClicked(panelNumber);
        }

        refreshBoard();
    }

    public static void highlightClicked(int panelNumber) {

        panelIsRedHighlighted[panelNumber] = false;

        //sets every other panel to default color
        for(int i = 0; i < 64; i++) {
            //sets every other yellow panel to normal
            if(panelNumber != i && panelIsHighlighted[i]) {
                if(panelColor[i] == 'W') {
                    panel[i].setBackground(new Color(0x7c4c3e));
                } else {
                    panel[i].setBackground(new Color(0x512a2a));
                }
                panelIsHighlighted[i] = false;
            }
            //sets every red panel to normal
            if(panelNumber != i && panelIsRedHighlighted[i]) {
                if(panelColor[i] == 'W') {
                    panel[i].setBackground(new Color(0x7c4c3e));
                } else {
                    panel[i].setBackground(new Color(0x512a2a));
                }
                panelIsHighlighted[i] = false;
            }
        }

        //checks if it's a piece that can move
        boolean canMove = false;
        if (panelPieceColor[panelNumber] != ' ') {
            if (panelPieceColor[panelNumber] == 'W' && turn) {
                canMove = true;
            } else if (!turn) {
                canMove = true;
            }
        }
        //highlights or un-highlights the current panel if it can move
        if(!panelIsHighlighted[panelNumber] && canMove) {
            if (panelColor[panelNumber] == 'W') {
                panel[panelNumber].setBackground(new Color(0xbea61f));

            } else {
                panel[panelNumber].setBackground(new Color(0xa89515));
            }
            pieceToMove = panelNumber;
            panelIsHighlighted[panelNumber] = true;
        } else {
            if(panelColor[panelNumber] == 'W') {
                panel[panelNumber].setBackground(new Color(0x7c4c3e));
            } else {
                panel[panelNumber].setBackground(new Color(0x512a2a));
            }
            pieceToMove = -1;
            panelIsHighlighted[panelNumber] = false;
        }

    }

    public static void redHighlight(int panelNumber) {

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


    private static void refreshBoard() {
        for(int l = 0; l < 64; l++) {
            String fileName;
            if(Character.isUpperCase(piecePositions[l])) {
                fileName = "src/main/resources/"+((piecePositions[l])+".png");
                panelPieceColor[l] = 'W';
            } else if (Character.isLowerCase(piecePositions[l])) {
                fileName = "src/main/resources/"+((piecePositions[l])+"2.png");
                panelPieceColor[l] = 'B';
            } else {
                fileName = "";
                panelPieceColor[l] = ' ';
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


        public static void main(String[] args) {
            for(int i = 0; i < 64; i++) {
                panelIsRedHighlighted[i] = false;
                panel[i] = new JPanel();
                panel[i].setLayout(new BorderLayout());
            }

            //creates the board of panels
            frame.setLayout(new GridLayout(8, 8));
            //sets the starting panel colors
            boolean white = true;
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

            refreshBoard(); //sets everything up to start

    }
}