package com.efecanbayat.deliveryapp.data.entity.foodadd

import com.google.gson.annotations.SerializedName

data class FoodAddResponse(
    @SerializedName("message")
    val message: Message,
    @SerializedName("success")
    val success: Boolean
)
