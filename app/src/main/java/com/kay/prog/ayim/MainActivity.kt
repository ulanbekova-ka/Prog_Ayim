package com.kay.prog.ayim

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<AppCompatButton>(R.id.send_button)
        button.setOnClickListener {
            val emails = findViewById<AppCompatEditText>(R.id.email).text.toString()
            val subj = findViewById<AppCompatEditText>(R.id.subject).text.toString()
            val txt = findViewById<AppCompatEditText>(R.id.text).text.toString()

            val email = emails.split(",").toTypedArray()

            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL, email)
            intent.putExtra(Intent.EXTRA_SUBJECT, subj)
            intent.putExtra(Intent.EXTRA_TEXT, txt)

            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Нет приложения для отправки почты", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
