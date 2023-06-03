package main.java.me.spaghetti;

import java.util.stream.IntStream;

import static java.lang.Math.abs;
import static main.java.me.spaghetti.ChessEngine.*;

public class Move {

    public static void King() {

        //castling
        if (turn) { //handles case for white
            if (whiteCanShortCastle && toPanel == 62) {
                if (piecePositions[61] == '0' && piecePositions[62] == '0') {
                    CommitToMove(fromPanel, toPanel);
                    CommitToMove(63, 61); //moves the rook
                    turn = !turn;
                    return;
                }
            } else if (whiteCanLongCastle && toPanel == 58) {
                if (piecePositions[57] == '0' && piecePositions[58] == '0' && piecePositions[59] == '0') {
                    CommitToMove(fromPanel, toPanel);
                    CommitToMove(56, 59); //moves the rook
                    turn = !turn;
                    return;
                }
            }
        } else { //handles case for black
            if (blackCanShortCastle && toPanel == 6) {
                if (piecePositions[5] == '0' && piecePositions[6] == '0') {
                    CommitToMove(fromPanel, toPanel);
                    CommitToMove(7, 5); //moves the rook
                    turn = !turn;
                    return;
                }
            } else if (blackCanLongCastle && toPanel == 2) {
                if (piecePositions[1] == '0' && piecePositions[2] == '0' && piecePositions[3] == '0') {
                    CommitToMove(fromPanel, toPanel);
                    CommitToMove(0, 3); //moves the rook
                    turn = !turn;
                    return;
                }
            }

        }

        //returns if the clicked position is more than one space away
        if (abs(toPanelX-fromPanelX) > 1) {
            return;
        } else if (abs(toPanelY-fromPanelY) > 1) {
            return;
        }

        //these work for now, but once all the other movement is done, run a check to ensure the square isn't defended
        if (toPanel > (fromPanel - 10) && toPanel < (fromPanel - 6)) { //handles all cases of moving up
            CommitToMove(fromPanel, toPanel);
        }

        if (toPanel > (fromPanel - 2) && toPanel < (fromPanel + 2)) { //handles all cases of moving sideways
            CommitToMove(fromPanel, toPanel);
        }

        if (toPanel > (fromPanel + 6) && toPanel < (fromPanel + 10)) { //handles all cases of moving down
            CommitToMove(fromPanel, toPanel);
        }

    }

    public static void Queen() {
        System.out.println("Queen");

        if (fromPanelX == toPanelX) { // same column
            System.out.println("Same column");
            int direction;
            if (toPanelY > fromPanelY) { //below
                System.out.println("below");
                direction = 8;
            } else { //above
                System.out.println("above");
                direction = -8;
            }

            // runs along checking for obstruction
            for (int i = 1; i < abs(fromPanelY-toPanelY) + 1; i++) {
                int tileToCheck = fromPanel + (direction * i);
                //is tile obstructed by ally?
                if (panelPieceColor[tileToCheck] != ' ') {
                    // if tile is an ally
                    if (panelPieceColor[fromPanel] == panelPieceColor[tileToCheck]) {
                        System.out.println("ally blocking");
                        return;
                    }

                    //if tile isn't the destination, and has an enemy
                    if (tileToCheck != toPanel && panelPieceColor[tileToCheck] != panelPieceColor[fromPanel]) {
                        System.out.println("enemy blocking");
                        return;
                    }
                }

            }
            System.out.println("Commit to move");
            CommitToMove(fromPanel, toPanel);

        } else if (fromPanelY == toPanelY) { //same row ---------------------------------------------
            System.out.println("Same row");
            int direction;
            if (toPanelX > fromPanelX) { //below
                System.out.println("right");
                direction = 1;
            } else { //above
                System.out.println("left");
                direction = -1;
            }

            // runs along checking for obstruction
            for (int i = 1; i < abs(fromPanelX-toPanelX) + 1; i++) {
                int tileToCheck = fromPanel + (direction * i);
                //is tile obstructed by ally?
                if (panelPieceColor[tileToCheck] != ' ') {
                    // if tile is an ally
                    if (panelPieceColor[fromPanel] == panelPieceColor[tileToCheck]) {
                        System.out.println("ally blocking");
                        return;
                    }

                    //if tile isn't the destination, and has an enemy
                    if (tileToCheck != toPanel && panelPieceColor[tileToCheck] != panelPieceColor[fromPanel]) {
                        System.out.println("enemy blocking");
                        return;
                    }
                }

            }

            System.out.println("Commit to move");
            CommitToMove(fromPanel, toPanel);
        } else if (toPanelX-toPanelY == fromPanelX-fromPanelY) { // connecting line is negative

            System.out.println("connecting line is y=-x+b");
            int direction;
            if (toPanelX > fromPanelX) { //below
                System.out.println("down + right");
                direction = 9;
            } else { //above
                System.out.println("up + left");
                direction = -9;
            }

            for (int i = 1; i < abs(fromPanelX-toPanelX) + 1; i++) {
                int tileToCheck = fromPanel+(direction*i);
                //is tile obstructed by ally?
                if (panelPieceColor[tileToCheck] != ' ') {
                    // if tile is an ally
                    if (panelPieceColor[fromPanel] == panelPieceColor[tileToCheck]) {
                        System.out.println("ally blocking");
                        return;
                    }

                    //if tile isn't the destination, and has an enemy
                    if (tileToCheck != toPanel && panelPieceColor[tileToCheck] != panelPieceColor[fromPanel]) {
                        System.out.println("enemy blocking");
                        return;
                    }
                }
            }

            System.out.println("Commit to move");
            CommitToMove(fromPanel, toPanel);

        } else if (7 - toPanelX - toPanelY ==  7 - fromPanelX - fromPanelY) { // connecting line is positive

            System.out.println("connecting line is y=x+b");
            int direction;
            if (toPanelX > fromPanelX) { //below
                System.out.println("up + right");
                direction = -7;
            } else { //above
                System.out.println("down + left");
                direction = 7;
            }

            for (int i = 1; i < abs(fromPanelX-toPanelX) + 1; i++) {
                int tileToCheck = fromPanel+(direction*i);
                //is tile obstructed by ally?
                if (panelPieceColor[tileToCheck] != ' ') {
                    // if tile is an ally
                    if (panelPieceColor[fromPanel] == panelPieceColor[tileToCheck]) {
                        System.out.println("ally blocking");
                        return;
                    }

                    //if tile isn't the destination, and has an enemy
                    if (tileToCheck != toPanel && panelPieceColor[tileToCheck] != panelPieceColor[fromPanel]) {
                        System.out.println("enemy blocking");
                        return;
                    }
                }
            }

            System.out.println("Commit to move");
            CommitToMove(fromPanel, toPanel);
        }

    }

    public static void Rook() {
        //4 checks for move validity which stop at the edge of the board, on an enemy, or the square before an ally

        //find direction of movement
        int direction; // -1 = left, 1 = right, -8 = up, 8 = down
        int distance;
        if (toPanel%8 == fromPanel %8) { // true for up or down only
            if (toPanel > fromPanel) { // true for down only
                direction = 8;
            } else {
                direction = -8;
            }
            distance = abs((toPanel- fromPanel)/8);
        } else if ((fromPanel - (fromPanel %8)) == (toPanel - (toPanel%8))) { // true for left or right only
            if (toPanel < fromPanel) { // true for left only
                direction = -1;
            } else {
                direction = 1;
            }
            distance = abs(toPanel- fromPanel);
        } else {
            return;
        }

        for(int i = 1; i < (distance+1); i++) {
            int tileToCheck = fromPanel + (direction*i);
            //is tile obstructed by ally?
            if (panelPieceColor[tileToCheck] != ' ') {
                // if tile is an ally
                if (panelPieceColor[fromPanel] == panelPieceColor[tileToCheck]) {
                    return;
                }

                //if tile isn't the destination, and has an enemy
                if (tileToCheck != toPanel && panelPieceColor[tileToCheck] != panelPieceColor[fromPanel]) {
                    return;
                }
            }
        }

        CommitToMove(fromPanel, toPanel);

        // run a check in the right direction

    }

    public static void Bishop() {
        //4 checks for move validity which stop at the edge of the board, on an enemy, or the square before an ally

    }

    public static void Knight() {


        int dif = (fromPanel%8) - (toPanel%8);
        dif = abs(dif);
        if (dif > 2) {
            return;
        }

        int absDif = abs(toPanel - fromPanel);
        if (absDif == 17 || absDif == 15 || absDif == 10 || absDif == 6) {
            CommitToMove(fromPanel, toPanel);
        }

    }

    public static void Pawn() {

        int sign;
        if (pieceSelected == 'P') {
            sign = -1;
        } else {
            sign = 1;
        }

        if (toPanel == fromPanel +(sign*16)) { //double move
            if ((fromPanel > 7 && fromPanel < 16) || (fromPanel > 47 && fromPanel < 56)) {
                if (piecePositions[toPanel] == '0' && piecePositions[fromPanel + (sign * 8)] == '0') {
                    piecePositions[fromPanel + (sign * 8)] = '&'; //en passant
                    CommitToMove(fromPanel, toPanel);
                }
            }
        } else if (toPanel == fromPanel +(sign*8)) { //single move
            if (piecePositions[toPanel] == '0') {
                CommitToMove(fromPanel, toPanel);
            }
        } else if (toPanel == fromPanel +(sign*9) || toPanel == fromPanel +(sign*7)) { //attack left
            if (sign == -1) {
                if (Character.isLowerCase(piecePositions[toPanel])) {
                    CommitToMove(fromPanel, toPanel);
                } else if (piecePositions[toPanel] == '&') { //en passant
                    CommitToMove(fromPanel, toPanel);
                    piecePositions[toPanel + 8] = '0';
                    panelPieceColor[toPanel + 8] = '0';
                }
            } else {
                if (Character.isUpperCase(piecePositions[toPanel])) {
                    CommitToMove(fromPanel, toPanel);
                } else if (piecePositions[toPanel] == '&') { //en passant
                    CommitToMove(fromPanel, toPanel);
                    piecePositions[toPanel - 8] = '0';
                    panelPieceColor[toPanel - 8] = '0';

                }
            }
        }

        //promotion
        if (toPanel > 55 || toPanel < 8) {
            Promote();
        }
    }

    public static void Promote() {

        //currently only promotes to a Queen
        //the turn was swapped by CommitToMove, so this code looks backwards, but it isn't
        if (turn) {
            piecePositions[toPanel] = 'q';
        } else {
            piecePositions[toPanel] = 'Q';
        }
    }

    public static void MovePiece(int toPanel, int x, int y) {

        //just toggles the highlight if it's the same input
        if (toPanel == fromPanel) {
            Highlight.HighlightClicked(toPanel, x, y);
            return;
        }
        //checks if the new square isn't an ally
        if ((panelPieceColor[toPanel] == 'W' && turn) || (panelPieceColor[toPanel] == 'B' && !turn)) {
            // todo if the king of the same color is already selected and the new selection is a rook,
            //  then try castling. If that fails then select the rook
            if (Character.toUpperCase(piecePositions[pieceSelected]) == 'K' && Character.toUpperCase(piecePositions[toPanel]) == 'R') {
                if (turn && piecePositions[toPanel] == 'R') { // white
                    if (toPanel == 56  && whiteCanLongCastle) { // long castling

                    } else if (toPanel == 63 && whiteCanShortCastle) { // short castling

                    }
                } else if (!turn && piecePositions[toPanel] == 'r') { // black
                    if (toPanel == 0  && blackCanLongCastle) { // long castling

                    } else if (toPanel == 7 && blackCanShortCastle) { // short castling

                    }
                }
            } else {
                Highlight.HighlightClicked(toPanel, x, y);
                return;
            }
        }

        char pieceTypeSelected = Character.toUpperCase(pieceSelected);
        if (pieceTypeSelected == 'K') {
            King();
        } else if (pieceTypeSelected == 'N') {
            Knight();
        } else if (pieceTypeSelected == 'P') {
            Pawn();
        } else if (pieceTypeSelected == 'R') {
            Rook();
        } else if (pieceTypeSelected == 'Q') {
            Queen();
        } else {
            CommitToMove(fromPanel, toPanel);
        }

        Refresh.Board();
    }

    public static void CommitToMove(int fromPos, int toPos) {

        //detects if any of the pieces have moved that would prevent castling
        switch(fromPos) {
            case 0 -> blackCanLongCastle = false;
            case 4 -> {
                blackCanLongCastle = false;
                blackCanShortCastle = false;
            }
            case 7 -> blackCanShortCastle = false;
            case 56 -> whiteCanLongCastle = false;
            case 60 -> {
                whiteCanLongCastle = false;
                whiteCanShortCastle = false;
            }
            case 63 -> whiteCanShortCastle = false;
        }
        switch(toPos) {
            case 0 -> blackCanLongCastle = false;
            case 4 -> {
                blackCanLongCastle = false;
                blackCanShortCastle = false;
            }
            case 7 -> blackCanShortCastle = false;
            case 56 -> whiteCanLongCastle = false;
            case 60 -> {
                whiteCanLongCastle = false;
                whiteCanShortCastle = false;
            }
            case 63 -> whiteCanShortCastle = false;
        }




        piecePositions[toPos] = piecePositions[fromPos];
        piecePositions[fromPos] = '0';
        panelPieceColor[toPos] = panelPieceColor[fromPos];
        panelPieceColor[fromPos] = ' ';

        //en passant
        int index = IntStream.range(0, 64).filter(i -> piecePositions[i] == '&').findFirst().orElse(-1);
        if (index != -1) {
            piecePositions[index] = '0';
        }

        toPos = -1;
        toPanelX = -1;
        toPanelY = -1;

        turn = !turn;

        Highlight.HighlightClicked(fromPos, fromPanelX, fromPanelY);


    }

}
