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

        btn.setOnClickListener {
            val text = edit.text.toString().trim()
            val listOfWords = text.split("\\s+".toRegex())
            var maxAs = 0
            var word = ""

            listOfWords.forEach {
                if (it.contains('a')) {
                    val numOfAs = it.count{c -> c == 'a'}
                    if (maxAs < numOfAs)
                    {
                        maxAs = numOfAs
                        word = it
                    }
                }
            }

            txt.text = "number of maximum 'a's = $maxAs\n in the word: $word"
        }
    }
}