package com.efecanbayat.deliveryapp.di

import android.content.Context
import androidx.room.Room
import com.efecanbayat.deliveryapp.data.local.BasketDao
import com.efecanbayat.deliveryapp.data.local.LocalDataSource
import com.efecanbayat.deliveryapp.data.local.RoomDB
import com.efecanbayat.deliveryapp.data.local.SharedPrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(
    ActivityRetainedComponent::class
)
class DatabaseModule {

    @Provides
    fun sharedPrefManager(@ApplicationContext context: Context): SharedPrefManager {
        return SharedPrefManager(context)
    }

    @Provides
    fun localDataSource(
        sharedPrefManager: SharedPrefManager,
        basketDao: BasketDao
    ): LocalDataSource {
        return LocalDataSource(sharedPrefManager, basketDao)
    }

    @Provides
    fun provideRoomDb(@ApplicationContext context: Context): RoomDB {
        return Room
            .databaseBuilder(context, RoomDB::class.java, "LocalDb")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(roomDB: RoomDB): BasketDao {
        return roomDB.basketDao()
    }
}