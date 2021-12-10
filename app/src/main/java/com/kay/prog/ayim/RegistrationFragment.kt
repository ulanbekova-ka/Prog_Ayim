package com.kay.prog.ayim

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment

interface OnRegistrationListener {

    fun changePrefs(login: String, password: String)
}

class RegistrationFragment : Fragment(R.layout.registration_frg) {
    private lateinit var listener: OnRegistrationListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnRegistrationListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newLogin = view.findViewById<AppCompatEditText>(R.id.new_login)
        val newPassword = view.findViewById<AppCompatEditText>(R.id.new_password)
        val newButton = view.findViewById<AppCompatButton>(R.id.new_btn)

        newButton.setOnClickListener {
            if (newLogin.text.isNullOrEmpty() || newPassword.text.isNullOrEmpty()) {
                Toast.makeText(context, "Заполните оба поля", Toast.LENGTH_SHORT).show()
            } else {
                listener.changePrefs(newLogin.text.toString(), newPassword.text.toString())
            }
        }
    }
}