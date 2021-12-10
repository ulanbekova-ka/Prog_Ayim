package com.kay.prog.ayim

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity(), OnAuthorisationListener, OnRegistrationListener {
    private lateinit var prefs: SharedPreferences
    private var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPreferences(MODE_PRIVATE)
        prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE)

        if (prefs.getString("login", "def").isNullOrEmpty()) {
            supportFragmentManager.beginTransaction()
                .add(R.id.frg_container, RegistrationFragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .add(R.id.frg_container, AuthorisationFragment())
                .commit()
        }
    }

    override fun changePrefs(login: String, password: String) {
        val editor = prefs.edit()
        editor.putString("login", login).apply()
        editor.putString("password", password).apply()

        supportFragmentManager.beginTransaction()
            .replace(R.id.frg_container, AuthorisationFragment())
            .commit()

        Toast.makeText(this, "Новый логин и пароль установлены", Toast.LENGTH_LONG).show()
    }

    override fun checkPrefs(login: String, password: String) {
        val corLog = prefs.getString("login", "def")
        val corPas = prefs.getString("password", "def")
        if (corLog == login && corPas == password) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frg_container, AppFragment())
                .addToBackStack(null)
                .commit()
        } else if (count == 5) {
            val changeBtn = findViewById<AppCompatButton>(R.id.change_btn)
            changeBtn.visibility = View.VISIBLE
            changeBtn.setOnClickListener {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frg_container, RegistrationFragment())
                    .commit()
            }
            count = 1
        } else {
            Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
            count++
        }
    }
}