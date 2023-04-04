package com.example.privasee

import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModelProvider
import com.example.privasee.database.model.Record
import com.example.privasee.database.model.User
import com.example.privasee.database.viewmodel.RecordViewModel
import java.util.*

class DbQueryIntentService : IntentService("TestIntentService") {

    private lateinit var mRecordViewModel: RecordViewModel

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

        val query = intent?.getStringExtra("query")

        if(query == "insertRecord") {

            val calendar = Calendar.getInstance()
            calendar.time = Date()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH) + 1 // month is 0 based so + 1
            val year = calendar.get(Calendar.YEAR)
            val time = calendar.timeInMillis

            val appName = intent.getStringExtra("appName")
            mRecordViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(RecordViewModel::class.java)
            val record = appName?.let { Record(0, day, month, year, time, it) }
            Log.d("tagimandos", "dbqueryintent service $record")

            if (record != null)
                mRecordViewModel.addRecord(record)

        }

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