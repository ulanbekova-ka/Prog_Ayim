package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kay.prog.ayim.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Navigation {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initMainFrg()
    }

    override fun initMainFrg() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, MainFrg())
            .commit()
    }

    override fun initAddFrg() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, AddFrg())
            .commit()
    }

    override fun initEditFrg(id : Long) {
        val frg = EditFrg()
        val bundle = Bundle()
        bundle.putLong("id", id)
        frg.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, frg)
            .commit()
    }
}