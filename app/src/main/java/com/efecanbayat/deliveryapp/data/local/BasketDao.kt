package com.efecanbayat.deliveryapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.efecanbayat.deliveryapp.data.entity.basket.BasketItem

@Dao
interface BasketDao {

    @Query("SELECT * FROM basket ORDER BY id DESC")
    fun getBasket(): List<BasketItem>

    @Insert
    fun addToBasket(basketItem: BasketItem?)

    @Delete
    fun deleteFromBasket(itemList: ArrayList<BasketItem>?)
}