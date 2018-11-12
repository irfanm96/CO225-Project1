package com.irfan;

import java.awt.*;

public class Fractals extends Thread {
    private int start;
    private int end ;

   private static Panel p;
    public Fractals(int start, int end ) {
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

		for(int i=0 ; i<getP().getWidth()+1 ; i++) {
			for(int j =getStart() ; j<getEnd()+1;j++) {
				Point point=new Point(i,j); // create points
				Complex z=point.findMapping(p,-1,1,-1,1); // map to complex plane
//				Panel.printPoint((Graphics2D) getP().getGraphics(),z.test1(1000),point);
			}
		}


    }

    public static void main(String[] args){
        Fractals.p= Panel.createPanel(800,800);
        Fractals t1=new Fractals(0,200);
        Fractals t2=new Fractals(200,400);
        Fractals t3=new Fractals(400,600);
        Fractals t4=new Fractals(600,800);
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}
