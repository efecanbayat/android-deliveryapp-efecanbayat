package com.efecanbayat.deliveryapp.data

import com.efecanbayat.deliveryapp.data.local.LocalDataSource
import com.efecanbayat.deliveryapp.data.remote.RemoteDataSource
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private var localDataSource: LocalDataSource
) {

}