package com.example.mvvmapicallcameraxdemo.ui.home.imageupload

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mvvmapicallcameraxdemo.R
import com.example.mvvmapicallcameraxdemo.databinding.FragmentImageUploadBinding
import com.example.mvvmapicallcameraxdemo.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
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

        //lifecycleScope.launch {  }
        val file =  File(getRealPathFromUri(requireContext(),imgUrl.toUri()))
        //val requestFile = RequestBody.create(MediaType.parse("image/jpg"), file)
        val requestFile = RequestBody.create(MediaType.parse("text/plain"), file)
        // MultipartBody.Part is used to send also the actual file name
        val body: MultipartBody.Part = MultipartBody.Part.createFormData("image", file.name, requestFile)

        // add another part within the multipart request
        val type = RequestBody.create(MediaType.parse("text/plain"), "media")


        viewModel.response.observe(viewLifecycleOwner){
            when(it.status){
                Status.SUCCESS->{
                    binding.progressBar.progress = 100
                    Toast.makeText(activity, it.data?.message ?: "SUCCESS",Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(view).popBackStack(R.id.homeFragment,false)
                }
                Status.LOADING->{
                    binding.progressBar.progress = 50
                    Toast.makeText(activity,"Status.LOADING",Toast.LENGTH_SHORT).show()
                }
                Status.ERROR->{
                    binding.progressBar.progress = 0
                    Toast.makeText(activity, it.data?.message ?: "ERROR",Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(view).popBackStack(R.id.homeFragment,false)
                }
            }
        }
        binding.btnUploadImg.setOnClickListener {
            viewModel.uploadImage(type,body)
        }
        return view
    }
    fun getRealPathFromUri(context: Context, contentUri: Uri?): String? {
        var cursor: Cursor? = null
        return try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.contentResolver.query(contentUri!!, proj, null, null, null)
            val column_index: Int = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            cursor.getString(column_index)
        } finally {
            if (cursor != null) {
                cursor.close()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}