package com.efecanbayat.deliveryapp.ui.restaurantdetail

import com.efecanbayat.deliveryapp.data.entity.food.Food

interface IFoodOnClickListener {
    fun onClick(food: Food)
}