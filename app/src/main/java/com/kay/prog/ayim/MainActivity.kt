package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity(), CheckInput {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, AuthorisationFragment())
            .commit()
    }

    companion object {
        const val MAIL = "kamila@chan.nya"
        const val PASSWORD = "lovesAnime"
    }

    override fun checkInput(mail: String, password: String) {
        if (mail == MAIL && password == PASSWORD) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AppFragment())
                .commit()
        } else {
            Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
        }
    }
}