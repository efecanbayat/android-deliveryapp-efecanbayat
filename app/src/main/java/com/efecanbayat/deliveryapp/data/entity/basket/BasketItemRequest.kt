package com.efecanbayat.deliveryapp.data.entity.basket

import com.google.gson.annotations.SerializedName

data class BasketItemRequest(
    @SerializedName("restaurantId")
    val restaurantId: String,
    @SerializedName("mealIds")
    val mealsId: ArrayList<String>
)