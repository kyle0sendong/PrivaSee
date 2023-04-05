package com.example.privasee.ui.userList.userInfoUpdate.userAppMonitoring

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.privasee.R
import com.example.privasee.database.model.Restriction
import com.example.privasee.database.viewmodel.AppViewModel
import com.example.privasee.database.viewmodel.RestrictionViewModel
import com.example.privasee.databinding.ActivityUserAppMonitoringBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserAppMonitoringActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserAppMonitoringBinding
    private lateinit var navController: NavController

    private lateinit var mRestrictionViewModel: RestrictionViewModel
    private lateinit var mAppViewModel: AppViewModel

    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityUserAppMonitoringBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcvMonitoring) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

        val userId = intent.extras?.getInt("userId")
        val bundle = Bundle()

        if (userId != null) {
            bundle.putInt("userId", userId)

            mRestrictionViewModel = ViewModelProvider(this)[RestrictionViewModel::class.java]
            mAppViewModel = ViewModelProvider(this)[AppViewModel::class.java]

            // Initializes restriction list for this user
            job = lifecycleScope.launch(Dispatchers.IO) {
                val userRestrictionCount = mRestrictionViewModel.getUserRestrictionCount(userId)
                if (userRestrictionCount < 1) {
                    val appList = mAppViewModel.getAllData()
                    for (app in appList) {
                        val restriction = Restriction(0, app.appName, monitored = false, controlled = false, userId, app.id)
                        mRestrictionViewModel.addRestriction(restriction)
                    }
                }
            }
        }

        navController.setGraph(R.navigation.monitoring_nav, bundle)

    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
        finish()
    }
}