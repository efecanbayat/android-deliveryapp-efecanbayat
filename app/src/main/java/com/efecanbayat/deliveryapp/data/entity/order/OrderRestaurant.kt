package com.efecanbayat.deliveryapp.data.entity.order

import com.google.gson.annotations.SerializedName

data class OrderRestaurant(
    @SerializedName("_id")
    val id: String,
    @SerializedName("imageUrl")
    val image: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("rating")
    val rating: Double
)
