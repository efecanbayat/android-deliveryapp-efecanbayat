package com.efecanbayat.deliveryapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.efecanbayat.deliveryapp.data.ApiRepository
import com.efecanbayat.deliveryapp.data.entity.basket.BasketItem
import com.efecanbayat.deliveryapp.data.entity.order.OrderListResponse
import com.efecanbayat.deliveryapp.data.entity.profile.User
import com.efecanbayat.deliveryapp.data.entity.profile.UserRequest
import com.efecanbayat.deliveryapp.data.entity.profile.UserResponse
import com.efecanbayat.deliveryapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
): ViewModel() {

    var orderList: ArrayList<BasketItem>? = null

    fun logout() {
        apiRepository.logout()
    }

    fun getUser(): LiveData<Resource<UserResponse>> {
        return apiRepository.getUser()
    }

    fun updateUser(userRequest: UserRequest): LiveData<Resource<User>> {
        return apiRepository.updateUser(userRequest)
    }

    fun getOrders(): LiveData<Resource<OrderListResponse>> {
        return apiRepository.getOrders()
    }
}