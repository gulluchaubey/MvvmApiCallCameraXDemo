package com.example.mvvmapicallcameraxdemo.ui.home.imageupload

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmapicallcameraxdemo.data.ApiResponse
import com.example.mvvmapicallcameraxdemo.remote.ApiInterface
import com.example.mvvmapicallcameraxdemo.utils.Results
import com.example.mvvmapicallcameraxdemo.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class ImageUploadViewModel @Inject constructor(private val apiInterface:ApiInterface, private val imageUploadRepository: ImageUploadRepository) : ViewModel() {
private var _response = MutableLiveData<com.example.mvvmapicallcameraxdemo.utils.Results<ApiResponse>>()
    val response get() = _response

    fun uploadImage(type:RequestBody,imgUrl:MultipartBody.Part) = viewModelScope.launch {
        _response.postValue(Results(Status.LOADING,null,null))
        response.postValue(Results(imageUploadRepository.getImageUploadResponse(type,imgUrl).status,
            imageUploadRepository.getImageUploadResponse(type,imgUrl).data,null))
    }
}


