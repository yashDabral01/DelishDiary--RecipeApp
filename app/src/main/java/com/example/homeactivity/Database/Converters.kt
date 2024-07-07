package com.example.homeactivity.Database
import androidx.room.TypeConverter
import com.example.homeactivity.Model.Equipment
import com.example.homeactivity.Model.Ingredient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {

    @TypeConverter
    fun fromEquipmentList(value: ArrayList<Equipment>?): String? {
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<Equipment>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toEquipmentList(value: String?): ArrayList<Equipment>? {
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<Equipment>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromIngredientList(value: ArrayList<Ingredient>?): String? {
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<Ingredient>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toIngredientList(value: String?): ArrayList<Ingredient>? {
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<Ingredient>>() {}.type
        return gson.fromJson(value, type)
    }
}
