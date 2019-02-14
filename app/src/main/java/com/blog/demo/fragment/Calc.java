package com.blog.demo.fragment;

import com.blog.demo.jni.NativeCalc;

public class Calc {
    static {
        System.loadLibrary("calcLibs");
    }

    private NativeCalc mCalc;
    public Calc() {
        mCalc = new NativeCalc();
    }

    public double add(double left, double right) {
        return mCalc.add(left, right);
    }

    public double subtract(double left, double right) {
        return mCalc.subtract(left, right);
    }

    public double multiply(double left, double right) {
        return mCalc.multiply(left, right);
    }

    public double divide(double left, double right) {
        return  mCalc.divide(left, right);
    }

}

