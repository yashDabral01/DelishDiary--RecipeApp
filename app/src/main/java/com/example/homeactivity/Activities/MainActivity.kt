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
import com.example.homeactivity.Database.RecipeApplication
import com.example.homeactivity.Network.NetworkUtils
import com.example.homeactivity.R
import com.example.homeactivity.Repository.Response
import com.example.homeactivity.ViewModel.MainViewModel
import com.example.homeactivity.ViewModel.MainViewModelFactory
import com.example.homeactivity.databinding.ActivityMainBinding
import com.facebook.shimmer.ShimmerFrameLayout

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel : MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel only once
        val repository = (application as RecipeApplication).recipeRepository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        // Initialize the ShimmerFrameLayout
        shimmerFrameLayout = binding.shimmerViewContainer

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


         mainViewModel.randomRecipies.observe(this) {
             when(it){
                 is Response.Loading -> {
                     // Show shimmer effect while data is loading
                     shimmerFrameLayout.startShimmer()
                     binding.viewPopular.visibility = View.GONE
                     shimmerFrameLayout.visibility = View.VISIBLE
                 }
                 is Response.Success -> {
                     binding.viewPopular.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
                     binding.viewPopular.adapter = PopularAdapter(it)
                     // Hide shimmer effect and show data
                     shimmerFrameLayout.stopShimmer()
                     shimmerFrameLayout.visibility = View.GONE
                     binding.viewPopular.visibility = View.VISIBLE
                 }
                 is Response.Error -> {
                     Toast.makeText(this, "Some error occurred!!", Toast.LENGTH_SHORT).show()
                 }
             }

        }

    }
    private fun initAllRecipes(){

        mainViewModel.allRecipies.observe(this) {

            when(it){
                is Response.Loading -> {
                    // Show shimmer effect while data is loading
                    shimmerFrameLayout.startShimmer()
                    binding.allRecipeView.visibility = View.GONE
                    shimmerFrameLayout.visibility = View.VISIBLE
                }
                is Response.Success ->{
                    binding.allRecipeView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
                    binding.allRecipeView.adapter = AllRecipeAdapter(it)

                    // Hide shimmer effect and show data
                    shimmerFrameLayout.stopShimmer()
                    shimmerFrameLayout.visibility = View.GONE
                    binding.allRecipeView.visibility = View.VISIBLE


                    // adding divider to recycler view items
                    binding.allRecipeView.apply {
                        setHasFixedSize(true)
                        adapter = binding.allRecipeView.adapter
                        layoutManager = binding.allRecipeView.layoutManager
                        addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))

                    }
                }
                is Response.Error ->{
                    Toast.makeText(this, "Some error occurred!!", Toast.LENGTH_SHORT).show()
                }
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