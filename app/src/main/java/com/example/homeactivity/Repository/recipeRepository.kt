package com.example.homeactivity.Repository

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homeactivity.Api.RecipeService
import com.example.homeactivity.Database.RecipeDatabase
import com.example.homeactivity.Model.RandomRecipiesList
import com.example.homeactivity.Model.Recipe

class recipeRepository(private val recipeService:RecipeService, private val recipeDatabase: RecipeDatabase) {
    private val allRecipesLiveData = MutableLiveData<Response<RandomRecipiesList>>()
    private val randomRecipesLiveData = MutableLiveData<Response<RandomRecipiesList>>()

    val allRecipes: LiveData<Response<RandomRecipiesList>>
        get() = allRecipesLiveData

    val randomRecipes: LiveData<Response<RandomRecipiesList>>
        get() = randomRecipesLiveData

    val favRecipesList : LiveData<List<Recipe>> = recipeDatabase.recipeDao().getAllFavRecipes()


    suspend fun getRandomRecipes() {
        try {
            val result = recipeService.getRandomPopularRecipes()
            if (result.body() != null) {
                randomRecipesLiveData.postValue(Response.Success(result.body()))
            }
        }catch (e:Exception){
            randomRecipesLiveData.postValue(Response.Error(e.message.toString()))
        }
    }

    suspend fun getAllRecipes() {
        try {
            val result = recipeService.getAllRecipes()
            if (result.body() != null) {
                allRecipesLiveData.postValue(Response.Success(result.body()))
            }
        }catch (e:Exception){
            allRecipesLiveData.postValue(Response.Error(e.message.toString()))
        }
    }
    suspend fun addRecipe(recipe: Recipe){
        try{
            recipeDatabase.recipeDao().addRecipe(recipe)
        }catch (e:Exception){
            // code to display the error
        }
    }
    suspend fun deleteRecipe(recipeID : Int){
        try {
            recipeDatabase.recipeDao().deleteRecipe(recipeID)
        }catch (e:Exception){
            // code to display the error
        }
    }
}