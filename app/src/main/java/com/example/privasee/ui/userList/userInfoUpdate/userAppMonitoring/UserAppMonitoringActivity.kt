package com.example.privasee.ui.userList.userInfoUpdate.userAppMonitoring

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.privasee.R
import com.example.privasee.database.viewmodel.RestrictionViewModel
import com.example.privasee.databinding.ActivityUserAppMonitoringBinding

class UserAppMonitoringActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserAppMonitoringBinding
    private lateinit var navController: NavController
    private lateinit var mRestrictionViewModel: RestrictionViewModel
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
        mRestrictionViewModel = ViewModelProvider(this)[RestrictionViewModel::class.java]

    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}