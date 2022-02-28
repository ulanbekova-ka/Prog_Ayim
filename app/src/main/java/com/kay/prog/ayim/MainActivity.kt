package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kay.prog.ayim.databinding.ActivityMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val episodesApi get() = Injector.episodesApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var listEpisodes = mutableListOf<Episode>()

        episodesApi.getEpisodes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                listEpisodes = it
                binding.txt.text = listEpisodes[0].toString()
            }
            .doOnError {
                Log.e("TAG", "Error= $it")
            }
            .subscribe()

//        binding.txt.text = listEpisodes[0].toString()
    }
}