package com.efecanbayat.deliveryapp.ui.foodDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.efecanbayat.deliveryapp.databinding.ItemIngredientBinding

class IngredientsAdapter: RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {

    private var list = ArrayList<String>()

    inner class IngredientsViewHolder(val binding: ItemIngredientBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val binding = ItemIngredientBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return IngredientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val item = list[position]

        holder.binding.ingredientTextView.text = item
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setIngredientList(list: List<String>){
        this.list = ArrayList(list)
        notifyDataSetChanged()
    }
}