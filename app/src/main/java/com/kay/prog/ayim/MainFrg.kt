package com.kay.prog.ayim

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kay.prog.ayim.database.CharacterEntity
import com.kay.prog.ayim.databinding.FrgMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainFrg : Fragment(R.layout.frg_main) {
    private var _binding: FrgMainBinding? = null
    private val binding get() = _binding!!

    private val dbInstance get() = Injector.database
    private val rickAndMortyApi get() = Injector.rickAndMortyApi
    private lateinit var listener: Navigate

    private lateinit var adapter: Adapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FrgMainBinding.bind(view)

        listener = context as Navigate

        getList()

        adapter = Adapter {
            listener.openItem(it)
        }

        binding.apply {
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))

            swipe.setOnRefreshListener {
                getList()
            }
        }
    }

    private fun getList() {
        rickAndMortyApi.getAllCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { response ->
                val list = mutableListOf<CharacterEntity>()
                response.results.forEach {
                    val character = CharacterEntity(it.id, it.name, it.status, it.species, it.type, it.gender, it.origin.name, it.location.name, it.image)
                    list.add(character)
                }

                dbInstance.characterDao().insertList(list)
                adapter.setData(list)
            }
            .doFinally {
                binding.swipe.isRefreshing = false
            }
            .subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}