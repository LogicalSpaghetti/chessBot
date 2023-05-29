package main.java.me.spaghetti;

import static java.lang.Math.abs;
import static main.java.me.spaghetti.ChessEngine.*;

public class Move {

    public static void King(int fromPos, int toPos) {

        //don't forget both castles and for both colors

        if(abs(toPos%8) > (fromPos + 1)) { //what does this do?
            return;
        }
        if(fromPos%8 == 0 && toPos%8 == 7) { //far left
            return;
        }
        if(fromPos%8 == 7  && toPos%8 == 0) { //far right
            return;
        }


        //these work for now, but once all the other movement is done, run a check to ensure the square isn't defended
        if(toPos > (fromPos - 10) && toPos < (fromPos - 6)) { //handles all cases of moving up
            CommitToMove(fromPos, toPos);
        }

        if(toPos > (fromPos - 2) && toPos < (fromPos + 2)) { //handles all cases of moving sideways
            CommitToMove(fromPos, toPos);
        }

        if(toPos > (fromPos + 6) && toPos < (fromPos + 10)) { //handles all cases of moving down
            CommitToMove(fromPos, toPos);
        }


    }

    public static void Knight(int fromPos, int toPos) {


        int dif = (fromPos%8) - (toPos%8);
        dif = abs(dif);
        if(dif > 2) {
            return;
        }

        int absDif = abs(toPos - fromPos);
        if(absDif == 17 || absDif == 15 || absDif == 10 || absDif == 6) {
            CommitToMove(fromPos, toPos);
        }

    }

    public static void Pawn(int fromPos, int toPos) {

        int sign;
        if(pieceSelected == 'P') {
            sign = -1;
        } else {
            sign = 1;
        }

        if(toPos == fromPos+(sign*16)) { //double move
            if((fromPos > 7 && fromPos < 16) || (fromPos > 47 && fromPos < 56)) {
                if (piecePositions[toPos] == '0' && piecePositions[fromPos + (sign * 8)] == '0') {
                    CommitToMove(fromPos, toPos);
                }
            }
        } else if(toPos == fromPos+(sign*8)) { //single move
            if(piecePositions[toPos] == '0') {
                CommitToMove(fromPos, toPos);
            }
        } else if (toPos == fromPos+(sign*9) || toPos == fromPos+(sign*7)) { //attack left
            if(sign == -1) {
                if (Character.isLowerCase(piecePositions[toPos])) {
                    CommitToMove(fromPos, toPos);
                }
            } else {
                if (Character.isUpperCase(piecePositions[toPos])) {
                    CommitToMove(fromPos, toPos);
                }
            }
        }

        //promotion
        if(toPos > 55 || toPos < 8) {
            Promote(toPos);
        }
    }

    public static void Promote(int toPos) {

        //the turn was already swapped by CommitToMove
        if(turn) {
            piecePositions[toPos] = 'q';
        } else {
            piecePositions[toPos] = 'Q';
        }
    }

    public static void MovePiece(int fromPos, int toPos) {
        //just move them in piecePositions[] and refreshBoard[]
        //also change the values in panelPieceColor

        if(toPos == fromPos) {
            Highlight.HighlightClicked(toPos);
            return;
        }

        //checks if the new square isn't the same color
        if((panelPieceColor[toPos] == 'W' && turn) || (panelPieceColor[toPos] == 'B' && !turn)) {
            Highlight.HighlightClicked(toPos);
            return;
        }


        if(pieceSelected == 'K' || pieceSelected == 'k') {
            King(fromPos, toPos);
        } else if(pieceSelected == 'N' || pieceSelected == 'n') {
            Knight(fromPos, toPos);
        } else if (pieceSelected == 'P' || pieceSelected == 'p') {
            Pawn(fromPos, toPos);
        } else {
            CommitToMove(fromPos, toPos);
        }

        Refresh.Board();
    }

    public static void CommitToMove(int fromPos, int toPos) {

        //detects if any of the pieces have moved that would prevent castling
        switch(fromPos) {
            case 0 -> blackQRookHasMoved = true;
            case 4 -> blackKingHasMoved = true;
            case 7 -> blackKRookHasMoved = true;
            case 56 -> whiteQRookHasMoved = true;
            case 60 -> whiteKingHasMoved = true;
            case 63 -> whiteKRookHasMoved = true;
        }



        piecePositions[toPos] = piecePositions[fromPos];
        piecePositions[fromPos] = '0';
        panelPieceColor[toPos] = panelPieceColor[fromPos];
        panelPieceColor[fromPos] = ' ';

        pieceToMove = -1;
        turn = !turn;

        Highlight.HighlightClicked(fromPos);


    }

}
