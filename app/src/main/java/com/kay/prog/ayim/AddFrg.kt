package com.kay.prog.ayim

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kay.prog.ayim.database.Employee
import com.kay.prog.ayim.databinding.AddFrgBinding

class AddFrg : Fragment(R.layout.add_frg) {
    private var _binding: AddFrgBinding? = null
    private val binding get() = _binding!!

    private val dbInstance get() = Injector.database

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = AddFrgBinding.bind(view)

        binding.apply {
            btnSave.setOnClickListener {

                if (editName.text.isNullOrEmpty() || editCompany.text.isNullOrEmpty() || editSalary.text.isNullOrEmpty()) {
                    Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()
                } else {
                    val e = Employee(
                        name = editName.text.toString(),
                        company = editCompany.text.toString(),
                        salary = editSalary.text.toString().toInt()
                    )
                    dbInstance.employeeDao().insert(e)

                    (activity as MainActivity).initShowFrg()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}