package com.irfan;


import java.awt.*;
import java.lang.String.*;

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


    public void run() {
        if (whichTest.equals("J")) {
            printJuliaSet(iterations);
        } else {
            printMandelBrotSet(x1, x2, y1, y2, iterations);
        }

    }

    public void printMandelBrotSet(double x1, double x2, double y1, double y2, int iterations) {

        for (int i = 0; i < getP().getWidth() + 1; i++) {
            for (int j = getStart(); j < getEnd() + 1; j++) {
                Point point = new Point(i, j); // create points
                Complex z = point.findMapping(getP(), x1, x2, y1, y2); // map to complex plane
                Panel.printPoint((Graphics2D) getP().getGraphics(), z.mandelBrotSetTest(iterations), point);

            }
        }


    }

    public void printJuliaSet(int iterations) {

        for (int i = 0; i < getP().getWidth() + 1; i++) {
            for (int j = getStart(); j < getEnd() + 1; j++) {
                Point point = new Point(i, j); // create points
                Complex z = point.findMapping(p, -1, 1, -1, 1); // map to complex plane
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
        if (args.length==0) {
            System.out.println("Please enter other options");
            return;
        }
        if (args.length == 4) {
            if (!args[0].toLowerCase().equals("julia")) {
                System.out.println("Invalid Input,Please enter write keyword julia/mandelbrot");
                return;
            }
            setName = "JuliaSet";
            whichTest = "J";

            for (int i = 1; i < 3; i++) {
                if (!isNumeric(args[i])) {
                    System.out.println("Invalid Input,need double/int for range and int for iterations for julia set");
                    return;
                }
            }
            Fractals.iterations=Integer.parseInt(args[3]);
            int y = getP().getHeight() / 4;
            createThread(setName, y, getP().getHeight());
            return;

        }
        if (args.length == 6) {
            if (!args[0].toLowerCase().equals("mendelbrot")) {
                System.out.println("Invalid Input,Please enter write keyword mandelbrot/julia");
                return;
            }
            setName = "MandelBrotSet";

            for (int i = 1; i < 6; i++) {
                if (!isNumeric(args[i])) {
                    System.out.println("Invalid Input,need double/int for ranges and int for iterations");
                    return;
                }
            }

            Fractals.iterations=Integer.parseInt(args[5]);

            Fractals.x1=Double.parseDouble(args[1]);

            Fractals.x2=Double.parseDouble(args[2]);

            Fractals.y1=Double.parseDouble(args[3]);

            Fractals.y2=Double.parseDouble(args[4]);

            int y = getP().getHeight() / 4;
            createThread(setName, y, getP().getHeight());

        }


    }

    private static void createThread(String name, int y, int height) {
        Fractals.p = Panel.createPanel(800, 800, name);
        Fractals t1 = new Fractals(0, y);
        Fractals t2 = new Fractals(y, 2 * y);
        Fractals t3 = new Fractals(2 * y, 3 * y);
        Fractals t4 = new Fractals(3 * y, height);
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}
