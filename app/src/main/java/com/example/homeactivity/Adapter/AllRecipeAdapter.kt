package com.example.homeactivity.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.homeactivity.Activities.DetailActivity
import com.example.homeactivity.Models.RandomRecipe.RandomRecipiesList
import com.example.homeactivity.databinding.AllrecipeViewholderBinding
import com.example.homeactivity.databinding.PopularViewholderBinding

class AllRecipeAdapter(val items : RandomRecipiesList) : RecyclerView.Adapter<AllRecipeAdapter.ViewHolder>() {
    private var context: Context? = null

    class ViewHolder(val binding: AllrecipeViewholderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = AllrecipeViewholderBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int = items.recipes.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AllRecipeAdapter.ViewHolder, position: Int) {
        holder.binding.recipeName.text = items.recipes[position].title.toString()
        holder.binding.readyIn.text =
            "Ready in ${items.recipes[position].readyInMinutes.toString()} mins"

        val requestOptions = RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context)
            .load(items.recipes[position].image)
            .apply(requestOptions)
            .into(holder.binding.recipeImg)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("object", items.recipes[position])
            holder.itemView.context.startActivity(intent)
        }
    }
}