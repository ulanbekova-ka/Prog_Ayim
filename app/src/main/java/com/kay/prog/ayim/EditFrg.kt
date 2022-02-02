package com.kay.prog.ayim

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kay.prog.ayim.database.Employee
import com.kay.prog.ayim.databinding.AddFrgBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EditFrg : Fragment(R.layout.add_frg) {
    private var _binding: AddFrgBinding? = null
    private val binding get() = _binding!!

    private lateinit var listener : Navigation

    private val dbInstance get() = Injector.database

    private lateinit var name : String
    private lateinit var company : String
    private lateinit var salary : String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = AddFrgBinding.bind(view)

        listener = context as Navigation

        binding.apply {
            //show previous info
            val id = arguments?.getLong(MainActivity.ID_KEY) ?: 1L
            dbInstance.employeeDao().getById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess {
                    name = it.name
                    company = it.company
                    salary = it.salary.toString()

                    editName.hint = name
                    editCompany.hint = company
                    editSalary.hint = salary
                }
                .doOnError {
                    Toast.makeText(context, "Возникла ошибка!", Toast.LENGTH_LONG).show()
                }
                .subscribe()

            btnSave.setOnClickListener {
                if (!editName.text.isNullOrEmpty()) {
                    name = editName.text.toString()
                }
                if (!editCompany.text.isNullOrEmpty()) {
                    company = editCompany.text.toString()
                }
                if (!editSalary.text.isNullOrEmpty()) {
                    salary = editSalary.text.toString()
                }

                val e = Employee(id, name, company, salary.toInt())

                dbInstance.employeeDao().update(e)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete {
                        Toast.makeText(context, "Запись изменена", Toast.LENGTH_LONG).show()
                    }
                    .doOnError {
                        Toast.makeText(context, "Возникла ошибка!", Toast.LENGTH_LONG).show()
                    }
                    .subscribe()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}