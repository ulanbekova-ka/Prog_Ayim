package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.bumptech.glide.Glide
import com.kay.prog.ayim.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageAddresses = arrayOf("https://animesher.com/orig/1/127/1275/12755/animesher.com_girl-circle-icons-one-punch-man-1275581.jpg",
            "https://www.google.com/url?sa=i&url=https%3A%2F%2Fanimesher.com%2Fentry%2Fanime-girl-circle-icons-glasses-1154205%2F&psig=AOvVaw0xbiXNCedGXxOLLjikGT5H&ust=1639658927772000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCOiFgdfr5fQCFQAAAAAdAAAAABAb",
        )

        object : CountDownTimer(1000000000, 10000) {

            override fun onTick(millisUntilFinished: Long) {
                val imageAddress = imageAddresses.random()
                Glide.with(this@MainActivity).load(imageAddress).into(binding.img)
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "time is off", Toast.LENGTH_LONG).show()
            }
        }.start()
    }
}