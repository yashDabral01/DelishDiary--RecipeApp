package com.example.homeactivity.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.homeactivity.Models.RandomRecipe.Ingredient
import com.example.homeactivity.databinding.IngredientsViewholderBinding
import com.example.homeactivity.databinding.PopularViewholderBinding

class IngredientsAdapter(private val ingredientsList: ArrayList<Ingredient>) : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>(){
    class ViewHolder(val binding: IngredientsViewholderBinding):RecyclerView.ViewHolder(binding.root)
    private var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = IngredientsViewholderBinding.inflate(LayoutInflater.from(context), parent, false)
        return IngredientsAdapter.ViewHolder(binding)
    }

    override fun getItemCount(): Int = ingredientsList.size

    override fun onBindViewHolder(holder: IngredientsAdapter.ViewHolder, position: Int) {
          holder.binding.roundTitleText.text = ingredientsList[position].name.toString()
         val requestOptions = RequestOptions().transform(CenterCrop())
          Glide.with(holder.itemView.context)
            .load(ingredientsList[position].image)
            .apply(requestOptions)
            .into(holder.binding.roundImg)
    }
}