package com.kay.prog.ayim

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment

class Fragment2: Fragment(R.layout.fragment2) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txt = view.findViewById<AppCompatTextView>(R.id.frg2_txt)
        val textAndItem = arguments?.getString("key") ?: "Fragment 2"
        txt.text = "Redirected from $textAndItem"

        val btn = view.findViewById<AppCompatButton>(R.id.frg2_btn)
        btn.setOnClickListener {
            val intent = Intent("MainActivity2")
            intent.putExtra("key", textAndItem)
            startActivity(intent)
        }
    }
}