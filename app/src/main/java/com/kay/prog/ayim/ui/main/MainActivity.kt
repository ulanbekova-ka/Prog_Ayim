package com.kay.prog.ayim.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kay.prog.ayim.R
import com.kay.prog.ayim.databinding.ActivityMainBinding
import com.kay.prog.ayim.ui.Navigate

class MainActivity : AppCompatActivity(), Navigate {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            openFragment(MainFrg())
        }
    }

    override fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frg_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}