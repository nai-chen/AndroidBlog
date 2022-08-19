package com.blog.demo.component

import android.os.Parcel
import android.os.Parcelable

class ParcelableValue constructor(private val i: Int, private val d: Double, private val s: String?) : Parcelable {

    constructor(data: Parcel) : this(data.readInt(), data.readDouble(), data.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(i)
        parcel.writeDouble(d)
        parcel.writeString(s)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "$i, $d, $s"
    }

    companion object CREATOR : Parcelable.Creator<ParcelableValue> {
        override fun createFromParcel(parcel: Parcel): ParcelableValue {
            return ParcelableValue(parcel)
        }

        override fun newArray(size: Int): Array<ParcelableValue?> {
            return arrayOfNulls(size)
        }
    }


}