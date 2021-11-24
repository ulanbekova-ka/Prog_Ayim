package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout = findViewById<ConstraintLayout>(R.id.main_layout)
        val textView = findViewById<TextView>(R.id.txt)
        val redButton = findViewById<AppCompatButton>(R.id.red_button)
        val yellowButton = findViewById<AppCompatButton>(R.id.yellow_button)
        val greenButton = findViewById<AppCompatButton>(R.id.green_button)

        redButton.setOnClickListener {
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
            textView.text = "Stop!"
        }

        yellowButton.setOnClickListener {
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.yellow))
            textView.text = "Wait"
        }

        greenButton.setOnClickListener {
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
            textView.text = "Go!"
        }
    }
}