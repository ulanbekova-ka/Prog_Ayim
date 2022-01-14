package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kay.prog.ayim.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, AddFrg())
            .commit()
    }

    fun initShowFrg() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, ShowFrg())
            .commit()
    }
}