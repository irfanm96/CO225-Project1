package com.irfan;

public class Complex {
    private double real;
    private double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex addComplex(Complex z1, Complex z2){
        double real=z1.real + z2.real;
        double imaginary=z1.imaginary + z2.imaginary;
        return  new Complex(real,imaginary);
    }

    public Complex calculateSquare(Complex z){
        double real = (z.real*z.real )- (z.imaginary*z.imaginary );
        double imaginary= 2*z.real*z.imaginary;
        return new Complex(real,imaginary);
    }

    public double calculateAbosluteValue(){

        double absolute= (this.real*this.real) + (this.imaginary*this.imaginary);
        return  Math.sqrt(absolute);
    }

    public boolean checkMod(){
        if(this.calculateAbosluteValue() < 2) {
            return  false;
        }
        return  true;
    }
}
