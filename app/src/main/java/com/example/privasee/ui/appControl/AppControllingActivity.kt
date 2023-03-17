package com.example.privasee.ui.appRestriction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.privasee.R
import com.example.privasee.databinding.ActivityAppRestrictionBinding

class AppRestrictionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppRestrictionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAppRestrictionBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}