package com.kay.prog.ayim

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment

class Fragment1: Fragment(R.layout.fragment_1) {
    private lateinit var listener: OnClickListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listener = context as OnClickListener

        val btn1 = view.findViewById<AppCompatButton>(R.id.frg1_btn1)
        val btn2 = view.findViewById<AppCompatButton>(R.id.frg1_btn2)
        val btn3 = view.findViewById<AppCompatButton>(R.id.frg1_btn3)

        btn1.setOnClickListener {
            listener.onButtonClicked(1)
        }
        btn2.setOnClickListener {
            listener.onButtonClicked(2)
        }
        btn3.setOnClickListener {
            listener.onButtonClicked(3)
        }
    }
}