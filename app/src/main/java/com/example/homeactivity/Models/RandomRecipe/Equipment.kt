package com.example.homeactivity.Models.RandomRecipe

import android.os.Parcel
import android.os.Parcelable

data class Equipment(
    val id: Int,
    val image: String,
    val localizedName: String,
    val name: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        id = parcel.readInt(),
        image = parcel.readString() ?: "",
        localizedName = parcel.readString() ?: "",
        name = parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(image)
        parcel.writeString(localizedName)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Equipment> {
        override fun createFromParcel(parcel: Parcel): Equipment {
            return Equipment(parcel)
        }

        override fun newArray(size: Int): Array<Equipment?> {
            return arrayOfNulls(size)
        }
    }
}
