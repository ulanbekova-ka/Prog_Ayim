package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, Fragment1())
            .commit()
    }

//    override fun onItemClicked(itemNum: Int) {
//        val frg2 = supportFragmentManager.findFragmentById(R.id.fragment_container) as Fragment2
//
//        frg2.setText("Redirected from ITEM -$itemNum")
//    }
}