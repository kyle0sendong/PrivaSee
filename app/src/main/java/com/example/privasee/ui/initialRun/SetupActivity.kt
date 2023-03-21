package com.example.privasee.ui.initialRun

import android.content.Intent
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.privasee.R
import com.example.privasee.database.model.App
import com.example.privasee.database.model.Restriction
import com.example.privasee.database.model.User
import com.example.privasee.database.viewmodel.AppViewModel
import com.example.privasee.database.viewmodel.RestrictionViewModel
import com.example.privasee.database.viewmodel.UserViewModel
import com.example.privasee.databinding.ActivitySetupBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SetupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetupBinding
    private lateinit var setupNavController: NavController

    private lateinit var mAppViewModel: AppViewModel
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mRestrictionViewModel: RestrictionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Make this activity the nav host fragment for the navgraph for the initial run fragments
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcvSetup) as NavHostFragment
        setupNavController = navHostFragment.navController
        setupActionBarWithNavController(setupNavController)


        // Initialize the Owner information
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        val userInfo = User(0, "owner", isOwner = true)
        mUserViewModel.addUser(userInfo)

        // Get all the installed app packages in the first run and store it in the database.
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

        mRestrictionViewModel = ViewModelProvider(this)[RestrictionViewModel::class.java]

        // run in coroutines
        lifecycleScope.launch(Dispatchers.IO) {
            val allAppId = mAppViewModel.readAllAppId()
            val ownerId = mUserViewModel.getOwnerId(true)

            for(appId in allAppId) {
                val restriction = Restriction(0, monitored = false, locked = false, ownerId, appId)
                mRestrictionViewModel.addRestriction(restriction)
            }
        }

    }

    // Enable action bar's back button
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fcvSetup)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    // Disable back pressed
    override fun onBackPressed() {}
}
