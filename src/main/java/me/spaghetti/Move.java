package main.java.me.spaghetti;

import java.util.stream.IntStream;

import static java.lang.Math.abs;
import static main.java.me.spaghetti.ChessEngine.*;

public class Move {

    public static void King(int fromPos, int toPos) {

        //castling
        if(turn) { //handles case for white
            if(whiteCanShortCastle && toPos == 62) {
                if(piecePositions[61] == '0' && piecePositions[62] == '0') {
                    CommitToMove(fromPos, toPos);
                    CommitToMove(63, 61); //moves the rook
                    turn = !turn;
                }
            } else if(whiteCanLongCastle && toPos == 58) {
                if(piecePositions[57] == '0' && piecePositions[58] == '0' && piecePositions[59] == '0') {
                    CommitToMove(fromPos, toPos);
                    CommitToMove(56, 59); //moves the rook
                    turn = !turn;
                }
            }
        } else { //handles case for black
            if(blackCanShortCastle && toPos == 6) {
                if(piecePositions[5] == '0' && piecePositions[6] == '0') {
                    CommitToMove(fromPos, toPos);
                    CommitToMove(7, 5); //moves the rook
                    turn = !turn;
                }
            } else if(blackCanLongCastle && toPos == 2) {
                if(piecePositions[1] == '0' && piecePositions[2] == '0' && piecePositions[3] == '0') {
                    CommitToMove(fromPos, toPos);
                    CommitToMove(0, 3); //moves the rook
                    turn = !turn;
                }
            }

        }

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
                    piecePositions[fromPos + (sign * 8)] = '&'; //en passant
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
                } else if(piecePositions[toPos] == '&') { //en passant
                    CommitToMove(fromPos, toPos);
                    piecePositions[toPos + 8] = '0';
                    panelPieceColor[toPos + 8] = '0';
                }
            } else {
                if (Character.isUpperCase(piecePositions[toPos])) {
                    CommitToMove(fromPos, toPos);
                } else if(piecePositions[toPos] == '&') { //en passant
                    CommitToMove(fromPos, toPos);
                    System.out.println("(toPos + (sign * -8))" + (toPos + (sign * -8)));
                    piecePositions[toPos - 8] = '0';
                    panelPieceColor[toPos - 8] = '0';

                }
            }
        }

        //promotion
        if(toPos > 55 || toPos < 8) {
            Promote(toPos);
        }
    }

    public static void Promote(int toPos) {

        //currently only promotes to a Queen
        //the turn was swapped by CommitToMove, so this code looks backwards, but it isn't
        if(turn) {
            piecePositions[toPos] = 'q';
        } else {
            piecePositions[toPos] = 'Q';
        }
    }

    public static void Queen(int fromPos, int toPos) {
        //8 checks for move validity which stop at the edge of the board, on an enemy, or the square before an ally

    }

    public static void Bishop(int fromPos, int toPos) {
        //4 checks for move validity which stop at the edge of the board, on an enemy, or the square before an ally

    }

    public static void Rook(int fromPos, int toPos) {
        //4 checks for move validity which stop at the edge of the board, on an enemy, or the square before an ally

        //find direction of movement
        int direction; // -1 = left, 1 = right, -8 = up, 8 = down
        int distance;
        System.out.println("fromPos: " + fromPos + " toPos: " + toPos);
        if(toPos%8 == fromPos%8) { // true for up or down only
            if (toPos > fromPos) { // true for down only
                direction = 8;
            } else {
                direction = -8;
            }
            distance = abs((toPos-fromPos)/8);
        } else if ((fromPos - (fromPos%8)) == (toPos - (toPos%8))) { // true for left or right only
            if(toPos < fromPos) { // true for left only
                direction = -1;
            } else {
                direction = 1;
            }
            distance = abs(toPos-fromPos);
        } else {
            return;
        }

        for(int i = 1; i < (distance+1); i++) {
            //is tile obstructed by ally?
            if(panelPieceColor[fromPos] == panelPieceColor[fromPos + (direction*i)]) {

            } else {

            }
            //is tile the destination

        }

        // run a check in the right direction

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
        } else if (pieceSelected == 'R' || pieceSelected == 'r') {
            Rook(fromPos, toPos);
        } else {
            CommitToMove(fromPos, toPos);
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
        if(index != -1) {
            piecePositions[index] = '0';
        }

        pieceToMove = -1;
        turn = !turn;

        Highlight.HighlightClicked(fromPos);


    }

}
