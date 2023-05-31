package main.java.me.spaghetti;

import java.awt.*;

import static main.java.me.spaghetti.ChessEngine.*;

public class Highlight {
    public static void HighlightClicked(int panelNumber) {

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
            } else if (panelPieceColor[panelNumber] == 'B' && !turn) {
                canMove = true;
            }
        }

        //highlights or un-highlights the current panel if it can move
        if(!panelIsHighlighted[panelNumber] && canMove) {

            //changes the color to select the tile
            if (panelColor[panelNumber] == 'W') {
                panel[panelNumber].setBackground(new Color(0xbea61f));
            } else {
                panel[panelNumber].setBackground(new Color(0xa89515));
            }
            pieceSelected = piecePositions[panelNumber];
            pieceToMove = panelNumber;
            System.out.println(pieceSelected);
            System.out.println(panelNumber);
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

    public static void RedHighlight(int panelNumber) {

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

}