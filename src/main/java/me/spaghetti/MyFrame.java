//contains the basic frame definition as well as the MouseListener

package main.java.me.spaghetti;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyFrame extends JFrame implements MouseListener{

    MyFrame(String title, int width, int height){

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int locX = (int)((screenSize.getWidth()-width)/2); //accounts for the size of the window when centering it
        int locY = (int)(((screenSize.getHeight()-height)/2));
        this.setLocation(locX, locY); //location is the top left corner of the window

        ImageIcon image = new ImageIcon("resources/logo.png"); //create image icon
        this.setIconImage(image.getImage()); //change icon of this
        this.getContentPane().setBackground(new Color(0xFFFFFF)); //change color of background
        this.setTitle(title); //sets title of this

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of the application
        this.setSize(width+20-4, height+30+10-1); // Add 30 to account for the frame title bar, and sets x and y dimension of this
        //this.setMinimumSize(new Dimension(width, height));

        this.addMouseListener(this);

        this.setResizable(false);

        this.setVisible(true);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        System.out.println(e.getX() + " " + e.getY());

        int x = (e.getX()) / ChessEngine.tileSize;
        int y = (e.getY() - 30) / ChessEngine.tileSize;
        int panelNumber = (y*8)+x;

        System.out.println("panelNumber = " + panelNumber);
        System.out.println("panelNumber%8 = " + (panelNumber%8));

        if(e.getButton() == 1) {
            Left.PanelClicked(x, y);
        } else if (e.getButton() == 3) {
            Highlight.RedHighlight(panelNumber);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}