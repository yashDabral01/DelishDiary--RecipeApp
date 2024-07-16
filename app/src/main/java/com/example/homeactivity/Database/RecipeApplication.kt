package com.example.homeactivity.Database

import android.app.Application
import com.example.homeactivity.Api.RecipeService
import com.example.homeactivity.Api.RetrofitHelper
import com.example.homeactivity.Repository.recipeRepository
import com.google.firebase.FirebaseApp

class RecipeApplication:Application() {
    lateinit var recipeRepository: recipeRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
        FirebaseApp.initializeApp(this)
    }

    private fun initialize() {
        val recipeService = RetrofitHelper.getInstance().create(RecipeService::class.java)
        val database = RecipeDatabase.getDatabase(applicationContext)
        recipeRepository = recipeRepository(recipeService,database)
    }
}