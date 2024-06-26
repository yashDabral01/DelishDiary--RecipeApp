package com.example.homeactivity.Activities

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.homeactivity.Models.RandomRecipe.RandomRecipiesList
import com.example.homeactivity.Models.RandomRecipe.RecipeX
import com.example.homeactivity.R
import com.example.homeactivity.databinding.ActivityDetailBinding
import com.example.homeactivity.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item : RecipeX

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDetails()
    }
    private fun initDetails(){
        item = intent.getParcelableExtra("object")!!
        binding.readyIn.text = item.readyInMinutes.toString()
        binding.servingsText.text = item.servings.toString()
        binding.pricePerServingText.text = item.pricePerServing.toString()
        binding.instructionsText.text = item.instructions
        binding.QuickSummaryText.text = item.summary

        val requestOptions = RequestOptions().placeholder(R.drawable.foodimg1)
        Glide.with(this)
            .load(item.image)
            .apply(requestOptions)
            .into(binding.recipeImg)
    }

}