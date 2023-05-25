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
        this.setSize(width, height + 30); // Add 30 to account for the frame title bar, and sets x and y dimension of this
        this.setMinimumSize(new Dimension(width, height));

        this.addMouseListener(this);

        this.setResizable(false);

        this.setVisible(true);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = (e.getX()) / chessEngine.tileSize; // Subtract 8 to account for the frame border
        int y = (e.getY() - 30) / chessEngine.tileSize; // Subtract 30 to account for the frame title bar

        System.out.println("Button: " + e.getButton() + ", x = " + x + " y = " + y);
        chessEngine.highlightClicked(x, y);
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