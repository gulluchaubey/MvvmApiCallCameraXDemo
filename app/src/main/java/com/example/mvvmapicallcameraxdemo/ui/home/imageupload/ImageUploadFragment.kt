package com.example.mvvmapicallcameraxdemo.ui.home.imageupload

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toFile
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mvvmapicallcameraxdemo.databinding.FragmentImageUploadBinding
import com.example.mvvmapicallcameraxdemo.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

@AndroidEntryPoint
class ImageUploadFragment : Fragment() {

    private var _binding : FragmentImageUploadBinding? = null
    private val binding get() = _binding!!

    val args: ImageUploadFragmentArgs by navArgs()
    val viewModel : ImageUploadViewModel by viewModels()

    private var imgUrl : String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentImageUploadBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        imgUrl = args.imageUrl


        Glide.with(requireActivity()).apply {
            load(imgUrl).into(binding.ivCapturedImg)
        }


        //pass it like this
        val file = File(imgUrl)
        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file.path)
        // MultipartBody.Part is used to send also the actual file name
        val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

        // add another part within the multipart request
        //new_uName.toRequestBody("text/plain".toMediaTypeOrNull())
        val type = RequestBody.create(MediaType.parse("text/plain"), "media")


        viewModel.response.observe(viewLifecycleOwner){
            when(it.status){
                Status.SUCCESS->{
                    binding.progressBar.progress = 1
                    Toast.makeText(activity,"Status.SUCCESS",Toast.LENGTH_SHORT).show()
                }
                Status.LOADING->{
                    binding.progressBar.progress = 1
                    Toast.makeText(activity,"Status.LOADING",Toast.LENGTH_SHORT).show()
                }
                Status.ERROR->{
                    binding.progressBar.progress = 0
                    Toast.makeText(activity,"Status.ERROR",Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.btnUploadImg.setOnClickListener {
            viewModel.uploadImage(type,body)
        }
        return view
    }
    fun getBitmap(context: Context, imageUri: Uri): Bitmap? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(
                    context.contentResolver, imageUri))
        } else {
            context.contentResolver
                .openInputStream(imageUri)?.use { inputStream ->
                    BitmapFactory.decodeStream(inputStream)
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}