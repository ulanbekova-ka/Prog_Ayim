package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kay.prog.ayim.api.Episode
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

        binding.apply {
            val adapter = Adapter { }
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(this@MainActivity)
            recycler.addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    RecyclerView.VERTICAL
                )
            )

            episodesApi.getEpisodes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { response ->
                    val list = mutableListOf<Episode>()

                    response.forEach {
                        val episode = Episode(
                            it.episode_id,
                            it.title,
                            it.season,
                            it.air_date,
                            it.characters,
                            it.episode,
                            it.series
                        )
                        list.add(episode)
                    }

                    adapter.setData(list)
                }
                .subscribe()

        }
    }
}