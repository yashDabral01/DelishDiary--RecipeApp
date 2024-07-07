package com.example.homeactivity.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeactivity.Model.RandomRecipiesList
import com.example.homeactivity.Model.Recipe
import com.example.homeactivity.Repository.recipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(private val recipeRepository: recipeRepository) : ViewModel() {

     lateinit var randomRecipies : LiveData<RandomRecipiesList>
     lateinit var allRecipies : LiveData<RandomRecipiesList>
     lateinit var allFavRecipes : LiveData<List<Recipe>>
    init{
        viewModelScope.launch (Dispatchers.IO) {
            recipeRepository.getRandomRecipes()
            recipeRepository.getAllRecipes()
        }
        randomRecipies  = recipeRepository.randomRecipes
        allRecipies = recipeRepository.allRecipes
        allFavRecipes = recipeRepository.favRecipesList
    }

}