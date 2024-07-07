package com.example.homeactivity.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.homeactivity.Model.Recipe

@Dao
interface RecipeDao {
    @Insert
    suspend fun addRecipe(recipe : Recipe)

    @Query("Select * from Recipe")
    fun getAllFavRecipes():LiveData<List<Recipe>>

    @Query("DELETE FROM Recipe WHERE id = :recipeId")
    suspend fun deleteRecipe(recipeId: Int)
}