package com.blog.demo.jni;

public class NativeCalc {

    public native double add(double left, double right);

    public native double subtract(double left, double right);

    public native double multiply(double left, double right);

    public native double divide(double left, double right);

}
