package com.example.homeactivity.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homeactivity.Api.RecipeService
import com.example.homeactivity.Api.RetrofitHelper
import com.example.homeactivity.Models.RandomRecipe.RandomRecipiesList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class recipeRepository(val recipeService:RecipeService) {
    private val allRecipesLiveData = MutableLiveData<RandomRecipiesList>()
    private val randomRecipesLiveData = MutableLiveData<RandomRecipiesList>()

    val allRecipes : LiveData<RandomRecipiesList>
        get() = allRecipesLiveData

    val randomRecipes : LiveData<RandomRecipiesList>
    get() = randomRecipesLiveData

    suspend fun getRandomRecipes(){
        val result = recipeService.getRandomPopularRecipes()
        if(result?.body() != null){
             randomRecipesLiveData.postValue(result.body())
        }
    }
    suspend fun getAllRecipes(){
        val result = recipeService.getAllRecipes()
        if(result?.body() != null){
            allRecipesLiveData.postValue(result.body())
        }
    }

    }