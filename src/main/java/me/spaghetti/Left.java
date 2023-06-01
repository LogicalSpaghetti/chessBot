package main.java.me.spaghetti;

import static main.java.me.spaghetti.ChessEngine.*;

public class Left {
    
    public static void PanelClicked(int panelNumber) {

        System.out.println("left triggered");
        System.out.println("highlightedPanel = " + highlightedPanel);

        //if there's already a highlighted space, it tries moving, else it highlights the selected space
        if(highlightedPanel > -1) {
            System.out.println("move triggered");
            Move.MovePiece(highlightedPanel, panelNumber);
        } else {
            System.out.println("highlight triggered");
            Highlight.HighlightClicked(panelNumber);
        }

    }

}
