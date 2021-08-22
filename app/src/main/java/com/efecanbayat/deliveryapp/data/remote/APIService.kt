package com.efecanbayat.deliveryapp.data.remote

import com.efecanbayat.deliveryapp.data.entity.basket.BasketItemRequest
import com.efecanbayat.deliveryapp.data.entity.basket.BasketItemResponse
import com.efecanbayat.deliveryapp.data.entity.food.FoodResponse
import com.efecanbayat.deliveryapp.data.entity.login.LoginRequest
import com.efecanbayat.deliveryapp.data.entity.login.LoginResponse
import com.efecanbayat.deliveryapp.data.entity.order.OrderListResponse
import com.efecanbayat.deliveryapp.data.entity.profile.User
import com.efecanbayat.deliveryapp.data.entity.profile.UserRequest
import com.efecanbayat.deliveryapp.data.entity.profile.UserResponse
import com.efecanbayat.deliveryapp.data.entity.register.RegisterRequest
import com.efecanbayat.deliveryapp.data.entity.register.RegisterResponse
import com.efecanbayat.deliveryapp.data.entity.restaurant.RestaurantListResponse
import com.efecanbayat.deliveryapp.data.entity.restaurant.RestaurantResponse
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @GET("a/restaurant")
    suspend fun getRestaurants(): Response<RestaurantListResponse>

    @GET("a/restaurant/{id}")
    suspend fun getRestaurantById(@Path("id") restaurantId: String): Response<RestaurantResponse>

    @GET("a/meal/{id}")
    suspend fun getFoodById(@Path("id") foodId: String): Response<FoodResponse>

    @GET("auth/profile")
    suspend fun getUser(): Response<UserResponse>

    @PUT("auth/updateDetails")
    suspend fun updateUser(@Body userRequest: UserRequest): Response<User>

    @GET("a/order/bulk")
    suspend fun getOrders(): Response<OrderListResponse>

    @POST("a/order/bulk")
    suspend fun postBulkOrder(@Body request: BasketItemRequest): Response<BasketItemResponse>

    @GET("a/restaurant/cuisine/{cuisineName}")
    suspend fun getRestaurantByCategory(@Path("cuisineName") category: String): Response<RestaurantListResponse>
}