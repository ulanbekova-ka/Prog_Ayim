package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kay.prog.ayim.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Navigation {
    private lateinit var binding: ActivityMainBinding
    private var id = 0L

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

    override fun initShowFrg() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, ShowFrg())
            .commit()
    }

    override fun initEditFrg(id : Long) {
        this.id = id
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, EditFrg())
            .commit()
    }

    override fun getId(): Long {
        return id
    }
}