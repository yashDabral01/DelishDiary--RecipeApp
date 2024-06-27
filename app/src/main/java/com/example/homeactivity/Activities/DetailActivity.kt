package com.example.homeactivity.Activities

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.homeactivity.Adapter.AllRecipeAdapter
import com.example.homeactivity.Adapter.EquipmentsAdapter
import com.example.homeactivity.Adapter.IngredientsAdapter
import com.example.homeactivity.Models.RandomRecipe.Equipment
import com.example.homeactivity.Models.RandomRecipe.Ingredient
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
        modifyStatusBar()
        initDetails()
        initIngredients()
        initEquipments()
        binding.favButton.setOnClickListener{
             // on click cache the
        }
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
    private fun initIngredients(){
        val ingredientsList : ArrayList<Ingredient> = generateArrayOfIngredients()
        binding.viewIngredients.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,false)
        binding.viewIngredients.adapter = IngredientsAdapter(ingredientsList)
    }
    private fun initEquipments(){
        val equipmentList : ArrayList<Equipment> = generateArrayOfEquipments()
        binding.viewEquipments.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,false)
        binding.viewEquipments.adapter = EquipmentsAdapter(equipmentList)
    }
    private fun generateArrayOfIngredients(): ArrayList<Ingredient> {
        val ingredientsList = ArrayList<Ingredient>()
         item = intent.getParcelableExtra("object")!!

        item.analyzedInstructions.forEach { analyzedInstruction ->
            analyzedInstruction.steps.forEach { step ->
                step.ingredients?.let { ingredientsList.addAll(it) }
            }
        }
        return ingredientsList
    }
    private fun generateArrayOfEquipments(): ArrayList<Equipment> {
        val equipmentsList = ArrayList<Equipment>()
        item = intent.getParcelableExtra("object")!!

        item.analyzedInstructions.forEach { analyzedInstruction ->
            analyzedInstruction.steps.forEach { step ->
                step.equipment?.let { equipmentsList.addAll(it) }
            }
        }
        return equipmentsList
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