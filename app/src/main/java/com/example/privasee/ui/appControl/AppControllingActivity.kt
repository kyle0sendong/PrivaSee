package com.example.privasee.ui.appControl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.privasee.databinding.ActivityAppControllingBinding

class AppControllingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppControllingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAppControllingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}