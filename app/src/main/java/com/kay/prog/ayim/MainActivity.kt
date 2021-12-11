package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), OnClickListener {
    private val fragment1 = Fragment1()
    private val fragment2 = Fragment2()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.frg1_container, fragment1)
            .add(R.id.frg2_container, fragment2)
            .commit()
    }

    override fun onButtonClicked(buttonNum: Int) {
        fragment2.setImage(buttonNum)
    }
}