package com.example.homeactivity.Model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Recipe")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val recipeID: Int = 0,
    val id: Int,
    var image: String,
    val readyInMinutes: Int,
    val servings: Int,
    val title: String,
    val summary: String,
    val instructions: String,
    val pricePerServing: Double,
    val equipment: ArrayList<Equipment>? = null,
    val ingredients: ArrayList<Ingredient>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        recipeID = parcel.readInt(),
        id = parcel.readInt(),
        image = parcel.readString()!!,
        readyInMinutes = parcel.readInt(),
        servings = parcel.readInt(),
        title = parcel.readString()!!,
        summary = parcel.readString()!!,
        instructions = parcel.readString()!!,
        pricePerServing = parcel.readDouble(),
        equipment = parcel.readArrayList(Equipment::class.java.classLoader) as? ArrayList<Equipment>,
        ingredients = parcel.readArrayList(Ingredient::class.java.classLoader) as ArrayList<Ingredient>
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(recipeID)
        parcel.writeInt(id)
        parcel.writeString(image)
        parcel.writeInt(readyInMinutes)
        parcel.writeInt(servings)
        parcel.writeString(title)
        parcel.writeString(summary)
        parcel.writeString(instructions)
        parcel.writeDouble(pricePerServing)
        parcel.writeList(equipment)
        parcel.writeList(ingredients)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }
}
