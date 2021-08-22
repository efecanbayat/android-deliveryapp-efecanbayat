package com.efecanbayat.deliveryapp.data.entity.foodadd

import com.google.gson.annotations.SerializedName

data class FoodAddRequest(
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("ingredients")
    val ingredients: List<String>
)
