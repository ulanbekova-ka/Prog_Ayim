package com.kay.prog.ayim

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kay.prog.ayim.databinding.MainFrgBinding

class MainFrg : Fragment(R.layout.main_frg) {
    private var _binding: MainFrgBinding? = null
    private val binding get() = _binding!!

    private lateinit var listener : Navigation

    private val dbInstance get() = Injector.database

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MainFrgBinding.bind(view)

        listener = context as Navigation

        binding.apply {
            btnAdd.setOnClickListener { listener.initAddFrg() }

            btnShow.setOnClickListener { listener.initShowFrg() }

            btnEdit.setOnClickListener {
                if (editId.text.isNullOrEmpty()) {
                    Toast.makeText(context, "Введите id", Toast.LENGTH_SHORT).show()
                } else {
                    val id = editId.text.toString().toLong()
                    listener.initEditFrg(id)
                }
            }

            btnDelete.setOnClickListener {
                if (editId.text.isNullOrEmpty()) {
                    Toast.makeText(context, "Введите id", Toast.LENGTH_SHORT).show()
                } else {
                    val id = editId.text.toString().toLong()
                    val e = dbInstance.employeeDao().getById(id)
                    dbInstance.employeeDao().delete(e)
                    Toast.makeText(context, "Запись с id $id удалена", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}