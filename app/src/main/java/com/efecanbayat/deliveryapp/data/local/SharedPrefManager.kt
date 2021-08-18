package com.efecanbayat.deliveryapp.data.local

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {
    companion object {
        const val TOKEN = "com.efecanbayat.deliveryapp.TOKEN"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("sharedPreferencesUtil", Context.MODE_PRIVATE)

    fun setOnboardingSeen() {
        sharedPreferences.edit().putBoolean("ONBOARDING", true).apply()
    }

    fun isOnboardingSeen(): Boolean {
        return sharedPreferences.getBoolean("ONBOARDING", false)
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN, token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN, "")
    }
}