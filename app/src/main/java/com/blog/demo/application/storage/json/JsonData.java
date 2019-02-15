package com.blog.demo.application.storage.json;

import com.blog.demo.People;

public class JsonData {
    public boolean aBoolean;
    public int aInt;
    public double aDouble;
    public String aString;
    public People aObject;
    public String[] aStringArray;
    public People[] aObjectArray;

    @Override
    public String toString() {
        return "aBoolean = " + aBoolean + ",\n"
                + "aInt = " + aInt + ",\n"
                + "aDouble = " + aDouble + ",\n"
                + "aString = " + aString + ",\n"
                + "aObject = " + aObject.description() + ",\n"
                + "aStringArray = " + toString(aStringArray) + ",\n"
                + "aObjectArray = " + toString(aObjectArray);
    }

    private String toString(String[] stringArray) {
        String string = "";
        for (String str : stringArray) {
            string += str + ",";
        }
        return string.substring(0, string.length() - 1);
    }

    private String toString(People[] objectArray) {
        String string = "";
        for (People people : objectArray) {
            string += people.description() + ",";
        }
        return string.substring(0, string.length() - 1);
    }

}
