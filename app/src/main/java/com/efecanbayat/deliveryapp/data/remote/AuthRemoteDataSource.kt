package com.efecanbayat.deliveryapp.data.remote

import com.efecanbayat.deliveryapp.data.entity.profile.UserRequest
import com.efecanbayat.deliveryapp.utils.BaseDataSource
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val authAPIService: AuthAPIService): BaseDataSource() {

    suspend fun getUser() = getResult { authAPIService.getUser() }

    suspend fun updateUser(userRequest: UserRequest) = getResult { authAPIService.updateUser(userRequest) }
}