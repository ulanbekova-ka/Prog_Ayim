package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView

class MainActivity : AppCompatActivity() {
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val incrementBtn = findViewById<AppCompatButton>(R.id.increment_button)
        val discardBtn = findViewById<AppCompatButton>(R.id.discard_button)
        val txt = findViewById<AppCompatTextView>(R.id.txt)

        incrementBtn.setOnClickListener {
            count++
            txt.text = "Счётчик: $count"
        }

        discardBtn.setOnClickListener {
            count = 0
            txt.text = "Счётчик: $count"
        }

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("key")
            txt.text = "Счётчик: $count"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("key", count)
    }
}