package com.example.jet2feed.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData

object NetworkUtils : LiveData<Boolean>() {
    private var isAvailable = false

    fun isInternetOn(application: Application): Boolean {
        // Use ConnectivityManager to check whether we are actually connected to the Internet,
        // and if so return true.
        val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // For devices with API >= 23
        val networkCapabilities = cm.getNetworkCapabilities(cm.activeNetwork)
        // NET_CAPABILITY_VALIDATED - Indicates that connectivity on this network was successfully validated.
        // NET_CAPABILITY_INTERNET - Indicates that this network should be able to reach the internet.
        isAvailable =
            (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED))
        return isAvailable
    }
}