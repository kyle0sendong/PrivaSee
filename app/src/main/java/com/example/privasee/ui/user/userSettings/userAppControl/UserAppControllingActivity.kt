package com.example.privasee.ui.user.userSettings.userAppControl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.privasee.R
import com.example.privasee.databinding.ActivityUserAppControllingBinding

class UserAppControllingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserAppControllingBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUserAppControllingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcvControlling) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

    }
}