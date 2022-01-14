package com.kay.prog.ayim

import android.content.Context

interface Preferences {

    fun setId(id: Long)
    fun getId(key: String): Long
}

class PreferencesImpl(context: Context) : Preferences {

    private val preferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)

    override fun setId(id: Long) {
        preferences.edit().apply {
            putLong("key", id)
        }.apply()
    }

    override fun getId(key: String): Long {
        return preferences.getLong(key, 1L)
    }
}