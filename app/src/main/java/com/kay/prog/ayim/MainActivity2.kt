package com.kay.prog.ayim

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val textView = findViewById<AppCompatTextView>(R.id.txt2)
        textView.text = "ITEM -${intent.getStringExtra("key")}"
    }
}
