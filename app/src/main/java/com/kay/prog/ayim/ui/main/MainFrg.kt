package com.kay.prog.ayim.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kay.prog.ayim.ui.Navigate
import com.kay.prog.ayim.R
import com.kay.prog.ayim.databinding.FrgMainBinding
import com.kay.prog.ayim.ui.Event
import com.kay.prog.ayim.ui.details.CharacterFrg

class MainFrg : Fragment(R.layout.frg_main) {
    private var _binding: FrgMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var vm: MainViewModel
    private lateinit var listener: Navigate

    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[MainViewModel::class.java]
        listener = context as Navigate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FrgMainBinding.bind(view)

        setupViews()
        subscribeToLiveData()
    }

    private fun setupViews() {
        adapter = Adapter {
            listener.openFragment(CharacterFrg.newInstance(it))
        }

        binding.apply {
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))

            swipe.setOnRefreshListener { vm.loadCharacters() }
        }
    }

    private fun subscribeToLiveData() {
        vm.charactersLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        vm.event.observe(viewLifecycleOwner) {
            when(it) {
                is Event.ShowToast ->
                    Toast.makeText(requireContext(), getString(it.resId), Toast.LENGTH_SHORT).show()
                is Event.ShowLoading -> binding.swipe.isRefreshing = true
                is Event.StopLoading -> binding.swipe.isRefreshing = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}