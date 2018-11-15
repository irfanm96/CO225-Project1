package com.irfan;

import java.awt.*; /* java abstract window toolkit */
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.Color;

public class Panel extends JPanel { // inherit JPanel

    private int width, height;
    private static BufferedImage bufferedImage;
    public static JFrame frame;

    public Panel(int width, int height) {
        // set the panel size
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }
    public void paintComponent(Graphics g) {
        // call paintComponent from parent class
        super.paintComponent(g);
        g.drawImage(bufferedImage, 0, 0, this);


    }

    public void setPixelColor(Point p,Color c) {
        bufferedImage.setRGB((int) p.getX(), (int) p.getY(), c.getRGB()); // set colors in buffered image
    }

    public static Panel createPanel(int width, int height, String name) {
        // create a frame
         frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set the content of the frame as one of this panel
        Panel p = new Panel(width, height);
        frame.setContentPane(p);

        frame.pack();
        frame.setLocationRelativeTo(null);
        return p;
    }
}
