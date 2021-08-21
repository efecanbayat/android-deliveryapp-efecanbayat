package com.efecanbayat.deliveryapp.ui.order

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.efecanbayat.deliveryapp.data.ApiRepository
import com.efecanbayat.deliveryapp.data.entity.BasketItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
): ViewModel() {

    fun getBasket(): List<BasketItem> {
        return apiRepository.getBasket()
    }
}