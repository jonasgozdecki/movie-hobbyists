package com.arctouch.codechallenge.api.repository

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefsManager @Inject constructor( sharedPreferences: SharedPreferences) {

    private var sharedPreferences: SharedPreferences? = null

    init {
        this.sharedPreferences = sharedPreferences
    }

    fun putValue(key: String, value: String) {
        this.sharedPreferences?.edit()
                ?.putString(key, value)
                ?.putString(NOT_EMPTY, NOT_EMPTY)
                ?.apply()
    }

    fun getValue(key: String) : String {
      return this.sharedPreferences?.getString(key, "") ?: key
    }

    fun hasRecords() : Boolean = this.sharedPreferences?.contains(NOT_EMPTY) ?: false

    companion object {
        val NOT_EMPTY = "NOT_EMPTY"
    }

}