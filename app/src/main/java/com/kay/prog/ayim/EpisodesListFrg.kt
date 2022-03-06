package com.kay.prog.ayim

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kay.prog.ayim.api.Episode
import com.kay.prog.ayim.databinding.FrgRecyclerBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EpisodesListFrg : Fragment(R.layout.frg_recycler) {
    private var _binding: FrgRecyclerBinding? = null
    private val binding get() = _binding!!

    private val episodesApi get() = Injector.episodesApi
    private lateinit var listener: Navigate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FrgRecyclerBinding.bind(view)

        listener = context as Navigate

        binding.apply {
            val adapter = Adapter {
                listener.openEpisode(it)
            }
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}