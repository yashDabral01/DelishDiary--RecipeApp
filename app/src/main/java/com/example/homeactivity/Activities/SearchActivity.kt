package com.example.homeactivity.Activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.example.homeactivity.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var resultsAdapter: ArrayAdapter<String>
    private val recipes = listOf("Recipe 1", "Recipe 2", "Recipe 3") // Example recipes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resultsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, recipes)
        binding.searchResultsList.adapter = resultsAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle query submission
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                resultsAdapter.filter.filter(newText)
                return true
            }
        })

        binding.searchResultsList.setOnItemClickListener { _, _, position, _ ->
            val selectedRecipe = resultsAdapter.getItem(position)
            // Handle recipe click (e.g., mark as favourite)
        }
    }
}
