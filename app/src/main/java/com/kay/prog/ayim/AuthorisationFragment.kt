package com.kay.prog.ayim

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment

class AuthorisationFragment : Fragment(R.layout.authorisation_frg) {
    private lateinit var listener: CheckInput
    private lateinit var mailText: String
    private lateinit var passwordText: String

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("mail", mailText)
        outState.putString("password", passwordText)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listener = context as CheckInput

        val mail = view.findViewById<AppCompatEditText>(R.id.mail)
        val password = view.findViewById<AppCompatEditText>(R.id.password)
        val button = view.findViewById<AppCompatButton>(R.id.btn)

        mailText = savedInstanceState?.getString("mail").toString()
        passwordText = savedInstanceState?.getString("password").toString()

        button.setOnClickListener {
            mailText = mail.text.toString()
            passwordText = password.text.toString()

            if (mailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(context, "Заполните оба поля", Toast.LENGTH_SHORT).show()
            } else {
                listener.checkInput(mailText, passwordText)
            }
        }
    }
}