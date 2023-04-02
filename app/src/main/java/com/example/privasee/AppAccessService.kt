package com.example.privasee

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import androidx.core.content.ContextCompat

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

        val metadata = AccessibilityServiceInfo()
        metadata.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED

        val action = intent?.getStringExtra("action")
        val packageNames = intent?.getStringArrayListExtra("packageNames")

        if (packageNames != null) {

            if(action == "addMonitor") {
                Log.d("tagimandos", "intent $packageNames")
                for(packageName in packageNames)
                    this.packageNames.add(packageName)
            }

            if (action == "removeMonitor") {
                Log.d("tagimandos", "intent $packageNames")
                for(packageName in packageNames)
                    this.packageNames.remove(packageName)
            }

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