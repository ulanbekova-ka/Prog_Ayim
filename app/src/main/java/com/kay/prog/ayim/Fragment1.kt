package com.kay.prog.ayim

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Fragment1 : Fragment(R.layout.fragment1) {
//    private lateinit var listener: OnItemClicked

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        listener = context as OnItemClicked
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = Adapter {
            Toast.makeText(requireContext(), "ITEM -$it", Toast.LENGTH_SHORT).show()
        }

        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val list = mutableListOf<String>()
        for (i in 0..20) {
            list.add("ITEM -$i")
        }
        adapter.setData(list)
    }
}