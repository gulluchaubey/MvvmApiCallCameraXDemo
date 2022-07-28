package com.example.mvvmapicallcameraxdemo.remote

import com.example.mvvmapicallcameraxdemo.data.ApiResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query


interface ApiInterface {
    @Multipart
    @POST("api/beta/content.php?cid=7ec99b415af3e88205250e3514ce0fa7")
    suspend fun beta(
        @Part("type") type:RequestBody,
        @Part image:MultipartBody.Part
    ):Response<ApiResponse>
}