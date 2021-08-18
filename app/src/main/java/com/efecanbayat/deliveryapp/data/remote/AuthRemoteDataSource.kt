package com.efecanbayat.deliveryapp.data.remote

import com.efecanbayat.deliveryapp.utils.BaseDataSource
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val authAPIService: AuthAPIService): BaseDataSource() {
}