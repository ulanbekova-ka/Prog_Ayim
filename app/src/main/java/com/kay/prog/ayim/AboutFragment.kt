package com.kay.prog.ayim

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

class AboutFragment : Fragment(R.layout.about_frg) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.about_toolbar)
        (activity as MainActivity).setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}