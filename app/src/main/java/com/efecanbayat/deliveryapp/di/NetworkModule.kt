package com.efecanbayat.deliveryapp.di

import com.efecanbayat.deliveryapp.BuildConfig
import com.efecanbayat.deliveryapp.data.remote.APIService
import com.efecanbayat.deliveryapp.data.remote.RemoteDataSource
import com.efecanbayat.deliveryapp.data.local.LocalDataSource
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(
    ActivityRetainedComponent::class
)
class NetworkModule {

    @Provides
    fun provideNoAuthInterceptorOkHttpClient(): OkHttpClient{
        val builder = OkHttpClient.Builder()
        builder.interceptors().add(HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                }else {
                    HttpLoggingInterceptor.Level.NONE
                }
        })
        return provideOkHttpClient(builder.build())
    }

    @Provides
    fun provideAuthInterceptorOkHttpClient(
        localDataSource: LocalDataSource
    ): OkHttpClient {
        return provideOkHttpClient(OkHttpClient.Builder()
            .addInterceptor {
                val token = localDataSource.getToken()
                val request = it.request().newBuilder().addHeader("Authorization", token!!).build()
                it.proceed(request)
            }
            .build())
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
        endPoint: EndPoint
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endPoint.url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideRemoteDataSource(
        apiService: APIService
    ): RemoteDataSource{
        return RemoteDataSource(apiService)
    }

    @Provides
    fun provideOkHttpClient(okHttpClient: OkHttpClient): OkHttpClient{
        return OkHttpClient()
    }

    @Provides
    fun provideEndPoint(): EndPoint{
        return EndPoint("https://fooddeliveryapp26.herokuapp.com/api/")
    }

}

data class EndPoint(val url: String)