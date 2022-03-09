package com.kay.prog.ayim

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kay.prog.ayim.databinding.FrgEpisodeBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EpisodeFrg : Fragment(R.layout.frg_episode) {
    private var _binding: FrgEpisodeBinding? = null
    private val binding get() = _binding!!

    private val dbInstance get() = Injector.database

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FrgEpisodeBinding.bind(view)

        val id = arguments?.getLong("id")
        if (id != null) {
            dbInstance.episodeDao().getById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess {
                    binding.apply {
                        title.text = getString(R.string.title, it.title)
                        season.text = getString(R.string.season, it.season)
                        airDate.text = getString(R.string.aired, it.airDate)
                        characters.text = getString(R.string.character, it.characters.toString())
                        episode.text = getString(R.string.episode, it.episode)
                        series.text = getString(R.string.series, it.series)
                    }
                }
                .subscribe()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}