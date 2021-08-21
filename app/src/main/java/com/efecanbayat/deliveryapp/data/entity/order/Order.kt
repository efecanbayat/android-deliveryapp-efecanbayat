package com.efecanbayat.deliveryapp.data.entity.order

import com.efecanbayat.deliveryapp.data.entity.food.Food
import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("createdDate")
    val createdDate: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("meals")
    val meals: List<Food>,
    @SerializedName("restaurant")
    val restaurant: OrderRestaurant,
    @SerializedName("user")
    val user: String
)
