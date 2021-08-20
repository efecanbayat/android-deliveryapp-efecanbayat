package com.efecanbayat.deliveryapp.data.entity.food

import com.google.gson.annotations.SerializedName

data class FoodResponse(
    @SerializedName("data")
    val data: Food,
    @SerializedName("success")
    val success: Boolean
)
