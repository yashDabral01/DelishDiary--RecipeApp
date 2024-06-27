package com.example.homeactivity.Models.RandomRecipe

import android.os.Parcel
import android.os.Parcelable

data class Step(
    val equipment: ArrayList<Equipment>?,
    val ingredients: ArrayList<Ingredient>?,
    val number: Int,
    val step: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        equipment = parcel.createTypedArrayList(Equipment.CREATOR),
        ingredients = parcel.createTypedArrayList(Ingredient.CREATOR),
        number = parcel.readInt(),
        step = parcel.readString() ?: ""
    )

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
