package com.example.homeactivity.Activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
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

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel : MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var suggestionsAdapter: ArrayAdapter<String>
    private val suggestions = listOf("Recipe 1", "Recipe 2", "Recipe 3") // Example suggestions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        modifyStatusBar()
        initPopular()
        initAllRecipes()
        initSearch()

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
    private fun initSearch() {

        Log.d("HomeActivity", "Initializing search")

        suggestionsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, suggestions)
        binding.searchSuggestionsList.adapter = suggestionsAdapter

        binding.searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
                // Clear focus to prevent continuous triggering
                binding.searchView.clearFocus()
            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle query submission
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    binding.searchSuggestionsList.visibility = android.view.View.GONE
                } else {
                    binding.searchSuggestionsList.visibility = android.view.View.VISIBLE
                    suggestionsAdapter.filter.filter(newText)
                }
                return true
            }
        })

        binding.searchSuggestionsList.setOnItemClickListener { _, _, position, _ ->
            val selectedSuggestion = suggestionsAdapter.getItem(position)
            // Handle suggestion click
        }
        }
    private fun modifyStatusBar() {
           // Change status bar color
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
               window.statusBarColor = ContextCompat.getColor(this, R.color.white)
           }

           // Change status bar icon colors to grey
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
               window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
           }
       }

    }