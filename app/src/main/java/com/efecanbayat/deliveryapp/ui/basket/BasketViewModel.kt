package com.efecanbayat.deliveryapp.ui.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.efecanbayat.deliveryapp.data.ApiRepository
import com.efecanbayat.deliveryapp.data.entity.basket.BasketItem
import com.efecanbayat.deliveryapp.data.entity.basket.BasketItemRequest
import com.efecanbayat.deliveryapp.data.entity.basket.BasketItemResponse
import com.efecanbayat.deliveryapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
): ViewModel() {

    var itemList = ArrayList<BasketItem>()

    fun getBasket(): List<BasketItem> {
        return apiRepository.getBasket()
    }

    fun postBulkOrder(restaurantId: String, mealsId: ArrayList<String>): LiveData<Resource<BasketItemResponse>> {
        val request = BasketItemRequest(restaurantId,mealsId)
        return apiRepository.postBulkOrder(request)
    }

    fun deleteFromBasket(itemList: ArrayList<BasketItem>) {
        return apiRepository.deleteFromBasket(itemList)
    }

}