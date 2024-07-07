package com.example.homeactivity.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.homeactivity.Activities.DetailActivity
import com.example.homeactivity.Activities.FavActivity
import com.example.homeactivity.Database.RecipeApplication
import com.example.homeactivity.Model.Recipe
import com.example.homeactivity.Repository.recipeRepository
import com.example.homeactivity.databinding.AllrecipeViewholderBinding
import com.example.homeactivity.databinding.FavrecipeViewholderBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavRecipeAdapter(private val items : List<Recipe>, private val repository:recipeRepository) : RecyclerView.Adapter<FavRecipeAdapter.ViewHolder>(){
    private var context: Context? = null

    class ViewHolder(val binding: FavrecipeViewholderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavRecipeAdapter.ViewHolder {
        context = parent.context
        val binding =
            FavrecipeViewholderBinding.inflate(LayoutInflater.from(context), parent, false)
        return FavRecipeAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavRecipeAdapter.ViewHolder, position: Int) {
        holder.binding.recipeName.text = items[position].title.toString()
        holder.binding.readyIn.text =
            "Ready in ${items[position].readyInMinutes.toString()} mins"

        val requestOptions = RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context)
            .load(items[position].image)
            .apply(requestOptions)
            .into(holder.binding.recipeImg)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("object", items[position])
            intent.putExtra("source","FavAdapter")
            holder.itemView.context.startActivity(intent)
        }

        // On long press show an alert box to delete the item
        holder.itemView.setOnLongClickListener {
            showDeleteDialog(holder.itemView.context, items[position])
            true
        }

    }

    override fun getItemCount(): Int = items.size
    private fun showDeleteDialog(context: Context, recipe: Recipe) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Delete Recipe")
        builder.setMessage("Are you sure you want to delete this recipe?")
        builder.setPositiveButton("Yes") { dialog, _ ->
            CoroutineScope(Dispatchers.IO).launch {
                repository.deleteRecipe(recipe.id)
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Recipe deleted", Toast.LENGTH_SHORT).show()
                }
            }
            dialog.dismiss()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
}