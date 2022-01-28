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
            var temp = text.replace(',', '*')
            text = temp.replace(':', '*')
            temp = text.replace(';', '*')
            text = temp.replace('!', '*')
            temp = text.replace('?', '*')
            text = temp.replace('.', '*')

            edit.text = Editable.Factory.getInstance().newEditable(text)
        }
    }
}