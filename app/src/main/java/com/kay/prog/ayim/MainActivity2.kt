package com.kay.prog.ayim

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val edit = findViewById<AppCompatEditText>(R.id.edit)
        val btn = findViewById<AppCompatButton>(R.id.btn2)
        btn.setOnClickListener {
            val intent = Intent()
            intent.putExtra("KEY", "Hello, ${edit.text.toString()}")
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}