package com.example.privasee

import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModelProvider
import com.example.privasee.database.model.User
import com.example.privasee.database.viewmodel.UserViewModel

class TestIntentService : IntentService("TestIntentService") {

    private lateinit var mUserViewModel: UserViewModel

    override fun onHandleIntent(intent: Intent?) {

        // Initialize foreground task
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Service Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        getSystemService(NotificationManager::class.java)?.createNotificationChannel(channel)
        val notification = NotificationCompat.Builder(this, CHANNEL_ID) // No notifs
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .build()

        // Start foreground
        startForeground(NOTIFICATION_ID, notification)

        // Do something kahit ano haha
        mUserViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(UserViewModel::class.java)
        val testUser = User(0, "testUser", false)
        Log.d("tagimandos", "test insert $testUser")
        mUserViewModel.addUser(testUser)

        stopSelf() // Staph
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("tagimandos", "Service destroyed")
    }

    companion object {
        private const val CHANNEL_ID = "ServiceChannel"
        private const val NOTIFICATION_ID = 1
    }
}