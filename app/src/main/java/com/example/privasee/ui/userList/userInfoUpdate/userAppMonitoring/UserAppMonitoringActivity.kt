package com.example.privasee.ui.userList.userInfoUpdate.userAppMonitoring

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.privasee.R
import com.example.privasee.databinding.ActivityUserAppMonitoringBinding
import com.example.privasee.ui.user.userSettings.userAppMonitoring.unmonitored.UserAppUnmonitoredFragmentDirections

class UserAppMonitoringActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserAppMonitoringBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserAppMonitoringBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcvMonitoring) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

        val userId = intent.extras?.getInt("userId")
        val bundle = Bundle()

        if (userId != null)
            bundle.putInt("userId", userId)

        navController.setGraph(R.navigation.monitoring_nav, bundle)

    }
}