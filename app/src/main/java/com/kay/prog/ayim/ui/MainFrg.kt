package com.kay.prog.ayim.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kay.prog.ayim.extensions.Navigate
import com.kay.prog.ayim.R
import com.kay.prog.ayim.databinding.FrgMainBinding

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

        configureRecycler()
        subscribeToLiveData()
        configureSwipe()
    }

    private fun configureRecycler() {
        adapter = Adapter {
            listener.openItem(it)
        }

        binding.apply {
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        }
    }

    private fun subscribeToLiveData() {
        vm.charactersLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

    private fun configureSwipe() {
        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}