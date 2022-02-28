package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.kay.prog.ayim.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    enum class States { GREEN, YELLOW, RED }
    private lateinit var state : States

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        state = States.RED
        object : CountDownTimer(1000000000, 5000) {

            override fun onTick(millisUntilFinished: Long) {
                when (state) {
                    States.GREEN -> {
                        state = States.RED
                        binding.img.setImageResource(R.drawable.ic_traffic_red)
                        binding.txt.text = "STOP"
                    }
                    States.RED -> {
                        state = States.YELLOW
                        binding.img.setImageResource(R.drawable.ic_traffic_yellow)
                        binding.txt.text = "WAIT"
                    }
                    States.YELLOW -> {
                        state = States.GREEN
                        binding.img.setImageResource(R.drawable.ic_traffic_green)
                        binding.txt.text = "GO"
                    }
                }
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "time is off", Toast.LENGTH_LONG).show()
            }
        }.start()
    }
}