package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), OnItemClicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, Fragment1())
            .commit()
    }

    override fun onItemClicked(item: Int) {
        val frg2 = Fragment2()
        val bundle = Bundle()
        bundle.putString("key", "ITEM -$item")
        frg2.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, frg2)
            .addToBackStack(null)
            .commit()
    }
}