package com.kay.prog.ayim.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.kay.prog.ayim.R
import com.kay.prog.ayim.databinding.FrgCharacterBinding

class CharacterFrg : Fragment (R.layout.frg_character) {
    private var _binding: FrgCharacterBinding? = null
    private val binding get() = _binding!!

    private lateinit var vm: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[CharacterViewModel::class.java]

        vm.setId(arguments?.getLong("id"))
        vm.fetchCharacter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FrgCharacterBinding.bind(view)

        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        vm.event.observe(viewLifecycleOwner) {
            when (it) {
                is Event.FetchedCharacter ->
                    binding.apply {
                        Glide.with(requireContext()).load(it.character.image).into(img)
                        name.text = getString(R.string.name, it.character.name)
                        status.text = getString(R.string.status, it.character.status)
                        species.text = getString(R.string.species, it.character.species)
                        type.text = getString(R.string.type, it.character.type)
                        gender.text = getString(R.string.gender, it.character.gender)
                        origin.text = getString(R.string.origin, it.character.origin)
                        location.text = getString(R.string.location, it.character.location)
                    }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}