package com.efecanbayat.deliveryapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.efecanbayat.deliveryapp.data.ApiRepository
import com.efecanbayat.deliveryapp.data.entity.profile.UserResponse
import com.efecanbayat.deliveryapp.data.entity.restaurant.Restaurant
import com.efecanbayat.deliveryapp.data.entity.restaurant.RestaurantListResponse
import com.efecanbayat.deliveryapp.data.entity.restaurantadd.RestaurantAddRequest
import com.efecanbayat.deliveryapp.data.entity.restaurantadd.RestaurantAddResponse
import com.efecanbayat.deliveryapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    var restaurantList: ArrayList<Restaurant>? = null

    fun getRestaurants(): LiveData<Resource<RestaurantListResponse>> {
        return apiRepository.getRestaurants()
    }

    fun getRestaurantByCategory(category: String): LiveData<Resource<RestaurantListResponse>> {
        return apiRepository.getRestaurantByCategory(category)
    }

    fun addRestaurant(request: RestaurantAddRequest): LiveData<Resource<RestaurantAddResponse>> {
        return apiRepository.addRestaurant(request)
    }

    fun getUser(): LiveData<Resource<UserResponse>> {
        return apiRepository.getUser()
    }

    fun searchTextOnRestaurantList(text: String?): ArrayList<Restaurant>? {
        if (text.isNullOrEmpty())
            return restaurantList

        val filterList: ArrayList<Restaurant> = arrayListOf()
        restaurantList?.forEach { restaurant ->
            if (restaurant.name.contains(text, true))
                filterList.add(restaurant)
            else if (restaurant.district.contains(text, true))
                filterList.add(restaurant)
        }
        return filterList
    }
}