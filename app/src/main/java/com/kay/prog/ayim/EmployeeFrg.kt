package com.kay.prog.ayim

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kay.prog.ayim.databinding.EmployeeFrgBinding

class EmployeeFrg : Fragment(R.layout.employee_frg) {
    private var _binding: EmployeeFrgBinding? = null
    private val binding get() = _binding!!

    private lateinit var listener : Navigation

    private val dbInstance get() = Injector.database

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = EmployeeFrgBinding.bind(view)

        listener = context as Navigation

        binding.apply {
            val id = arguments?.getLong(MainActivity.ITEM_KEY) ?: 1L
            val e = dbInstance.employeeDao().getById(id)

            eId.text = id.toString()
            eName.text = e.name
            eCompany.text = e.company
            eSalary.text = e.salary.toString()

            btnEdit.setOnClickListener {
                listener.initEditFrg(id)
            }

            btnDelete.setOnClickListener {
                dbInstance.employeeDao().delete(e)
                Toast.makeText(context, "Запись удалена", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}