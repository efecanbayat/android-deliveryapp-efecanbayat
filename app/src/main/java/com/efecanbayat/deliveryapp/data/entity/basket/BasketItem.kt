package com.efecanbayat.deliveryapp.data.entity.basket

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket")
data class BasketItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "restaurantId") val restaurantId: String,
    @ColumnInfo(name = "foodId") val foodId: String,
    @ColumnInfo(name = "foodName") val foodName: String,
    @ColumnInfo(name = "restaurantName") val restaurantName: String,
    @ColumnInfo(name = "foodPrice") val foodPrice: String,
    @ColumnInfo(name = "foodImage") val foodImage: String

)