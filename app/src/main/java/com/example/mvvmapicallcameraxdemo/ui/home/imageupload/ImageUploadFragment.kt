package com.example.mvvmapicallcameraxdemo.ui.home.imageupload

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvvmapicallcameraxdemo.R
import com.example.mvvmapicallcameraxdemo.databinding.FragmentCameraBinding
import com.example.mvvmapicallcameraxdemo.databinding.FragmentImageUploadBinding


class ImageUploadFragment : Fragment() {

    private var _binding : FragmentImageUploadBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentImageUploadBinding.inflate()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_upload, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}