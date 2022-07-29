package com.example.mvvmapicallcameraxdemo.hilt

import com.example.mvvmapicallcameraxdemo.remote.ApiInterface
import com.example.mvvmapicallcameraxdemo.ui.home.imageupload.ImageUploadRepository
import com.example.mvvmapicallcameraxdemo.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    @Provides
    fun provideRetrofitInterface():ApiInterface{
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).client(OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()).
        build().create(ApiInterface::class.java)
    }

    @Provides
    fun provideRepository(apiInterface: ApiInterface) : ImageUploadRepository{
        return ImageUploadRepository(apiInterface)
    }
}