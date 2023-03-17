package com.example.privasee.ui.appMonitoring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.privasee.R
import com.example.privasee.databinding.ActivityAppMonitoringBinding

class AppMonitoringActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppMonitoringBinding
    private lateinit var monitoringNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()
        binding = ActivityAppMonitoringBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Make this activity the nav host fragment for the monitoring
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcvAppMonitoring) as NavHostFragment
        monitoringNavController = navHostFragment.navController
        setupActionBarWithNavController(monitoringNavController)

    }
}