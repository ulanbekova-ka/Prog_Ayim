package com.kay.prog.ayim

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kay.prog.ayim.databinding.ShowFrgBinding

class ShowFrg : Fragment(R.layout.show_frg) {

    private var _binding: ShowFrgBinding? = null
    private val binding get() = _binding!!

    private val dbInstance get() = Injector.database

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ShowFrgBinding.bind(view)

        binding.apply {
            val id = (activity as MainActivity).lastId.getId("key")
            val e = dbInstance.employeeDao().getById(id)
            txtName.text = e.name
            txtCompany.text = e.company
            txtSalary.text = e.salary.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}