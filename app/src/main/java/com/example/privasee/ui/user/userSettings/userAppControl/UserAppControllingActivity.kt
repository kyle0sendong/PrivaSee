package com.example.privasee.ui.user.userSettings.userAppControl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.privasee.databinding.ActivityUserAppControllingBinding

class UserAppControllingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserAppControllingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUserAppControllingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}