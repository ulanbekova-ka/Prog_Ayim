package com.kay.prog.ayim

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment

interface OnFragment2ButtonClicked {
    fun sendToFragment1(text: String)
}

class Fragment2: Fragment(R.layout.fragment_2) {
    private lateinit var listener: OnFragment2ButtonClicked
    private lateinit var txt: AppCompatTextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listener = context as OnFragment2ButtonClicked

        txt = view.findViewById(R.id.frg2_txt)
        val editTxt = view.findViewById<AppCompatEditText>(R.id.frg2_edit)
        val btn = view.findViewById<AppCompatButton>(R.id.frg2_btn)

        btn.setOnClickListener {
            if (!editTxt.text.isNullOrEmpty()) {
                listener.sendToFragment1(editTxt.text.toString())
            }
        }
    }

    fun setText(text: String) {
        txt.text = text
    }
}