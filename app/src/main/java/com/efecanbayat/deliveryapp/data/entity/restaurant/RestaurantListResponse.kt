package com.efecanbayat.deliveryapp.data.entity.restaurant

import com.google.gson.annotations.SerializedName

data class RestaurantListResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("data")
    val restaurantList: ArrayList<Restaurant>,
    @SerializedName("success")
    val success: Boolean
)
