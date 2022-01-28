package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txt = findViewById<AppCompatTextView>(R.id.txt)
        val txtKotlin = findViewById<AppCompatTextView>(R.id.txt_kotlin)
        val edit = findViewById<AppCompatEditText>(R.id.edit)
        val btn = findViewById<AppCompatButton>(R.id.btn)

        btn.setOnClickListener {
            val text = edit.text.toString().trim()
            val numOfWords = text.split("\\b([A-Za-z][A-Za-z][A-Za-z])\\b".toRegex()).size - 1
            txt.text = "Number of words\n consisting of\n 3 characters: $numOfWords"

            val numOfKotlin = text.split("Kotlin".toRegex()).size - 1
            txtKotlin.text = "Number of 'Kotlin's: $numOfKotlin"
        }
    }
}