package com.efecanbayat.deliveryapp.data.entity.profile

import com.google.gson.annotations.SerializedName

data class UserRequest (
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("place")
val address: String
)
