package com.example.privasee

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.privasee.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("isFirstTimeOpen", Context.MODE_PRIVATE)
        val isFirstTimeOpen = sharedPreferences.getBoolean("is_first_time_open", true)

        if (isFirstTimeOpen)
            sharedPreferences.edit().putBoolean("is_first_time_open", false).apply()

        val bottomNavigationView = binding.botNav
        bottomNavController = findNavController(R.id.fcvBotNav)
        bottomNavigationView.setupWithNavController(bottomNavController)

    }

    override fun onSupportNavigateUp(): Boolean { // make the back button in AddFragment functional
        val navController = findNavController(R.id.fcvUser)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}
