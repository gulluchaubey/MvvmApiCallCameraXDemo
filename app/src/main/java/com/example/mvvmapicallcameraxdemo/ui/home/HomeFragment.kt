package com.example.mvvmapicallcameraxdemo.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvvmapicallcameraxdemo.R
import com.example.mvvmapicallcameraxdemo.databinding.FragmentHomeBinding
import com.example.mvvmapicallcameraxdemo.databinding.FragmentImageUploadBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding!!
    private var adapterHome:AdapterHome = AdapterHome()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        binding.rvHome.adapter = adapterHome
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}