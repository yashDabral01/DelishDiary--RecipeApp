package com.example.homeactivity.Api

import com.example.homeactivity.Models.RandomRecipe.RandomRecipiesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {

    @GET("recipes/random")
    suspend fun getRandomPopularRecipes(
        @Query("number") number: Int = 15,
        @Query("apiKey") apiKey: String = "e497eb9de4e14237968f46d97f06c1e6"
    ): Response<RandomRecipiesList>

    @GET("recipes/random")
    suspend fun getAllRecipes(
        @Query("number") number: Int = 100,
        @Query("apiKey") apiKey: String = "e497eb9de4e14237968f46d97f06c1e6"
    ): Response<RandomRecipiesList>
}