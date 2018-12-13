package com.blog.demo;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Layer implements Parcelable {

	private String name = "";
	private String className = "";
	private List<Layer> mChild = new ArrayList<Layer>();

	public Layer() {
	}
	
	public Layer(String name) {
		this.name = name;
	}
	
	public Layer(Parcel parcel) {
		name = parcel.readString();
		className = parcel.readString();
		parcel.readTypedList(mChild, CREATOR);
	}
	
	public void addValue(String label, String className) {
		int index = label.indexOf('/');
		if (index == -1) {
			addChild(label, className);
		} else {
			Layer child = addChild(label.substring(0, index));
			child.addValue(label.substring(index + 1), className);
		}
	}
	
	private Layer addChild(String label) {
		for (Layer child : mChild) {
			if (child.name.equals(label))
				return child;
		}
		Layer child = new Layer(label);
		mChild.add(child);
		return child;
	}
	
	private Layer addChild(String label, String className) {
		Layer child = addChild(label);
		child.className = className;
		return child;
	}
	
	public boolean hasChild() {
		return size() > 0;
	}
	
	public int size() {
		return mChild.size();
	}
	
	public Layer getChild(int index) {
		return mChild.get(index);
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
		dest.writeTypedList(mChild);
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