package com.irfan;

public class Complex {
    private double real;
    private double imaginary;
    public static Complex manderboltConstant = new Complex(0, 0);
    public static Complex juliaConstant = new Complex(-0.5, 0.156);

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

    double calculateAbosluteValue() {

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


    public boolean test1(Complex z) {
        int iterations = 1000;
        Complex newz = calculateManderbolt(manderboltConstant, z);
        for (int i = 0; i < iterations; i++) {
            if (newz.checkMod()) {
                return true;
            }
            newz = calculateManderbolt(newz, z);
        }
        return false;

    }
    public boolean test2(Complex z) {
        int iterations = 1000;

        Complex newz = calculateJulia(z);
        for (int i = 0; i < iterations; i++) {
            if (newz.checkMod()) {
                return true;
            }
            newz = calculateManderbolt(newz, z);
        }
        return false;

    }

}
}
