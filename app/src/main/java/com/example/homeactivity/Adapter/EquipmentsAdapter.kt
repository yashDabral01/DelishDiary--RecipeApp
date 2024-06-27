package com.example.homeactivity.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.homeactivity.Models.RandomRecipe.Equipment
import com.example.homeactivity.Models.RandomRecipe.Ingredient
import com.example.homeactivity.databinding.EquipmentsViewholderBinding
import com.example.homeactivity.databinding.IngredientsViewholderBinding
import com.example.homeactivity.databinding.PopularViewholderBinding

class EquipmentsAdapter(private val equipmentsList: ArrayList<Equipment>) : RecyclerView.Adapter<EquipmentsAdapter.ViewHolder>(){
    class ViewHolder(val binding: EquipmentsViewholderBinding):RecyclerView.ViewHolder(binding.root)
    private var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = EquipmentsViewholderBinding.inflate(LayoutInflater.from(context), parent, false)
        return EquipmentsAdapter.ViewHolder(binding)
    }

    override fun getItemCount(): Int = equipmentsList.size

    override fun onBindViewHolder(holder: EquipmentsAdapter.ViewHolder, position: Int) {
        holder.binding.roundTitleText.text = equipmentsList[position].name.toString()
        val requestOptions = RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context)
            .load(equipmentsList[position].image)
            .apply(requestOptions)
            .into(holder.binding.roundImg)
    }
}