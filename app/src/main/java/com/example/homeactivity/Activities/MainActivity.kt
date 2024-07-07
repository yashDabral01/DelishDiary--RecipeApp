package com.example.homeactivity.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeactivity.Adapter.AllRecipeAdapter
import com.example.homeactivity.Adapter.PopularAdapter
import com.example.homeactivity.Api.RecipeService
import com.example.homeactivity.Api.RetrofitHelper
import com.example.homeactivity.Database.RecipeApplication
import com.example.homeactivity.MarginItemDecoration
import com.example.homeactivity.Network.NetworkUtils
import com.example.homeactivity.R
import com.example.homeactivity.Repository.recipeRepository
import com.example.homeactivity.ViewModel.MainViewModel
import com.example.homeactivity.ViewModel.MainViewModelFactory
import com.example.homeactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel : MainViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //check for network connection
        if (NetworkUtils.isInternetAvailable(this)) {
            Toast.makeText(this, "You are online", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No internet connection. Showing offline data.", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, FavActivity::class.java)
//            startActivity(intent)
        }
        // Initialization for views
        modifyStatusBar()
        initPopular()
        initAllRecipes()
        initSearch()
        initBottomNav()
    }

    private fun initPopular() {
        val repository = (application as RecipeApplication).recipeRepository
         mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository))[MainViewModel::class.java]
         mainViewModel.randomRecipies.observe(this) {
            binding.viewPopular.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            binding.viewPopular.adapter = PopularAdapter(it)

        }

    }
    private fun initAllRecipes(){
        val repository = (application as RecipeApplication).recipeRepository
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository))[MainViewModel::class.java]
        mainViewModel.allRecipies.observe(this) {
            binding.allRecipeView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            binding.allRecipeView.adapter = AllRecipeAdapter(it)
            // adding divider to recycler view items
            binding.allRecipeView.apply {
                setHasFixedSize(true)
                adapter = binding.allRecipeView.adapter
                layoutManager = binding.allRecipeView.layoutManager
                addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
                // adding margin to recycler view items
                 binding.allRecipeView.addItemDecoration(
                   MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin))
                )
            }
        }
    }
    private fun initSearch() {
        binding.searchView.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }
    private fun modifyStatusBar() {
        // Change status bar color
       window.statusBarColor = ContextCompat.getColor(this, R.color.white)

       // Change status bar icon colors to grey
       window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
   }
    private fun initBottomNav() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home-> {
                    // Handle navigation to the Home screen
                    Toast.makeText(this, "Home selected", Toast.LENGTH_SHORT).show()
                    // Replace the current fragment/activity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.favourite -> {
                    // Handle navigation to the Favourite screen
                    Toast.makeText(this, "Search selected", Toast.LENGTH_SHORT).show()
                    // Replace the current fragment/activity
                    val intent = Intent(this, FavActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> {false}
            }
        }
    }
}