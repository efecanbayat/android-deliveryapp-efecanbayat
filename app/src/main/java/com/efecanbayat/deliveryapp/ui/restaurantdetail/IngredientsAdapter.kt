package com.efecanbayat.deliveryapp.ui.restaurantdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.efecanbayat.deliveryapp.data.entity.Ingredient
import com.efecanbayat.deliveryapp.databinding.ItemIngredientCardBinding

class IngredientsAdapter(private val ingredientList: ArrayList<Ingredient>): RecyclerView.Adapter<IngredientsAdapter.IngredientAdapter>() {

    private var listener: IngredientAdapterListener? = null

    inner class IngredientAdapter(val binding: ItemIngredientCardBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindItems(
            ingredient: Ingredient,
            position: Int,
            listener: IngredientAdapterListener?
        ) {
            binding.ingredientNameTextView.text = ingredient.ingredient
            binding.ingredientCardView.setOnClickListener {
                listener?.onClick(ingredient, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientAdapter {
        val binding = ItemIngredientCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return IngredientAdapter(binding)
    }

    override fun onBindViewHolder(holder: IngredientAdapter, position: Int) {
        holder.bindItems(ingredientList[position],position,listener)
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    fun addListener(listener: IngredientAdapterListener) {
        this.listener = listener
    }
}