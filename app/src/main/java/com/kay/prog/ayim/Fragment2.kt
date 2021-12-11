package com.kay.prog.ayim

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment

class Fragment2: Fragment(R.layout.fragment_2) {
    private lateinit var image: AppCompatImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image = view.findViewById(R.id.frg2_img)
    }

    fun setImage(num: Int) {
        when(num) {
            1 -> image.setImageResource(R.drawable.image_1)
            2 -> image.setImageResource(R.drawable.image_2)
            3 -> image.setImageResource(R.drawable.image_3)
        }
    }
}