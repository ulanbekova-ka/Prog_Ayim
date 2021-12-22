package com.kay.prog.ayim

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment

interface OnAuthorisationListener {
    fun checkPrefs(login: String, password: String)
}

class AuthorisationFragment : Fragment(R.layout.autorisation_frg) {
    private lateinit var listener: OnAuthorisationListener
    private var loginIsNotEmpty = false
    private var passwordIsNotEmpty = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnAuthorisationListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val login = view.findViewById<AppCompatEditText>(R.id.login)
        val password = view.findViewById<AppCompatEditText>(R.id.password)
        val button = view.findViewById<AppCompatButton>(R.id.auto_btn)

        login.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != "") {
                    if (passwordIsNotEmpty) {
                        button.isEnabled = true
                    }
                    loginIsNotEmpty = true
                } else {
                    loginIsNotEmpty = false
                }
            }
        })

        password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != "") {
                    if (loginIsNotEmpty) {
                        button.isEnabled = true
                    }
                    passwordIsNotEmpty = true
                } else {
                    passwordIsNotEmpty = false
                }
            }
        })

        button.setOnClickListener {
            listener.checkPrefs(login.text.toString(), password.text.toString())
        }
    }
}