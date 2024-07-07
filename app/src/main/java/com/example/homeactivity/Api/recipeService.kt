package com.example.homeactivity.Api

import com.example.homeactivity.Model.RandomRecipiesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {

    @GET("recipes/random")
    suspend fun getRandomPopularRecipes(
        @Query("number") number: Int = 15,
        @Query("apiKey") apiKey: String = "fb919adff6094d3aaaa36bbd4eff467f"
    ): Response<RandomRecipiesList>

    @GET("recipes/random")
    suspend fun getAllRecipes(
        @Query("number") number: Int = 250,
        @Query("apiKey") apiKey: String = "fb919adff6094d3aaaa36bbd4eff467f"
    ): Response<RandomRecipiesList>

}