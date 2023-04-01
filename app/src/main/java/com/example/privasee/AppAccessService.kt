package com.example.privasee

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_USER_ACTION
import android.content.pm.PackageManager
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.privasee.database.model.User
import com.example.privasee.database.viewmodel.RecordViewModel
import com.example.privasee.database.viewmodel.UserViewModel
import com.example.privasee.ui.userList.userInfoUpdate.userAppControl.applock.BlockScreen

class AppAccessService : AccessibilityService() {

    private var packageNames: MutableList<String> = mutableListOf()
    private var previousPackageName = "initial"

    private var controlAction: String = "removeLock"

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {

        if (packageNames.size > 0) {

            if(event?.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {

                val currentlyOpenedApp = event.packageName.toString()

                val pm = applicationContext.packageManager
                val appInfo = pm.getPackageInfo(currentlyOpenedApp, PackageManager.GET_META_DATA)
                val appName = appInfo.applicationInfo.loadLabel(pm).toString()

                // Triggering only once, for repeated opens
                if (previousPackageName == currentlyOpenedApp) {
                    previousPackageName = currentlyOpenedApp

                } else {
                    previousPackageName = currentlyOpenedApp
                    // start intent service, start verifying etc
                    Log.d("tagimandos", "$appName")
                    val intent = Intent(this, TestIntentService::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    ContextCompat.startForegroundService(this, intent)
                }
            }
        }

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val monitorAction = intent?.getStringExtra("action")
        controlAction = intent?.getStringExtra("controlAction").toString()
        if(monitorAction == "addMonitor") {
            val metadata = AccessibilityServiceInfo()
            metadata.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED

            val addMonitor = intent.getStringArrayListExtra("addMonitoredAppPackageName")

            Log.d("tagimandos", "intent $addMonitor")
            if (addMonitor != null)
                for(appPackageName in addMonitor)
                    packageNames.add(appPackageName)

            metadata.packageNames = packageNames.toTypedArray()
            serviceInfo = metadata
        }

        if (monitorAction == "removeMonitor") {
            val metadata = AccessibilityServiceInfo()
            metadata.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED

            val removeMonitor = intent.getStringArrayListExtra("removeMonitoredAppPackageName")
            Log.d("tagimandos", "intent $removeMonitor")
            if (removeMonitor != null)
                for(appPackageName in removeMonitor)
                    packageNames.remove(appPackageName)

            metadata.packageNames = packageNames.toTypedArray()
            serviceInfo = metadata
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onInterrupt() {
        TODO("Not yet implemented")
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.d("tagimandos", "On service connect")
    }

}