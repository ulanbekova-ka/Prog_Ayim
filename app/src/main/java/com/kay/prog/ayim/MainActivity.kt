package com.kay.prog.ayim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView

class MainActivity : AppCompatActivity() {
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val txt = findViewById<AppCompatTextView>(R.id.txt)
                txt.text = it.data?.getStringExtra("KEY") ?: "default"
            }
        }

        val btn = findViewById<AppCompatButton>(R.id.btn)
        btn.setOnClickListener {
            launcher.launch(Intent(this, MainActivity2::class.java))
        }
    }
}