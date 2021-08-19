package com.efecanbayat.deliveryapp.ui.home

import com.efecanbayat.deliveryapp.data.entity.restaurant.Restaurant

interface IRestaurantOnClickListener {
    fun onClick(restaurant: Restaurant)
}