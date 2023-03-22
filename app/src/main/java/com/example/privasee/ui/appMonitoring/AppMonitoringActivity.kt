package com.example.privasee.ui.appMonitoring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.privasee.R
import com.example.privasee.databinding.ActivityAppMonitoringBinding

class AppMonitoringActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppMonitoringBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppMonitoringBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcvMonitoring) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

    }
}