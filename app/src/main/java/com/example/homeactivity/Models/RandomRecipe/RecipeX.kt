package com.example.homeactivity.Models.RandomRecipe

import android.os.Parcel
import android.os.Parcelable

data class RecipeX(
    val aggregateLikes: Int,
    val analyzedInstructions: List<AnalyzedInstruction>,
    val cheap: Boolean,
    val cookingMinutes: Any?,
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
    val originalId: Any?,
    val preparationMinutes: Any?,
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
        aggregateLikes = parcel.readInt(),
        analyzedInstructions = parcel.createTypedArrayList(AnalyzedInstruction.CREATOR) ?: emptyList(),
        cheap = parcel.readByte() != 0.toByte(),
        cookingMinutes = parcel.readValue(Any::class.java.classLoader),
        creditsText = parcel.readString() ?: "",
        cuisines = parcel.createStringArrayList(),
        dairyFree = parcel.readByte() != 0.toByte(),
        diets = parcel.createStringArrayList(),
        dishTypes = parcel.createStringArrayList(),
        gaps = parcel.readString() ?: "",
        glutenFree = parcel.readByte() != 0.toByte(),
        healthScore = parcel.readInt(),
        id = parcel.readInt(),
        image = parcel.readString() ?: "",
        imageType = parcel.readString() ?: "",
        instructions = parcel.readString() ?: "",
        license = parcel.readString() ?: "",
        lowFodmap = parcel.readByte() != 0.toByte(),
        occasions = parcel.createStringArrayList(),
        originalId = parcel.readValue(Any::class.java.classLoader),
        preparationMinutes = parcel.readValue(Any::class.java.classLoader),
        pricePerServing = parcel.readDouble(),
        readyInMinutes = parcel.readInt(),
        servings = parcel.readInt(),
        sourceName = parcel.readString() ?: "",
        sourceUrl = parcel.readString() ?: "",
        spoonacularScore = parcel.readDouble(),
        spoonacularSourceUrl = parcel.readString() ?: "",
        summary = parcel.readString() ?: "",
        sustainable = parcel.readByte() != 0.toByte(),
        title = parcel.readString() ?: "",
        vegan = parcel.readByte() != 0.toByte(),
        vegetarian = parcel.readByte() != 0.toByte(),
        veryHealthy = parcel.readByte() != 0.toByte(),
        veryPopular = parcel.readByte() != 0.toByte(),
        weightWatcherSmartPoints = parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(aggregateLikes)
        parcel.writeTypedList(analyzedInstructions)
        parcel.writeByte(if (cheap) 1 else 0)
        parcel.writeValue(cookingMinutes)
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
        parcel.writeValue(originalId)
        parcel.writeValue(preparationMinutes)
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
