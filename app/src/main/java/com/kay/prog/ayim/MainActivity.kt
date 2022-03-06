package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kay.prog.ayim.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Navigate {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frg_container, EpisodesListFrg())
            .commit()
    }

    override fun openListFrg() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frg_container, EpisodesListFrg())
            .addToBackStack(null)
            .commit()
    }

    override fun openEpisode(id: Long?) {
        val fragment = EpisodeFrg()
        val bundle = Bundle()
        if (id != null) {
            bundle.putLong("id", id)
        }

        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.frg_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}