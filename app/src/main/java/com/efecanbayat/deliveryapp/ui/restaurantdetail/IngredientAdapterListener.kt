package com.efecanbayat.deliveryapp.ui.restaurantdetail

import com.efecanbayat.deliveryapp.data.entity.Ingredient

interface IngredientAdapterListener {
    fun onClick(ingredient: Ingredient, position: Int)
}