package com.efecanbayat.deliveryapp.data.entity.order

import com.google.gson.annotations.SerializedName

data class OrderListResponse(
    @SerializedName("data")
    val orderList: ArrayList<Order>,
    @SerializedName("success")
    val success: Boolean
)
