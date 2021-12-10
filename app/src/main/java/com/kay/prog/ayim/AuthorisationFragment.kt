package com.kay.prog.ayim

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment

interface OnAuthorisationListener {

    fun checkPrefs(login: String, password: String)
}

class AuthorisationFragment : Fragment(R.layout.autorisation_frg) {
    private lateinit var listener: OnAuthorisationListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnAuthorisationListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val login = view.findViewById<AppCompatEditText>(R.id.login)
        val password = view.findViewById<AppCompatEditText>(R.id.password)
        val button = view.findViewById<AppCompatButton>(R.id.auto_btn)

        button.setOnClickListener {
            if (login.text.isNullOrEmpty() || password.text.isNullOrEmpty()) {
                Toast.makeText(context, "Заполните оба поля", Toast.LENGTH_SHORT).show()
            } else {
                listener.checkPrefs(login.text.toString(), password.text.toString())
            }
        }
    }
}