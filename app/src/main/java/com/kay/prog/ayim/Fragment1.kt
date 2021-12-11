package com.kay.prog.ayim

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment

interface OnFragment1ButtonClicked {
    fun sendToFragment2(text: String)
}

class Fragment1: Fragment(R.layout.fragment_1) {
    private lateinit var listener: OnFragment1ButtonClicked
    private lateinit var txt: AppCompatTextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listener = context as OnFragment1ButtonClicked

        txt = view.findViewById(R.id.frg1_txt)
        val editTxt = view.findViewById<AppCompatEditText>(R.id.frg1_edit)
        val btn = view.findViewById<AppCompatButton>(R.id.frg1_btn)

        btn.setOnClickListener {
            if (!editTxt.text.isNullOrEmpty()) {
                listener.sendToFragment2(editTxt.text.toString())
            }
        }
    }

    fun setText(text: String) {
        txt.text = text
    }
}