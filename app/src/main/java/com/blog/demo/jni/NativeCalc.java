package com.blog.demo.jni;

/**
 * Created by cn on 2017/3/27.
 */

public class NativeCalc {

    public native double add(double v1, double v2);

    public native double subtract(double v1, double v2);

    public native double multiply(double v1, double v2);

    public native double divide(double v1, double v2);

}
