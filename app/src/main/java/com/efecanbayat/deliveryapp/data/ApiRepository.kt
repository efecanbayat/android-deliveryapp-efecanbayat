package com.efecanbayat.deliveryapp.data

import com.efecanbayat.deliveryapp.data.entity.BasketItem
import com.efecanbayat.deliveryapp.data.entity.login.LoginRequest
import com.efecanbayat.deliveryapp.data.entity.profile.UserRequest
import com.efecanbayat.deliveryapp.data.entity.register.RegisterRequest
import com.efecanbayat.deliveryapp.data.local.LocalDataSource
import com.efecanbayat.deliveryapp.data.remote.AuthRemoteDataSource
import com.efecanbayat.deliveryapp.data.remote.RemoteDataSource
import com.efecanbayat.deliveryapp.utils.performAuthTokenNetworkOperation
import com.efecanbayat.deliveryapp.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private var authRemoteDataSource: AuthRemoteDataSource,
    private var localDataSource: LocalDataSource
) {

    fun login(request: LoginRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.postLogin(request)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )

    fun register(request: RegisterRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.postRegister(request)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )

    fun logout() {
        localDataSource.saveToken("")
    }

    fun getRestaurants() = performNetworkOperation {
        remoteDataSource.getRestaurants()
    }

    fun getRestaurantById(restaurantId: String) = performNetworkOperation {
        remoteDataSource.getRestaurantById(restaurantId)
    }

    fun getFoodById(foodId: String) = performNetworkOperation {
        remoteDataSource.getFoodById(foodId)
    }

    fun getUser() = performNetworkOperation {
        authRemoteDataSource.getUser()
    }

    fun updateUser(userRequest: UserRequest) = performNetworkOperation {
        authRemoteDataSource.updateUser(userRequest)
    }

    fun getBasket() = localDataSource.getBasket()

    fun addToBasket(basketItem: BasketItem) = localDataSource.addToBasket(basketItem)

    fun deleteFromBasket(basketItem: BasketItem) = localDataSource.deleteFromBasket(basketItem)

}