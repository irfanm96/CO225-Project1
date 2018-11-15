package com.irfan;


import java.awt.*;
import java.lang.String.*;
import java.awt.*; /* java abstract window toolkit */
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Line2D;
import java.util.Random;


public class Fractals extends Thread {
    private int start;  // start point for a thread to print
    private int end; // end point for a thread to print
    // variables for region of interest
    private static double x1;
    private static double x2;
    private static double y1;
    private static double y2;

    private static int iterations; // number of iterations
    private static String whichTest; // variable to decide which test to print
    public static Panel p; // panel
    // set width and height of panel
    private static int width = 600;
    private static int height = 600;
    private static int numberOfThreads = 4; // can have variable amount of threads


    // constructor
    public Fractals(int start, int end) {
        this.start = start;
        this.end = end;
    }

    //getters
    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public static Panel getP() {
        return p;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    // run function for thread
    public void run() {
        if (whichTest.equals("J")) { // decide which test
            printJuliaSet();// print julia set
            return;
        }
            printMandelBrotSet(x1, x2, y1, y2, iterations); // print mandelbrot set
    }

    public void printMandelBrotSet(double x1, double x2, double y1, double y2, int iterations) {

        for (int i = 0; i < getWidth() + 1; i++) {
            for (int j = getStart(); j < getEnd() + 1; j++) {
                Point point = new Point(i, j); // create points
                Complex z = point.findMapping(getP(), x1, x2, y1, y2); // map to complex plane
                p.setPixelColor(point, z.mandelBrotSetTest(iterations));// set the pixel color in buffered image
                p.repaint();

            }
        }


    }

    public void printJuliaSet() {

        for (int i = 0; i < getWidth(); i++) {
            for (int j = getStart(); j < getEnd(); j++) {
                Point point = new Point(i, j); // create points
                Complex z = point.findMapping(getP(), -1, 1, -1, 1); // map to complex plane
                p.setPixelColor(point, z.juliaSetTest(1000));// set the pixel color in buffered image
                p.repaint();
            }
        }
    }


     // function to check arguments are valid
    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        String setName;
        int count = args.length;
        if (count != 6 && count!= 1 && count!=3 ) {
            System.out.println("Please give the proper commandline arguments");
            return;// defensive approach , if not valid argument length print message and exit
        }
        if (count == 1 || count == 3) { // check for julia set
            if (!args[0].equalsIgnoreCase("julia")) {
                System.out.println("Invalid Input,Please enter write keyword julia/mandelbrot");
                return;// if argument 1 is not julia exit
            }
            setName = "JuliaSet"; // frame name
            whichTest = "J";
            if (count == 3) {  // change the julia constant
                for (int i = 1; i < 3; i++) {
                    if (!isNumeric(args[i])) {
                        System.out.println("Invalid Input,need double/int for range and int for iterations for julia set");
                        return;
                    }
                }

                Complex.setJuliaSetConstant(new Complex(Double.parseDouble(args[1]), Double.parseDouble(args[2])));
            }
            Fractals.iterations = 1000;
            Fractals.createThread(setName);


        }


        if (count == 6) {
            if (!args[0].equalsIgnoreCase("mandelbrot")) {
                System.out.println("Invalid Input,Please enter write keyword mandelbrot/julia");
                return;// if first argument is not mandelbrot exit
            }
            setName = "MandelBrotSet";
            whichTest = "M";

            for (int i = 1; i < 6; i++) { // input check
                if (!isNumeric(args[i])) {
                    System.out.println("Invalid Input,need double/int for ranges and int for iterations");
                    return;
                }
            }

            Fractals.iterations = Integer.parseInt(args[5]); // set iterations

             // set region of interest
            Fractals.x1 = Double.parseDouble(args[1]);

            Fractals.x2 = Double.parseDouble(args[2]);

            Fractals.y1 = Double.parseDouble(args[3]);

            Fractals.y2 = Double.parseDouble(args[4]);

            Fractals.createThread(setName);

        }
    }


    private static void createThread(String name) {
        Fractals.p = Panel.createPanel(getWidth(), getHeight(), name); // create the panel
        int y=getHeight()/4;
        Fractals[] t = new Fractals[numberOfThreads];
        int i;
        for (i = 0; i < numberOfThreads-1 ; i++) { // create threads for divided ranges
            t[i] = new Fractals(i * y, (i + 1) * y);
            t[i].start();
        }
        t[i]=new Fractals((i-1)*y ,getHeight());
        t[i].start();
        try { // wait for all threads to complete
            for (int j = 0; j <numberOfThreads ; j++) {
                t[j].join();
            }
        } catch (InterruptedException e) { // if a thread is interrupted exit
            System.out.println("Thread execution interrupted");
            return;
        }
        Panel.frame.setVisible(true); // make the frame visible

    }
}

