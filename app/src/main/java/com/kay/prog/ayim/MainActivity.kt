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
        val edit = findViewById<AppCompatEditText>(R.id.edit)
        val btn = findViewById<AppCompatButton>(R.id.btn)

        txt.text = getString(R.string.txt, 0)

        btn.setOnClickListener {
            val text = edit.text.toString().trim()
            val listOfWords = text.split("\\s+".toRegex())

            var numOfWords = 0
            listOfWords.forEach {
                if (it.length % 2 == 0 && it.isNotEmpty()) {
                    numOfWords++
                }
            }

            txt.text = getString(R.string.txt, numOfWords)
        }
    }
}