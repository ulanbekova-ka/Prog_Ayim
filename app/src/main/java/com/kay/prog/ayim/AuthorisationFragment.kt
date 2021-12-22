package com.kay.prog.ayim

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AuthorisationFragment : Fragment(R.layout.authorisation_frg) {
    private val correctMail = "kamila@chan.nya"
    private val correctPassword = "anime"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mailLayout = view.findViewById<TextInputLayout>(R.id.mail)
        val mail = view.findViewById<TextInputEditText>(R.id.mail_txt)
        val passwordLayout = view.findViewById<TextInputLayout>(R.id.password)
        val password = view.findViewById<TextInputEditText>(R.id.password_txt)
        val button = view.findViewById<AppCompatButton>(R.id.btn)

        button.setOnClickListener {
            val mailText = mail.text.toString()
            val passwordText = password.text.toString()

            if (mailText.isNotEmpty() && passwordText.isNotEmpty()) {
                if (mailText == correctMail && passwordText == correctPassword) {
                    (activity as MainActivity).openApp()
                } else {
                    mailLayout.error = "Неверный логин или пароль"
                    passwordLayout.error = "Неверный логин или пароль"
                }
            } else if (mailText.isEmpty()) {
                    mailLayout.error = "Это поле обязательно для заполнения"
            } else {
                    passwordLayout.error = "Это поле обязательно для заполнения"
            }
        }
    }
}