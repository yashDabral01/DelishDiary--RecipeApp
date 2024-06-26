package com.example.homeactivity.Models.RandomRecipe

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class Step(
    val equipment: ArrayList<Equipment>?,
    val ingredients: ArrayList<Ingredient>?,
    val number: Int,
    val step: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Equipment),
        parcel.createTypedArrayList(Ingredient),
        parcel.readInt(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(equipment)
        parcel.writeTypedList(ingredients)
        parcel.writeInt(number)
        parcel.writeString(step)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Step> {
        override fun createFromParcel(parcel: Parcel): Step {
            return Step(parcel)
        }

        override fun newArray(size: Int): Array<Step?> {
            return arrayOfNulls(size)
        }
    }
}