package com.blog.demo.component;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableValue implements Parcelable {

    private int i;
    private double d;
    private String s;

    public ParcelableValue(int i, double d, String s) {
        this.i = i;
        this.d = d;
        this.s = s;
    }

    public ParcelableValue(Parcel data) {
        i = data.readInt();
        d = data.readDouble();
        s = data.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(i);
        dest.writeDouble(d);
        dest.writeString(s);
    }

    @Override
    public String toString() {
        return i + ", " + d + ", " + s;
    }

    public static final Creator<ParcelableValue> CREATOR = new Parcelable.Creator<ParcelableValue>() {

        @Override
        public ParcelableValue createFromParcel(Parcel source) {
            return new ParcelableValue(source);
        }

        @Override
        public ParcelableValue[] newArray(int size) {
            return new ParcelableValue[size];
        }
    };

}
