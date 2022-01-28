package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edit = findViewById<AppCompatEditText>(R.id.edit)
        val btn = findViewById<AppCompatButton>(R.id.btn)

        btn.setOnClickListener {
            var text = edit.text.toString()
            var temp = text.replace("1", "один ")
            text = temp.replace("2", "два ")
            temp = text.replace("3", "три ")
            text = temp.replace("4", "четыре ")
            temp = text.replace("5", "пять ")
            text = temp.replace("6", "шесть ")
            temp = text.replace("7", "семь ")
            text = temp.replace("8", "восемь ")
            temp = text.replace("9", "девять ")
            text = temp.replace("0", "ноль ")

            edit.text = Editable.Factory.getInstance().newEditable(text.trim())
        }
    }
}