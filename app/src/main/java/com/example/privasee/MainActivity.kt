package com.example.privasee

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.privasee.database.viewmodel.UserViewModel
import com.example.privasee.databinding.ActivityMainBinding
import com.example.privasee.ui.initialRun.SetupActivity
import com.example.privasee.utils.CheckPermissionUtils

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavController: NavController

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavController = findNavController(R.id.fcvBotNav)
        binding.botNav.setupWithNavController(bottomNavController)

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        // Checking if first time has been opened or when owner is non-existent
        val sharedPreferences = getSharedPreferences("isFirstTimeOpen", Context.MODE_PRIVATE)
        val isFirstTimeOpen = sharedPreferences.getBoolean("isFirstTimeOpen", true)

        mUserViewModel.getAllDataLive.observe(this, Observer { userList ->

            if (isFirstTimeOpen || userList.isEmpty()) {
                // Start initial run
                val intent = Intent(this@MainActivity, SetupActivity::class.java)
                startActivity(intent)
                sharedPreferences.edit().putBoolean("isFirstTimeOpen", false).apply()
            } else
                CheckPermissionUtils.checkAccessibilityPermission(this)

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fcvUser)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {} // Disable back button

}
