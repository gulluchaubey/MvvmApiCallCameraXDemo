package com.example.mvvmapicallcameraxdemo.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.mvvmapicallcameraxdemo.R
import com.example.mvvmapicallcameraxdemo.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        binding.navView.setupWithNavController(navController)
        binding.bottomNav.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(topLevelDestinationIds = setOf(R.id.homeFragment),binding.drawerLayout)
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController,appBarConfiguration)

        navController.addOnDestinationChangedListener{controller, destination, arguments ->
            if (destination.id==R.id.cameraFragment){
                binding.toolbar.visibility= View.GONE
                binding.bottomNav.visibility=View.GONE
            }else{
                binding.toolbar.visibility= View.VISIBLE
                binding.bottomNav.visibility=View.VISIBLE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragmentContainer).navigateUp(appBarConfiguration)
    }
}