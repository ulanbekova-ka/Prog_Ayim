package com.kay.prog.ayim.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.kay.prog.ayim.Injector
import com.kay.prog.ayim.R
import com.kay.prog.ayim.databinding.FrgCharacterBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterFrg : Fragment (R.layout.frg_character) {
    private var _binding: FrgCharacterBinding? = null
    private val binding get() = _binding!!

    private val dbInstance get() = Injector.database

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FrgCharacterBinding.bind(view)

        val id = arguments?.getLong("id")
        if (id != null) {
            dbInstance.characterDao().getById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess {
                    binding.apply {
                        Glide.with(requireContext()).load(it.image).into(img)
                        name.text = getString(R.string.name, it.name)
                        status.text = getString(R.string.status, it.status)
                        species.text = getString(R.string.species, it.species)
                        type.text = getString(R.string.type, it.type)
                        gender.text = getString(R.string.gender, it.gender)
                        origin.text = getString(R.string.origin, it.origin)
                        location.text = getString(R.string.location, it.location)
                    }
                }
                .subscribe()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}