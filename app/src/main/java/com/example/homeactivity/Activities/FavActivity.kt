package com.example.homeactivity.Activities

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeactivity.Adapter.AllRecipeAdapter
import com.example.homeactivity.Adapter.FavRecipeAdapter
import com.example.homeactivity.Database.RecipeApplication
import com.example.homeactivity.MarginItemDecoration
import com.example.homeactivity.R
import com.example.homeactivity.ViewModel.MainViewModel
import com.example.homeactivity.ViewModel.MainViewModelFactory
import com.example.homeactivity.databinding.ActivityFavBinding
import com.example.homeactivity.databinding.ActivityMainBinding
import com.example.homeactivity.databinding.FavrecipeViewholderBinding

class FavActivity : AppCompatActivity() {
    lateinit var mainViewModel : MainViewModel
    private lateinit var binding : ActivityFavBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFavRecipes()
        modifyStatusBar()

        binding.backButton.setOnClickListener{
            finish();
        }
    }

    private fun initFavRecipes(){
        val repository = (application as RecipeApplication).recipeRepository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]
        mainViewModel.allFavRecipes.observe(this) {
            binding.favView.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false)
            binding.favView.adapter = FavRecipeAdapter(it,repository)

            // adding divider to recycler view items
            binding.favView.apply {
                setHasFixedSize(true)
                adapter = binding.favView.adapter
                layoutManager = binding.favView.layoutManager
                addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))

            }
        }
    }
    private fun modifyStatusBar() {
        // Change status bar color
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        // Change status bar icon colors to grey
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}