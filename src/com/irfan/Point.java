package  com.irfan;
public class Point {

    private double x, y; 
    
    public Point(double x, double y) { 
	this.x = x; 
	this.y = y; 
    }

    public double getX() { return this.x; } 
    public double getY() { return this.y; } 
    
    // bunch of overloaded functions 
    public Point findMidTo(Point p) { 
	return new Point((this.getX() + p.getX())/2, 
			 (this.getY() + p.getY())/2); 
    }

    public Point findMidTo(Point p1, Point p2) { 
	return new Point((this.getX() + p1.getX() + p2.getX())/3, 
			 (this.getY() + p1.getY() + p2.getY())/3 ); 
    }

    public static Point findMidTo(Point p1, Point p2, Point p3) { 
	return new Point((p1.getX() + p2.getX() + p3.getX())/3, 
			 (p1.getY() + p2.getY() + p3.getY())/3 ); 
    }

    public Complex findMapping( Panel p ,double x1 ,double x2, double y1 ,double y2){
        double width=p.getWidth();
        double height=p.getHeight();
        double real = ((x2-x1)*(this.x/width)) + x1;
        double imaginary = ((y2-y1)*(this.y/height)) + y1;
        return new Complex(real,imaginary);
    }
}
	