package com.kay.prog.ayim

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment

interface OnRegistrationListener {
    fun changePrefs(login: String, password: String)
}

class RegistrationFragment : Fragment(R.layout.registration_frg) {
    private lateinit var listener: OnRegistrationListener
    private var loginIsNotEmpty = false
    private var passwordIsNotEmpty = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnRegistrationListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newLogin = view.findViewById<AppCompatEditText>(R.id.new_login)
        val newPassword = view.findViewById<AppCompatEditText>(R.id.new_password)
        val newButton = view.findViewById<AppCompatButton>(R.id.new_btn)

        newLogin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != "") {
                    if (passwordIsNotEmpty) {
                        newButton.isEnabled = true
                    }
                    loginIsNotEmpty = true
                } else {
                    loginIsNotEmpty = false
                }
            }
        })

        newPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != "") {
                    if (loginIsNotEmpty) {
                        newButton.isEnabled = true
                    }
                    passwordIsNotEmpty = true
                } else {
                    passwordIsNotEmpty = false
                }
            }
        })

        newButton.setOnClickListener {
            listener.changePrefs(newLogin.text.toString(), newPassword.text.toString())
        }
    }
}