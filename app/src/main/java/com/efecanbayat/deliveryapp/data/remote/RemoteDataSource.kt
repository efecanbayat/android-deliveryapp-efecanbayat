package com.efecanbayat.deliveryapp.data.remote

import com.efecanbayat.deliveryapp.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService): BaseDataSource() {

}