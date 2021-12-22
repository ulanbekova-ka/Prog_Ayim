package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, AuthorisationFragment())
            .commit()
    }

    fun openApp() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, AppFragment())
            .commit()
    }
}