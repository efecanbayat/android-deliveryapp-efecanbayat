package com.efecanbayat.deliveryapp.data.remote

import com.efecanbayat.deliveryapp.data.entity.login.LoginRequest
import com.efecanbayat.deliveryapp.data.entity.login.LoginResponse
import com.efecanbayat.deliveryapp.data.entity.register.RegisterRequest
import com.efecanbayat.deliveryapp.data.entity.register.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
}