package main.java.me.spaghetti;

import javax.swing.*;
import java.awt.*;

public class ChessEngine {

    // declaring variables
    static int tileSize = 80;
    static JPanel[] panel = new JPanel[64];

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
    static char[] panelPieceColor = new char[64];
    static char[] panelColor = new char[64];
    static boolean[] panelIsRedHighlighted = new boolean[64];

    static char pieceSelected;
    static int fromPanel, fromPanelX, fromPanelY = -1;
    static int toPanel, toPanelX, toPanelY = -1;

    static boolean whiteCanLongCastle, whiteCanShortCastle = true;
    static boolean blackCanLongCastle, blackCanShortCastle = true;

    // true = white
    static boolean turn = true;

    // sets up the frame
    static MyFrame frame = new MyFrame("logicalChess", (8*tileSize), (8*tileSize));



    // main method
    public static void main(String[] args) {

        for (int i = 0; i < 64; i++) {
            panelIsRedHighlighted[i] = false;
            panel[i] = new JPanel();
            panel[i].setLayout(new BorderLayout());
            panel[i].setFocusable(true);
            panel[i].addMouseListener(frame);
        }

        // sets the starting panel colors
        boolean white = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int k = (i*8)+j;
                if (white) {
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

        Refresh.Board();

    }
}