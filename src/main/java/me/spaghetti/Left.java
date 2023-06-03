package main.java.me.spaghetti;

import static main.java.me.spaghetti.ChessEngine.*;

public class Left {
    
    public static void PanelClicked(int panelNumber, int x, int y) {

        //if there's already a highlighted space, it tries moving, else it highlights the selected space
        if (fromPanel > -1) {
            toPanel = panelNumber;
            toPanelX = x;
            toPanelY = y;
            Move.MovePiece(panelNumber, x, y);
        } else {
            Highlight.HighlightClicked(panelNumber, x, y);
        }

    }

}
