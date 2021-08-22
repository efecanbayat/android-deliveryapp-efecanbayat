package com.efecanbayat.deliveryapp.data.remote

import com.efecanbayat.deliveryapp.data.entity.basket.BasketItemRequest
import com.efecanbayat.deliveryapp.data.entity.foodadd.FoodAddRequest
import com.efecanbayat.deliveryapp.data.entity.login.LoginRequest
import com.efecanbayat.deliveryapp.data.entity.profile.UserRequest
import com.efecanbayat.deliveryapp.data.entity.register.RegisterRequest
import com.efecanbayat.deliveryapp.data.entity.restaurantadd.RestaurantAddRequest
import com.efecanbayat.deliveryapp.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService) : BaseDataSource() {

    suspend fun postLogin(request: LoginRequest) = getResult {
        apiService.login(request)
    }

    suspend fun postRegister(request: RegisterRequest) = getResult {
        apiService.register(request)
    }

    suspend fun getRestaurants() = getResult {
        apiService.getRestaurants()
    }

    suspend fun getRestaurantById(restaurantId: String) = getResult {
        apiService.getRestaurantById(restaurantId)
    }

    suspend fun getFoodById(foodId: String) = getResult {
        apiService.getFoodById(foodId)
    }

    suspend fun getUser() = getResult { apiService.getUser() }

    suspend fun updateUser(userRequest: UserRequest) =
        getResult { apiService.updateUser(userRequest) }

    suspend fun getOrders() = getResult {
        apiService.getOrders()
    }

    suspend fun postBulkOrder(request: BasketItemRequest) = getResult {
        apiService.postBulkOrder(request)
    }

    suspend fun getRestaurantByCategory(category: String) = getResult {
        apiService.getRestaurantByCategory(category)
    }

    suspend fun addRestaurant(request: RestaurantAddRequest) = getResult {
        apiService.addRestaurant(request)
    }

    suspend fun addFood(restaurantId: String,request: FoodAddRequest) = getResult {
        apiService.addFood(restaurantId,request)
    }
}