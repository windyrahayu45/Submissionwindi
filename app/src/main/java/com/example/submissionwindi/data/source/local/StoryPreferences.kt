package com.example.submissionwindi.data.source.local

import android.content.SharedPreferences

class StoryPreferences(private val preferences: SharedPreferences) {

    fun saveToken(token: String, isLogin : Boolean) {
        preferences.edit().putString(TOKEN, token).apply()
        preferences.edit().putBoolean(IS_LOGIN, isLogin).apply()
    }

    fun getToken(): String {
        return preferences.getString(TOKEN, "") ?: ""

    }

    companion object {
        const val PREFERENCES_NAME = "story_preferences"
        private const val TOKEN = "token"
        private const val IS_LOGIN = "is_login"
    }
}