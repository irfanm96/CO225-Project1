package com.irfan;

import java.awt.*;

public class Complex {
    private double real;
    private double imaginary;
    private static Complex mandelbrotSetConstant = new Complex(0, 0); // mandelbort constant
    private static Complex juliaSetConstant = new Complex(-0.4, 0.6);// julia constant

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }


    public static void setJuliaSetConstant(Complex juliaSetConstant) {
        Complex.juliaSetConstant = juliaSetConstant;
    }
    // function to add two complex numbers
    public static Complex addComplex(Complex z1, Complex z2) {
        double real = z1.getReal() + z2.getReal();
        double imaginary = z1.getImaginary() + z2.getImaginary();
        return new Complex(real, imaginary);
    }
  // calculate the square of complex number
    public Complex calculateSquare() {
        double real = (Math.pow(getReal(),2)) - (Math.pow(getImaginary(),2));
        double imaginary = 2 * getReal() * getImaginary();
        return new Complex(real, imaginary);
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    private double calculateAbosluteValue() {

        double absolute = (Math.pow(getReal(),2)) + (Math.pow(getImaginary(),2));
        return absolute;// not returning the square root
    }
 // check modulus of the complex number
    private boolean checkMod() {
        if (this.calculateAbosluteValue() < 4) { // fractals test
            return false;
        }
        return true;
    }
// calculate the sequence of mandelbrot complex numbers
    private static Complex calculateMandelbrot(Complex z1, Complex z2) {
        Complex newZ;
        newZ = addComplex(z1.calculateSquare(), z2);
        return newZ;
    }
    // calculate the sequence of mandelbrot complex numbers
    private static Complex calculateJulia(Complex z1) {
        Complex newZ;
        newZ = addComplex(z1.calculateSquare(), juliaSetConstant);
        return newZ;
    }

 // return the appropriate color after mandelbrot test
    public Color mandelBrotSetTest(int iterations) {

        Complex newz = calculateMandelbrot(mandelbrotSetConstant, this);

        for (int i = 0; i < iterations; i++) {
            if (newz.checkMod()) {
                return  new Color(Color.HSBtoRGB(i/1024f,1f,i/(i+10f)));
            }
            newz = calculateMandelbrot(newz, this);
        }

        return Color.BLACK;

    }
    // return the appropriate color after julia test

    public Color juliaSetTest(int iterations ) {

        Complex newz = calculateJulia(this);
        for (int i = 0; i < iterations; i++) {
            if (newz.checkMod()) {
               return new Color(Color.HSBtoRGB(i/1024f,1f,i/(i+10f)));
            }
            newz = calculateJulia( newz);
        }

        return Color.BLACK;


    }

}

