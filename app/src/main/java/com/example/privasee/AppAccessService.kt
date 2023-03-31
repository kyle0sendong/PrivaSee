package com.example.privasee

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import com.example.privasee.ui.userList.userInfoUpdate.userAppControl.applock.BlockScreen


class AppAccessService : AccessibilityService() {

    private var packageNames: MutableList<String> = mutableListOf()
    private var previousPackageName = "initial"

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if(event?.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {

            val packageName = event.packageName.toString()

            // Triggering only once, for whitelisted apps
            if (previousPackageName == packageName) {
                previousPackageName = packageName
                val pm = applicationContext.packageManager
                val appInfo = pm.getPackageInfo(packageName, PackageManager.GET_META_DATA)
                val appName = appInfo.applicationInfo.loadLabel(pm).toString()
                Log.d("testing", "$appName to foreground")

                val intent = Intent(this, BlockScreen::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }

//            // For blocklisted apps test
//            previousPackageName = packageName
//            val pm = applicationContext.packageManager
//            val appInfo = pm.getPackageInfo(packageName, PackageManager.GET_META_DATA)
//            val appName = appInfo.applicationInfo.loadLabel(pm).toString()
//            Log.d("testing", "$appName to foreground")
//
//            val intent = Intent(this, BlockScreen::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            startActivity(intent)
        }
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.d("test1234", "onServiceConnected")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.getStringExtra("action")

        if(action == "addMonitor") {
            val metadata = AccessibilityServiceInfo()
            metadata.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED

            val addMonitor = intent.getStringArrayListExtra("addMonitoredAppPackageName")
            Log.d("test1234", "Check List: Add to monitoring $addMonitor ")
//            packageNames.add("com.google.android.apps.photos")
            metadata.packageNames = packageNames.toTypedArray()
            serviceInfo = metadata
        }

        if (action == "removeMonitor") {
            val metadata = AccessibilityServiceInfo()
            metadata.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED

            val removeMonitor = intent.getStringArrayListExtra("removeMonitoredAppPackageName")
            Log.d("test1234", "Check List: Removing monitoring list $removeMonitor ")
            metadata.packageNames = packageNames.toTypedArray()
            serviceInfo = metadata
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onInterrupt() {
        TODO("Not yet implemented")
    }
}