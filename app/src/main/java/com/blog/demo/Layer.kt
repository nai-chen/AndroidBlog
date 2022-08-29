package com.blog.demo

import android.os.Parcel
import android.os.Parcelable

class Layer(var name: String?) : Parcelable {
    private val SEPERATOR = "/"

    private var className: String? = ""
    private val children: MutableList<Layer> = mutableListOf()

    constructor(parcel: Parcel) : this(parcel.readString()) {
        className = parcel.readString()
        parcel.readTypedList(children, CREATOR)
    }

    fun addItem(path: String, className: String?) {
        val index: Int = path.indexOf(SEPERATOR)
        if (index == -1) {
            val child = addItem(path)
            child.className = className
        } else {
            val child = addItem(path.substring(0, index))
            child.addItem(path.substring(index + 1), className)
        }
    }

    private fun addItem(path: String): Layer {
        for (child in children) {
            if (child.name == path) {
                return child
            }
        }
        val child = Layer(path)
        children.add(child)
        return child
    }

    fun getValue(position: Int): Layer? {
        return children[position]
    }

    fun getCount(): Int {
        return children.size
    }

    fun isEmpty(): Boolean {
        return children.isEmpty()
    }

    fun getClassName(): String? {
        return className
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(className)
        parcel.writeTypedList(children)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Layer> {
        override fun createFromParcel(parcel: Parcel): Layer {
            return Layer(parcel)
        }

        override fun newArray(size: Int): Array<Layer?> {
            return arrayOfNulls(size)
        }
    }

}