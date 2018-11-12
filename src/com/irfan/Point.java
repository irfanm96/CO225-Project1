package  com.irfan;
public class Point {

    private double x, y; 
    
    public Point(double x, double y) { 
	this.x = x; 
	this.y = y; 
    }

    public double getX() { return this.x; } 
    public double getY() { return this.y; } 

    public Complex findMapping( Panel p ,double x1 ,double x2, double y1 ,double y2 ){
        double width=p.getWidth();
        double height=p.getHeight();
        double real = ((x2-x1)*(getX()/width)) + x1;
        double imaginary = ((y1-y2)*(getY()/height)) + y2;
        return new Complex(real,imaginary);
    }
}
	