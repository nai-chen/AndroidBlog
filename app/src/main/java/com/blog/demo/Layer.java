package com.blog.demo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Layer implements Parcelable {
    private static final String SEPERATOR = "/";
    private String name;
    private String className = "";
    private List<Layer> children = new ArrayList<>();

    public Layer(String name) {
        this.name = name;
    }

    public Layer(Parcel parcel) {
        name = parcel.readString();
        className = parcel.readString();
        parcel.readTypedList(children, CREATOR);
    }

    public void addItem(String path, String className) {
        int index = path.indexOf(SEPERATOR);
        if (index == -1) {
            Layer child = addItem(path);
            child.className = className;
        } else {
            Layer child = addItem(path.substring(0, index));
            child.addItem(path.substring(index + 1), className);
        }
    }

    private Layer addItem(String path) {
        for (Layer child : children) {
            if (child.name.equals(path)) {
                return child;
            }
        }
        Layer child = new Layer(path);
        children.add(child);
        return child;
    }

    public Layer getValue(int position) {
        return children.get(position);
    }

    public int getCount() {
        return children.size();
    }

    public boolean isEmpty() {
        return children.size() == 0;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(className);
        dest.writeTypedList(children);
    }

    public static final Creator<Layer> CREATOR = new Creator<Layer>() {
        public Layer createFromParcel(Parcel source) {
            return new Layer(source);
        }

        public Layer[] newArray(int size) {
            return new Layer[size];
        }
    };

}
