package com.example.homeactivity.Activities

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeactivity.Adapter.AllRecipeAdapter
import com.example.homeactivity.Adapter.SuggestionAdapter
import com.example.homeactivity.Database.RecipeApplication
import com.example.homeactivity.Model.RandomRecipiesList
import com.example.homeactivity.Model.RecipeX
import com.example.homeactivity.R
import com.example.homeactivity.Repository.Response
import com.example.homeactivity.ViewModel.MainViewModel
import com.example.homeactivity.ViewModel.MainViewModelFactory
import com.example.homeactivity.databinding.ActivitySearchBinding
import java.util.Locale

class SearchActivity : AppCompatActivity() {
    lateinit var mainViewModel : MainViewModel
    private lateinit var binding: ActivitySearchBinding
    private  var filterSuggestionList: MutableList<RecipeX> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = (application as RecipeApplication).recipeRepository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        initList()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    filterList(newText)
                }
                return true
            }

        } )

        // changing the status bar
          modifyStatusBar()

        //configuring the back button
        binding.backButton.setOnClickListener {
            finish();
        }
    }
    private fun filterList(query:String?){
       if(query != null){
           val filteredList : MutableList<RecipeX> = mutableListOf()
           for(i in filterSuggestionList){
               if( i.title.lowercase(Locale.ROOT).contains(query)){
                       filteredList.add(i)
               }
           }
           if(filteredList.isEmpty()){
               Toast.makeText(this, "NO DATA IS FOUND !!", Toast.LENGTH_SHORT).show()
           }else{
               binding.searchResultsList.adapter = SuggestionAdapter(filteredList)
           }
       }
    }
    private fun initList() {

        mainViewModel.allRecipies.observe(this) {
            binding.searchResultsList.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL, false
            )
            binding.searchResultsList.adapter = AllRecipeAdapter(it)
           // adding divider to recycler view items
            binding.searchResultsList.apply {
                setHasFixedSize(true)
                adapter = binding.searchResultsList.adapter
                layoutManager = binding.searchResultsList.layoutManager
                addItemDecoration(
                    DividerItemDecoration(
                        this.context,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
            initSuggestionList(it)
        }
    }
    private fun initSuggestionList(randomRecipiesList: Response<RandomRecipiesList>){
       filterSuggestionList.addAll(randomRecipiesList.data!!.recipes)
    }
    private fun modifyStatusBar() {
        // Change status bar color
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        // Change status bar icon colors to grey
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

}
