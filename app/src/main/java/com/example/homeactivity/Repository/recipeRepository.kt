package com.example.homeactivity.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homeactivity.Api.RecipeService
import com.example.homeactivity.Database.RecipeDatabase
import com.example.homeactivity.Model.RandomRecipiesList
import com.example.homeactivity.Model.Recipe

class recipeRepository(private val recipeService:RecipeService, private val recipeDatabase: RecipeDatabase) {
    private val allRecipesLiveData = MutableLiveData<RandomRecipiesList>()
    private val randomRecipesLiveData = MutableLiveData<RandomRecipiesList>()

    val allRecipes: LiveData<RandomRecipiesList>
        get() = allRecipesLiveData

    val randomRecipes: LiveData<RandomRecipiesList>
        get() = randomRecipesLiveData

    val favRecipesList : LiveData<List<Recipe>> = recipeDatabase.recipeDao().getAllFavRecipes()


    suspend fun getRandomRecipes() {
        val result = recipeService.getRandomPopularRecipes()
        if (result.body() != null) {
            randomRecipesLiveData.postValue(result.body())
        }
    }

    suspend fun getAllRecipes() {
        val result = recipeService.getAllRecipes()
        if (result.body() != null) {
            allRecipesLiveData.postValue(result.body())
        }
    }
    suspend fun addRecipe(recipe: Recipe){
        recipeDatabase.recipeDao().addRecipe(recipe)
    }
    suspend fun deleteRecipe(recipeID : Int){
        recipeDatabase.recipeDao().deleteRecipe(recipeID)
    }

}