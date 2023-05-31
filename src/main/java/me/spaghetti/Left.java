package main.java.me.spaghetti;

import static main.java.me.spaghetti.ChessEngine.*;

public class Left {
    
    public static void PanelClicked(int panelX, int panelY) {

        int panelNumber = (panelY*8)+panelX;

        //finds which space is highlighted, if any
        int highlightedSpace = -1;
        for(int i = 0; i < 64; i++) {
            if(panelIsHighlighted[i]) {
                highlightedSpace = i;
            }
        }

        //if there's already a highlighted space, it tries moving, else it highlights the selected space
        if(highlightedSpace > -1) {
            Move.MovePiece(highlightedSpace, panelNumber);
        } else {
            Highlight.HighlightClicked(panelNumber);
        }

    }

}
