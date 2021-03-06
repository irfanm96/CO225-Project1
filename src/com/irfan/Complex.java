package com.irfan;

import java.awt.*;

public class Complex {
    private double real;
    private double imaginary;
    private static Complex manderboltConstant = new Complex(0, 0);
    private static Complex juliaConstant = new Complex(-0.4, 0.6);

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public static Complex addComplex(Complex z1, Complex z2) {
        double real = z1.real + z2.real;
        double imaginary = z1.imaginary + z2.imaginary;
        return new Complex(real, imaginary);
    }

    public Complex calculateSquare() {
        double real = (this.real * this.real) - (this.imaginary * this.imaginary);
        double imaginary = 2 * this.real * this.imaginary;
        return new Complex(real, imaginary);
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    private double calculateAbosluteValue() {

        double absolute = (this.real * this.real) + (this.imaginary * this.imaginary);
        return absolute;
    }

    public boolean checkMod() {
        if (this.calculateAbosluteValue() < 4) {
            return false;
        }
        return true;
    }

    public static Complex calculateManderbolt(Complex z1, Complex z2) {
        Complex newZ;
        newZ = addComplex(z1.calculateSquare(), z2);
        return newZ;
    }
    public static Complex calculateJulia(Complex z1) {
        Complex newZ;
        newZ = addComplex(z1.calculateSquare(), juliaConstant);
        return newZ;
    }


    public Color test1(int iterations) {

        Complex newz = calculateManderbolt(manderboltConstant, this);

        for (int i = 0; i < iterations; i++) {
            if (newz.checkMod()) {
                return  new Color(Color.HSBtoRGB(i/256f,1,i/(i+8f)));
            }
            newz = calculateManderbolt(newz, this);
        }

        return Color.BLACK;

    }
    public Color test2(int iterations ) {

        Complex newz = calculateJulia(this);
        for (int i = 0; i < iterations; i++) {
            if (newz.checkMod()) {
               return new Color(Color.HSBtoRGB(i/256f,0.8f,i/(i+8f)));
            }
            newz = calculateJulia( newz);
        }

        return Color.CYAN;


    }

}

