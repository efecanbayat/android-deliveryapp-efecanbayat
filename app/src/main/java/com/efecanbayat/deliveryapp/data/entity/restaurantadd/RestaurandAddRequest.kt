package com.efecanbayat.deliveryapp.data.entity.restaurantadd

import com.google.gson.annotations.SerializedName

data class RestaurantAddRequest(
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("cuisine")
    val category: String,
    @SerializedName("district")
    val district: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("deliveryInfo")
    val deliveryInfo: String,
    @SerializedName("deliveryTime")
    val deliveryTime: String,
    @SerializedName("minDeliveryFee")
    val minDeliveryFee: String,
    @SerializedName("paymentMethods")
    val paymentMethods: String,
    @SerializedName("rating")
    val rating: Int
)
