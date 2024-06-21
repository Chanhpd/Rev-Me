package com.example.fitme.model

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.fitme.api.ApiService
import retrofit2.Call
import retrofit2.Response

object PreferenceManager {


    private const val PREFERENCE_NAME = "RevMe"


    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun saveKey(context: Context, key: String) {
        val editor = getPreferences(context).edit()
        editor.putString("key", key)
        editor.apply()
    }

    fun getKey(context: Context): String? {
        return getPreferences(context).getString("key", null)
    }

    fun saveUserId(context: Context, userId: Int) {
        val editor = getPreferences(context).edit()
        editor.putInt("userId", userId)
        editor.apply()
    }

    fun getUserId(context: Context): Int {
        return getPreferences(context).getInt("userId", 0)
    }

    fun saveGoalId(context: Context, goalId: Int) {
        val editor = getPreferences(context).edit()
        editor.putInt("goalId", goalId)
        editor.apply()
    }

    fun getGoalId(context: Context): Int {
        return getPreferences(context).getInt("goalId", 0)
    }
}