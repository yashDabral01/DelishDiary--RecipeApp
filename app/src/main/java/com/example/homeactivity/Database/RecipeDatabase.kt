package com.example.homeactivity.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.homeactivity.Model.Recipe

@Database(entities = [Recipe::class], version = 1)
@TypeConverters(Converters::class)
abstract class RecipeDatabase : RoomDatabase(){
   abstract fun recipeDao() : RecipeDao
   companion object{
      private var INSTANCE : RecipeDatabase? = null
      fun getDatabase(context: Context):RecipeDatabase{
         if(INSTANCE == null){
            synchronized(this){
               INSTANCE = Room.databaseBuilder(context,RecipeDatabase::class.java,"recipe_database")
                  .build()
            }
         }
         return INSTANCE!!
      }
   }
}