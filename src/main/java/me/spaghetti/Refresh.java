package main.java.me.spaghetti;

import javax.swing.*;
import java.awt.*;

import static main.java.me.spaghetti.ChessEngine.*;

public class Refresh {

    public static void Board() {
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


}
