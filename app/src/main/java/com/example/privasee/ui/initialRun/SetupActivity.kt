package com.example.privasee.ui.initialRun

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.privasee.R
import com.example.privasee.database.model.App
import com.example.privasee.database.viewmodel.AppViewModel
import com.example.privasee.databinding.ActivitySetupBinding

class SetupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetupBinding
    private lateinit var setupNavController: NavController
    private lateinit var mAppViewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Make this activity the nav host fragment for the navgraph for the initial run fragments
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcvSetup) as NavHostFragment
        setupNavController = navHostFragment.navController
        setupActionBarWithNavController(setupNavController)

        // Get all the installed app packages in the first run and store it in the database
        mAppViewModel = ViewModelProvider(this)[AppViewModel::class.java]
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val packageManager = this.packageManager
        val resolveInfoList = packageManager?.queryIntentActivities(intent, PackageManager.MATCH_ALL)

        if (resolveInfoList != null) {
            for (resolveInfo in resolveInfoList) {

                val packageName = resolveInfo.activityInfo.packageName
                val applicationInfo = packageManager.getApplicationLabel(resolveInfo.activityInfo.applicationInfo).toString()
                val appInfo = App(0, packageName, applicationInfo)
                mAppViewModel.addApp(appInfo)

            }
        }

    }
}