package com.efecanbayat.deliveryapp.data.local

import com.efecanbayat.deliveryapp.data.entity.BasketItem
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val sharedPrefManager: SharedPrefManager,
    private val basketDao: BasketDao
) {

    fun saveToken(token: String) {
        sharedPrefManager.saveToken(token)
    }

    fun getToken(): String? {
        return sharedPrefManager.getToken()
    }

    fun getBasket(): List<BasketItem> {
        return basketDao.getBasket()
    }

    fun addToBasket(basketItem: BasketItem) {
        basketDao.addToBasket(basketItem)
    }

    fun deleteFromBasket(basketItem: BasketItem) {
        basketDao.deleteFromBasket(basketItem)
    }
}