package com.irfan;

import java.awt.*;

public class Complex {
    private double real;
    private double imaginary;
    public static Complex mandelbrotSetConstant = new Complex(0, 0);
    public static Complex juliaSetConstant = new Complex(-0.4, 0.6);

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    public static void setMandelbrotSetConstant(Complex mandelbrotSetConstant) {
        Complex.mandelbrotSetConstant = mandelbrotSetConstant;
    }

    public static void setJuliaSetConstant(Complex juliaSetConstant) {
        Complex.juliaSetConstant = juliaSetConstant;
    }

    public static Complex addComplex(Complex z1, Complex z2) {
        double real = z1.getReal() + z2.getReal();
        double imaginary = z1.getImaginary() + z2.getImaginary();
        return new Complex(real, imaginary);
    }

    public Complex calculateSquare() {
        double real = (getReal() * getReal()) - (getImaginary() * getImaginary());
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

        double absolute = (getReal() * getReal()) + (getImaginary() * getImaginary());
        return absolute;
    }

    private boolean checkMod() {
        if (this.calculateAbosluteValue() < 4) {
            return false;
        }
        return true;
    }

    private static Complex calculateMandelbrot(Complex z1, Complex z2) {
        Complex newZ;
        newZ = addComplex(z1.calculateSquare(), z2);
        return newZ;
    }
    public static Complex calculateJulia(Complex z1) {
        Complex newZ;
        newZ = addComplex(z1.calculateSquare(), juliaSetConstant);
        return newZ;
    }


    public Color mandelBrotSetTest(int iterations) {

        Complex newz = calculateMandelbrot(mandelbrotSetConstant, this);

        for (int i = 0; i < iterations; i++) {
            if (newz.checkMod()) {
                return  new Color(Color.HSBtoRGB(i/256f,1,i/(i+8f)));
            }
            newz = calculateMandelbrot(newz, this);
        }

        return Color.BLACK;

    }
    public Color juliaSetTest(int iterations ) {

        Complex newz = calculateJulia(this);
        for (int i = 0; i < iterations; i++) {
            if (newz.checkMod()) {
               return new Color(Color.HSBtoRGB(i/256f,0.8f,i/(i+8f)));
            }
            newz = calculateJulia( newz);
        }

        return Color.BLACK;


    }

}

