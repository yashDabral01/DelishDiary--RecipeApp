package com.example.homeactivity.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.homeactivity.Activities.DetailActivity
import com.example.homeactivity.Model.RandomRecipiesList
import com.example.homeactivity.Repository.Response
import com.example.homeactivity.databinding.PopularViewholderBinding

class PopularAdapter(val items: Response<RandomRecipiesList>) : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {
    private var context: Context? = null

    class ViewHolder(val binding: PopularViewholderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = PopularViewholderBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.data!!.recipes.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PopularAdapter.ViewHolder, position: Int) {
        holder.binding.recipeName.text = items.data!!.recipes[position].title.toString()
        holder.binding.readyIn.text = "Ready in ${items.data.recipes[position].readyInMinutes.toString()} mins"

        val requestOptions = RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context)
            .load(items.data.recipes[position].image)
            .apply(requestOptions)
            .into(holder.binding.randomRecipeImg)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("object",items.data.recipes[position])
            intent.putExtra("source","PopularAdapter")
            holder.itemView.context.startActivity(intent)
        }
    }
}