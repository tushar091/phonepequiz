package com.example.phonepequiz.model

import android.os.Parcel
import android.os.Parcelable

data class Logos(
    val imageUrl: String?,
    val name: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imageUrl)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Logos> {
        override fun createFromParcel(parcel: Parcel): Logos {
            return Logos(parcel)
        }

        override fun newArray(size: Int): Array<Logos?> {
            return arrayOfNulls(size)
        }
    }
}