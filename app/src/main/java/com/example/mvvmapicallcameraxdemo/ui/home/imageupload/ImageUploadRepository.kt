package com.example.mvvmapicallcameraxdemo.ui.home.imageupload

import com.example.mvvmapicallcameraxdemo.data.ApiResponse
import com.example.mvvmapicallcameraxdemo.remote.ApiInterface
import com.example.mvvmapicallcameraxdemo.utils.Results
import com.example.mvvmapicallcameraxdemo.utils.Status
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ImageUploadRepository(private val apiInterface: ApiInterface) {
    suspend fun getImageUploadResponse(type:RequestBody,imgUrl:MultipartBody.Part): Results<ApiResponse> {
        return try {

            val response = apiInterface.beta(type,imgUrl)
            if(response.isSuccessful){
                Results(Status.SUCCESS,response.body(),response.message())
            }else{
               Results(Status.ERROR,null,null)
            }
        }catch (e:Exception){
            Results(Status.ERROR,null,null)
        }
    }
}


