package com.example.homeactivity.Model

import android.os.Parcel
import android.os.Parcelable

data class Ingredient(
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

    companion object CREATOR : Parcelable.Creator<Ingredient> {
        override fun createFromParcel(parcel: Parcel): Ingredient {
            return Ingredient(parcel)
        }

        override fun newArray(size: Int): Array<Ingredient?> {
            return arrayOfNulls(size)
        }
    }
}
