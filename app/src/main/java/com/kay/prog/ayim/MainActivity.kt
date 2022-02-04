package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.kay.prog.ayim.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val images = intArrayOf(
        R.drawable.ic_dice_1,
        R.drawable.ic_dice_2,
        R.drawable.ic_dice_3,
        R.drawable.ic_dice_4,
        R.drawable.ic_dice_5,
        R.drawable.ic_dice_6)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        object : CountDownTimer(1000000000, 10000) {

            override fun onTick(millisUntilFinished: Long) {
                val image = images.random()
                binding.img.setImageResource(image)
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "time is off", Toast.LENGTH_LONG).show()
            }
        }.start()
    }
}