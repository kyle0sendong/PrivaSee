package com.example.privasee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.privasee.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var BottomNavController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.botNav
        BottomNavController = findNavController(R.id.fcvBotNav)
        bottomNavigationView.setupWithNavController(BottomNavController)

    }


    override fun onSupportNavigateUp(): Boolean { // make the back button in AddFragment functional
        val navController = findNavController(R.id.fcvUser)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}
