package com.efecanbayat.deliveryapp.data.remote

import com.efecanbayat.deliveryapp.data.entity.profile.User
import com.efecanbayat.deliveryapp.data.entity.profile.UserRequest
import com.efecanbayat.deliveryapp.data.entity.profile.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface AuthAPIService {

    @GET("auth/profile")
    suspend fun getUser() : Response<UserResponse>

    @PUT("auth/updateDetails")
    suspend fun updateUser(@Body userRequest: UserRequest) : Response<User>
}