package com.example.privasee



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.privasee.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
//        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.bottomNavMenu
        navController = findNavController(R.id.bottom_nav_menu_fcv)
        bottomNavigationView.setupWithNavController(navController)

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.user_fcv) as NavHostFragment
//        navController = navHostFragment.navController
//        setupActionBarWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean { // make the back button in AddFragment functional
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}



//        // Get the navigation host fragment from this Activity
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.user_fcv) as NavHostFragment
//        // Instantiate the navController using the NavHostFragment
//        navController = navHostFragment.navController
//        // Make sure actions in the ActionBar get propagated to the NavController
//        setupActionBarWithNavController(navController)


//    override fun onSupportNavigateUp(): Boolean { // make the back button in AddFragment functional
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }