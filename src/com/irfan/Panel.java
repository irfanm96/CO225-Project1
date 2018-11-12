package com.irfan;

import java.awt.*; /* java abstract window toolkit */
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Line2D;
import java.util.Random;


public class Panel extends JPanel { // inherit JPanel

    private int width, height;

    public Panel(int width, int height) {
        // set the panel size
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
    }

    public static void printPoint(Graphics2D frame, Color c, Point p) {

        frame.setColor(c);
        frame.draw(new Line2D.Double(p.getX(), p.getY(),
                p.getX(), p.getY()));
    }

    public void paintComponent(Graphics g) {
        // call paintComponent from parent class
        super.paintComponent(g);

    }

    public static Panel createPanel(int width, int height ,String name) {

        // create a frame
        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set the content of the frame as one of this panel
        Panel p = new Panel(width, height);
        frame.setContentPane(p);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return p;
    }
}
