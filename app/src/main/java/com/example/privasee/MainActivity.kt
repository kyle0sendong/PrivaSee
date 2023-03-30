package com.example.privasee

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.privasee.databinding.ActivityMainBinding
import com.example.privasee.ui.initialRun.SetupActivity
import com.example.privasee.utils.CheckPermissionUtils

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("isFirstTimeOpen", Context.MODE_PRIVATE)
        val isFirstTimeOpen = sharedPreferences.getBoolean("isFirstTimeOpen", true)

        if (isFirstTimeOpen) {
            // Start initial run
            Intent(this, SetupActivity::class.java).also {
                startActivity(it)
            }
            sharedPreferences.edit().putBoolean("isFirstTimeOpen", false).apply()
        } else {
            CheckPermissionUtils.checkAccessibilityPermission(this)
        }

        bottomNavController = findNavController(R.id.fcvBotNav)
        binding.botNav.setupWithNavController(bottomNavController)

    }

    override fun onSupportNavigateUp(): Boolean { // make the back button in AddFragment functional
        val navController = findNavController(R.id.fcvUser)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}
