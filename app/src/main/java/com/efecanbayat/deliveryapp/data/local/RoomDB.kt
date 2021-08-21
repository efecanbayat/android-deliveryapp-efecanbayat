package com.efecanbayat.deliveryapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.efecanbayat.deliveryapp.data.entity.BasketItem


@Database(entities = [BasketItem::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun basketDao(): BasketDao
}
