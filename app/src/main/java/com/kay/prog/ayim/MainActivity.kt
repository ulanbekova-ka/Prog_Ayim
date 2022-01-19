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
            .addToBackStack(null)
            .commit()
    }

    override fun initEmployeeFrg(id : Long) {
        val frg = EmployeeFrg()
        val bundle = Bundle()
        bundle.putLong(ITEM_KEY, id)
        frg.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, frg)
            .addToBackStack(null)
            .commit()
    }

    override fun initEditFrg(id : Long) {
        val frg = EditFrg()
        val bundle = Bundle()
        bundle.putLong(ID_KEY, id)
        frg.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, frg)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val ITEM_KEY = "item"
        const val ID_KEY = "id"
    }
}