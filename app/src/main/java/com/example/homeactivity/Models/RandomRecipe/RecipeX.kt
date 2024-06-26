package com.example.homeactivity.Models.RandomRecipe

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class RecipeX(
    val aggregateLikes: Int,
    val analyzedInstructions: List<AnalyzedInstruction>,
    val cheap: Boolean,
    val cookingMinutes: Any,
    val creditsText: String,
    val cuisines: ArrayList<String>?,
    val dairyFree: Boolean,
    val diets: ArrayList<String>?,
    val dishTypes: ArrayList<String>?,
    val gaps: String,
    val glutenFree: Boolean,
    val healthScore: Int,
    val id: Int,
    val image: String,
    val imageType: String,
    val instructions: String,
    val license: String,
    val lowFodmap: Boolean,
    val occasions: ArrayList<String>?,
    val originalId: Any,
    val preparationMinutes: Any,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String,
    val sourceUrl: String,
    val spoonacularScore: Double,
    val spoonacularSourceUrl: String,
    val summary: String,
    val sustainable: Boolean,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
    val veryPopular: Boolean,
    val weightWatcherSmartPoints: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        TODO("analyzedInstructions"),
        parcel.readByte() != 0.toByte(),
        TODO("cookingMinutes"),
        parcel.readString().toString(),
        parcel.createStringArrayList(),
        parcel.readByte() != 0.toByte(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte(),
        parcel.createStringArrayList(),
        TODO("originalId"),
        TODO("preparationMinutes"),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(aggregateLikes)
        parcel.writeByte(if (cheap) 1 else 0)
        parcel.writeString(creditsText)
        parcel.writeStringList(cuisines)
        parcel.writeByte(if (dairyFree) 1 else 0)
        parcel.writeStringList(diets)
        parcel.writeStringList(dishTypes)
        parcel.writeString(gaps)
        parcel.writeByte(if (glutenFree) 1 else 0)
        parcel.writeInt(healthScore)
        parcel.writeInt(id)
        parcel.writeString(image)
        parcel.writeString(imageType)
        parcel.writeString(instructions)
        parcel.writeString(license)
        parcel.writeByte(if (lowFodmap) 1 else 0)
        parcel.writeStringList(occasions)
        parcel.writeDouble(pricePerServing)
        parcel.writeInt(readyInMinutes)
        parcel.writeInt(servings)
        parcel.writeString(sourceName)
        parcel.writeString(sourceUrl)
        parcel.writeDouble(spoonacularScore)
        parcel.writeString(spoonacularSourceUrl)
        parcel.writeString(summary)
        parcel.writeByte(if (sustainable) 1 else 0)
        parcel.writeString(title)
        parcel.writeByte(if (vegan) 1 else 0)
        parcel.writeByte(if (vegetarian) 1 else 0)
        parcel.writeByte(if (veryHealthy) 1 else 0)
        parcel.writeByte(if (veryPopular) 1 else 0)
        parcel.writeInt(weightWatcherSmartPoints)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipeX> {
        override fun createFromParcel(parcel: Parcel): RecipeX {
            return RecipeX(parcel)
        }

        override fun newArray(size: Int): Array<RecipeX?> {
            return arrayOfNulls(size)
        }
    }
}