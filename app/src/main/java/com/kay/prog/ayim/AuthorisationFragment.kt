package com.kay.prog.ayim

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

class AuthorisationFragment : Fragment(R.layout.authorisation_frg) {
    private lateinit var listener: CheckInput

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.app_toolbar)

        listener = context as CheckInput

        val mail = view.findViewById<AppCompatEditText>(R.id.mail)
        val password = view.findViewById<AppCompatEditText>(R.id.password)
        val button = view.findViewById<AppCompatButton>(R.id.btn)

        button.setOnClickListener {
            val mailText = mail.text.toString()
            val passwordText = password.text.toString()

            if (mailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(context, "Заполните оба поля", Toast.LENGTH_SHORT).show()
            } else {
                listener.checkInput(mailText, passwordText)
            }
        }
    }
}