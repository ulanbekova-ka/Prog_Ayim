package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kay.prog.ayim.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    enum class States { DEFAULT, GREEN, YELLOW, RED }
    private lateinit var state : States

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        state = States.DEFAULT
        binding.apply {
            while (true) {
                Timer().schedule(5000) {
                    when (state) {
                        States.DEFAULT, States.GREEN -> {
                            state = States.RED
                            img.setImageResource(R.drawable.ic_traffic_red)
                            txt.text = "STOP"
                        }
                        States.RED -> {
                            state = States.YELLOW
                            img.setImageResource(R.drawable.ic_traffic_yellow)
                            txt.text = "WAIT"
                        }
                        States.YELLOW -> {
                            state = States.GREEN
                            img.setImageResource(R.drawable.ic_traffic_green)
                            txt.text = "GO"
                        }
                    }
                }
            }
        }
    }
}