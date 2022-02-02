package com.kay.prog.ayim

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.kay.prog.ayim.databinding.MainFrgBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainFrg : Fragment(R.layout.main_frg) {
    private var _binding: MainFrgBinding ?= null
    private val binding get() = _binding!!

    private lateinit var listener : Navigation

    private val dbInstance get() = Injector.database

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MainFrgBinding.bind(view)

        listener = context as Navigation

        binding.apply {
            /** Adapter */
            val adapter = ItemAdapter {
                listener.initEmployeeFrg(it)
            }

            recycler.adapter = adapter
            recycler.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))

            dbInstance.employeeDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    adapter.setData(it)
                }
                .doOnError {
                    Toast.makeText(context, "Возникла ошибка!", Toast.LENGTH_LONG).show()
                }
                .subscribe()

            /** Add button */
            btnAdd.setOnClickListener {
                listener.initAddFrg()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}