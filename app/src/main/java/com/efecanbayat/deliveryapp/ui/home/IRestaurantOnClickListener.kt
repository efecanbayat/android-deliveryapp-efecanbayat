package com.efecanbayat.deliveryapp.ui.home

import com.efecanbayat.deliveryapp.data.entity.Restaurant

interface IRestaurantOnClickListener {
    fun onClick(restaurant: Restaurant)
}