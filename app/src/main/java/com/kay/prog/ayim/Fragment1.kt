package com.kay.prog.ayim

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class Fragment1 : Fragment(R.layout.fragment1) {
    private lateinit var listener: OnItemClicked
    private val list = mutableListOf<String>()
    private lateinit var adapter: Adapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as OnItemClicked
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = Adapter { item: Int, isLongClick: Boolean ->
            if (isLongClick) {
                showDialog(item)
            } else {
                listener.initFrg2(list[item])
            }
        }

        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))

        if (list.size == 0) {
            initList()
        }

        adapter.setData(list)
    }

    private fun initList() {
        for (i in 0..20) {
            list.add("ITEM -$i")
        }
    }

    private fun showDialog(item: Int) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Удалить итем?")
            .setPositiveButton("Да") { _, _ ->
                list.removeAt(item)
                adapter.setData(list)
            }
            .setNegativeButton("Нет") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }
}