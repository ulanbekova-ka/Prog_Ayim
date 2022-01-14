package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kay.prog.ayim.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val lastId get() = Injector.preferences
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lastId.setId(0L)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, AddFrg())
            .commit()
    }

    fun initShowFrg() {
        var newId = lastId.getId("key")
        lastId.setId(++newId)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, ShowFrg())
            .addToBackStack(null)
            .commit()
    }
}