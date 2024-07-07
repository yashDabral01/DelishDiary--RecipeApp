package com.example.homeactivity.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.homeactivity.Activities.DetailActivity
import com.example.homeactivity.Model.RecipeX
import com.example.homeactivity.databinding.AllrecipeViewholderBinding

class SuggestionAdapter(val items : MutableList<RecipeX>) : RecyclerView.Adapter<SuggestionAdapter.ViewHolder>() {
    private var context: Context? = null

    class ViewHolder(val binding: AllrecipeViewholderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding =
            AllrecipeViewholderBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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
            intent.putExtra("source","SuggestionAdapter")
            holder.itemView.context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int = items.size
}