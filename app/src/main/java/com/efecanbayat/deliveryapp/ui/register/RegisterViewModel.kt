package com.efecanbayat.deliveryapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.efecanbayat.deliveryapp.data.ApiRepository
import com.efecanbayat.deliveryapp.data.entity.login.LoginRequest
import com.efecanbayat.deliveryapp.data.entity.register.RegisterRequest
import com.efecanbayat.deliveryapp.data.entity.register.RegisterResponse
import com.efecanbayat.deliveryapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
): ViewModel() {

    fun register(name: String, email: String, password: String): LiveData<Resource<RegisterResponse>> {
        val request = RegisterRequest(name, email, password)
        return apiRepository.register(request)
    }
}