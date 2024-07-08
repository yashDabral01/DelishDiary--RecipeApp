package com.example.homeactivity.Activities

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.homeactivity.Adapter.EquipmentsAdapter
import com.example.homeactivity.Adapter.IngredientsAdapter
import com.example.homeactivity.Database.RecipeApplication
import com.example.homeactivity.Model.Equipment
import com.example.homeactivity.Model.Ingredient
import com.example.homeactivity.Model.Recipe
import com.example.homeactivity.Model.RecipeX
import com.example.homeactivity.R
import com.example.homeactivity.ViewModel.MainViewModel
import com.example.homeactivity.databinding.ActivityDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item : RecipeX
    private lateinit var Favitem : Recipe
    lateinit var mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

      //  modifyStatusBar()

        // handling the case when different object can come from different classes
        val source = intent.getStringExtra("source")
        when (source) {
            "FavAdapter" -> {
                initFavDetails()
                initEquipmentsForFavList()
                initIngredientsForFavList()
            }
            else -> {
                initDetails()
                initIngredients()
                initEquipments()

                // on click favbutton item is added to database as favourite item
                binding.favButton.setOnClickListener {
                    val scope = CoroutineScope(Dispatchers.Main)
                    scope.launch {
                        // Call your suspend function here
                        initFavButton()
                    }
                }
            }
        }
    }
    private fun initDetails(){

        item = intent.getParcelableExtra("object")!!
        binding.readyIn.text = item.readyInMinutes.toString()
        binding.servingsText.text = item.servings.toString()
        binding.pricePerServingText.text = item.pricePerServing.toString()
        binding.instructionsText.text = item.instructions.stripHtmlTags()
        binding.QuickSummaryText.text = item.summary.stripHtmlTags()

        val requestOptions = RequestOptions().placeholder(R.drawable.foodimg1)
        Glide.with(this)
            .load(item.image)
            .apply(requestOptions)
            .into(binding.recipeImg)

    }

    // This function is made for the purpose that it is called if the data is coming from FavRecipeAdapter
    private fun initFavDetails(){
        Favitem = intent.getParcelableExtra("object")!!
        binding.readyIn.text = Favitem.readyInMinutes.toString()
        binding.servingsText.text = Favitem.servings.toString()
        binding.pricePerServingText.text = Favitem.pricePerServing.toString()
        binding.instructionsText.text = Favitem.instructions.stripHtmlTags()
        binding.QuickSummaryText.text = Favitem.summary.stripHtmlTags()

        val requestOptions = RequestOptions().placeholder(R.drawable.foodimg1)
        Glide.with(this)
            .load(Favitem.image)
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

    private suspend fun initFavButton(){
        item = intent.getParcelableExtra("object")!!
        val recipe = Recipe(
            id = item.id, image = item.image,
            readyInMinutes = item.readyInMinutes,
            servings = item.servings,
            title = item.title,
            summary = item.summary,
            instructions = item.instructions,
            pricePerServing = item.pricePerServing,
            equipment = generateArrayOfEquipments(),
            ingredients = generateArrayOfIngredients() )

        val repository = (application as RecipeApplication).recipeRepository
        repository.addRecipe(recipe)
        // Update UI on the main thread
        withContext(Dispatchers.Main) {
            Toast.makeText(this@DetailActivity, "Recipe is added to favourite", Toast.LENGTH_SHORT).show()
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
    private fun initIngredientsForFavList(){
        Favitem = intent.getParcelableExtra("object")!!
        val ingredientsList : ArrayList<Ingredient> = Favitem.ingredients
        binding.viewIngredients.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,false)
        binding.viewIngredients.adapter = IngredientsAdapter(ingredientsList)
    }
    private fun initEquipmentsForFavList(){
        Favitem = intent.getParcelableExtra("object")!!
        val equipmentList : ArrayList<Equipment>? = Favitem.equipment
        binding.viewEquipments.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,false)
        binding.viewEquipments.adapter = equipmentList?.let { EquipmentsAdapter(it) }
    }

    // function to remove html tags from the response
    fun String.stripHtmlTags(): String {
        return this.replace(Regex("<.*?>"), "")
    }
}