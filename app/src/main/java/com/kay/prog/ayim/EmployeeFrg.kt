package com.kay.prog.ayim

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kay.prog.ayim.database.Employee
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
                showDialog(e)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showDialog(e : Employee) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Удалить запись?")
            .setPositiveButton("Да") { _, _ ->
                dbInstance.employeeDao().delete(e)
                Toast.makeText(context, "Запись удалена", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("Нет") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }
}