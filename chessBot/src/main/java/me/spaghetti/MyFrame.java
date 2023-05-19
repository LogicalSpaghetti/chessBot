package main.java.me.spaghetti;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public class MyFrame extends JFrame {

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
        this.setSize(width, height); //sets x and y dimension of this
        this.setResizable(false);

    }

}