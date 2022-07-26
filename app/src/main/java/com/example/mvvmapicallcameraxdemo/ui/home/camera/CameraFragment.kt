package com.example.mvvmapicallcameraxdemo.ui.home.camera

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvvmapicallcameraxdemo.R
import com.example.mvvmapicallcameraxdemo.databinding.FragmentCameraBinding


class CameraFragment : Fragment() {

    private var _binding:FragmentCameraBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentCameraBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}