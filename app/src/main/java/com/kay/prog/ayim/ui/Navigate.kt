package com.kay.prog.ayim.ui

import androidx.fragment.app.Fragment

interface Navigate {
    fun openFragment(fragment : Fragment, addToBackStack: Boolean? = true)
}