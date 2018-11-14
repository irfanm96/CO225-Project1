package com.irfan;


import java.awt.*;
import java.lang.String.*;
import java.awt.*; /* java abstract window toolkit */
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Line2D;
import java.util.Random;



public class Fractals extends Thread {
    private int start;
    private int end;
    private static double x1;
    private static double x2;
    private static double y1;
    private static double y2;
    private static int iterations;
    private static String whichTest;
    private static Panel p;
    private static int width = 600;
    private static int height = 800;


    public Fractals(int start, int end) {
        this.start = start;
        this.end = end;
    }

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

    public void run() {
        if (whichTest.equals("J")) {
            printJuliaSet(iterations);
            return;
        }if (whichTest.equals("M")){
            printMandelBrotSet(x1, x2, y1, y2, iterations);

        }

    }

    public void printMandelBrotSet(double x1, double x2, double y1, double y2, int iterations) {

        for (int i = 0; i < getWidth() + 1; i++) {
            for (int j = getStart(); j < getEnd() + 1; j++) {
                Point point = new Point(i, j); // create points
                Complex z = point.findMapping(getP(), -1, 1, -1, 1); // map to complex plane
                Panel.printPoint((Graphics2D) getP().getGraphics(), z.mandelBrotSetTest(iterations), point);

            }
        }


    }

    public void printJuliaSet(int iterations) {

        for (int i = 0; i < getWidth() + 1; i++) {
            for (int j = getStart(); j < getEnd() + 1; j++) {
                Point point = new Point(i, j); // create points
                Complex z = point.findMapping(getP(), -1, 1, -1, 1); // map to complex plane
                Panel.printPoint((Graphics2D) getP().getGraphics(), z.juliaSetTest(iterations), point);
            }
        }
    }


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
        int count=args.length;
        if (count>6 || count<1) {
            System.out.println("Please give the proper commandline arguments");
            return;
        }
        if (count== 1 || count==3) {
            if (!args[0].toLowerCase().equals("julia")) {
                System.out.println("Invalid Input,Please enter write keyword julia/mandelbrot");
                return;
            }
            setName = "JuliaSet";
            whichTest = "J";
            if(count==3) {
                for (int i = 1; i < 3; i++) {
                    if (!isNumeric(args[i])) {
                        System.out.println("Invalid Input,need double/int for range and int for iterations for julia set");
                        return;
                    }
                }

                Complex.setJuliaSetConstant(new Complex(Double.parseDouble(args[1]),Double.parseDouble(args[2])));
            }
            Fractals.iterations = 1000;
            int y = getHeight() / 4;
            Fractals.createThread(setName, y);


        }


        if (count == 6) {
            if (!args[0].toLowerCase().equals("mandelbrot")) {
                System.out.println("Invalid Input,Please enter write keyword mandelbrot/julia");
                return;
            }
            setName = "MandelBrotSet";
            whichTest = "M";


            for (int i = 1; i < 6; i++) {
                if (!isNumeric(args[i])) {
                    System.out.println("Invalid Input,need double/int for ranges and int for iterations");
                    return;
                }
            }

            Fractals.iterations = Integer.parseInt(args[5]);

            Fractals.x1 = Double.parseDouble(args[1]);

            Fractals.x2 = Double.parseDouble(args[2]);

            Fractals.y1 = Double.parseDouble(args[3]);

            Fractals.y2 = Double.parseDouble(args[4]);

            int y = getHeight() / 4;
            System.out.println(y);
            Fractals.createThread(setName, y);

        }
    }


        private static void createThread (String name,int y){
            Fractals.p = Panel.createPanel(getWidth(), getHeight(), name);

            Fractals t1 = new Fractals(0, y);
            Fractals t2 = new Fractals(y, 2 * y);
            Fractals t3 = new Fractals(2 * y, 3 * y);
            Fractals t4 = new Fractals(3 * y, getHeight());
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            System.out.println("thread started");
            try {
                t1.join();
                t2.join();
                t3.join();
                t4.join();
            }catch (InterruptedException e){
                System.out.println("Thread execution interupted");
                return;
            }
            System.out.println("no interuptions");
        }
    }

