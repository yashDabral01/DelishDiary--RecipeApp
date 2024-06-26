package com.example.homeactivity.Activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeactivity.Adapter.AllRecipeAdapter
import com.example.homeactivity.Adapter.PopularAdapter
import com.example.homeactivity.Api.RecipeService
import com.example.homeactivity.Api.RetrofitHelper
import com.example.homeactivity.R
import com.example.homeactivity.Repository.recipeRepository
import com.example.homeactivity.ViewModel.MainViewModel
import com.example.homeactivity.ViewModel.MainViewModelFactory
import com.example.homeactivity.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel : MainViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPopular()
        initAllRecipes()
    }

    private fun initPopular() {
        val recipeService = RetrofitHelper.getInstance().create(RecipeService::class.java)
        val repository = recipeRepository(recipeService)
         mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository))[MainViewModel::class.java]
         mainViewModel.randomRecipies.observe(this) {
            binding.viewPopular.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            binding.viewPopular.adapter = PopularAdapter(it)
        }

    }
    private fun initAllRecipes(){
        val recipeService = RetrofitHelper.getInstance().create(RecipeService::class.java)
        val repository = recipeRepository(recipeService)
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository))[MainViewModel::class.java]
        mainViewModel.allRecipies.observe(this) {
            binding.allRecipeView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            binding.allRecipeView.adapter = AllRecipeAdapter(it)
        }
    }
}