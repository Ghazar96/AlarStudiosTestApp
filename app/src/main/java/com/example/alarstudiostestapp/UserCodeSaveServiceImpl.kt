package com.example.alarstudiostestapp

import android.content.SharedPreferences

class UserCodeSaveServiceImpl(private val sharedPreferences: SharedPreferences) : UserCodeSaveService {
    override fun saveCode(code: String) {
        sharedPreferences.edit().putString(CODE_KEY, code).apply()
    }

    override fun getCode(): String? {
        return sharedPreferences.getString(CODE_KEY, null)
    }

    companion object {
        const val CODE_KEY = "user_code_key"
    }
}