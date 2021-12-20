package com.kay.prog.ayim

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val msg = intent.getStringExtra("key")
        val txt = findViewById<AppCompatTextView>(R.id.txt2)
        txt.text = "It was $msg"
    }
}