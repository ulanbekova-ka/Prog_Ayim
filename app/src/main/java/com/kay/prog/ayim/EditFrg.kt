package com.kay.prog.ayim

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kay.prog.ayim.database.Employee
import com.kay.prog.ayim.databinding.AddFrgBinding

class EditFrg : Fragment(R.layout.add_frg) {
    private var _binding: AddFrgBinding? = null
    private val binding get() = _binding!!

    private lateinit var listener : Navigation

    private val dbInstance get() = Injector.database

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = AddFrgBinding.bind(view)

        listener = context as Navigation

        binding.apply {
            //show previous info
            val id = listener.getId()
            val oldE = dbInstance.employeeDao().getById(id)
            editName.hint = oldE.name
            editCompany.hint = oldE.company
            editSalary.hint = oldE.salary.toString()

            btnSave.setOnClickListener {
                if (editName.text.isNullOrEmpty() || editCompany.text.isNullOrEmpty() || editSalary.text.isNullOrEmpty()) {
                    Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()
                } else {
                    val e = Employee(
                        id = listener.getId(),
                        name = editName.text.toString(),
                        company = editCompany.text.toString(),
                        salary = editSalary.text.toString().toInt()
                    )
                    dbInstance.employeeDao().update(e)

                    listener.initMainFrg()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}