package com.efecanbayat.deliveryapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.efecanbayat.deliveryapp.data.ApiRepository
import com.efecanbayat.deliveryapp.data.entity.restaurant.Restaurant
import com.efecanbayat.deliveryapp.data.entity.restaurant.RestaurantListResponse
import com.efecanbayat.deliveryapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
): ViewModel() {

    var restaurantList: ArrayList<Restaurant>? = null

    fun getRestaurants(): LiveData<Resource<RestaurantListResponse>> {
        return apiRepository.getRestaurants()
    }
}