package com.kay.prog.ayim.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.kay.prog.ayim.R
import com.kay.prog.ayim.data.models.CharacterEntity
import com.kay.prog.ayim.databinding.FrgCharacterBinding
import com.kay.prog.ayim.ui.Event
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFrg : Fragment (R.layout.frg_character) {
    private var _binding: FrgCharacterBinding? = null
    private val binding get() = _binding!!

    private lateinit var vm: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[CharacterViewModel::class.java]

        vm.setId(arguments?.getLong(KEY_ID))
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
                is Event.FetchedCharacter -> setupViews(it.character)
            }
        }
    }

    private fun setupViews(character: CharacterEntity) {
        binding.apply {
            Glide.with(requireContext()).load(character.image).into(img)
            name.text = getString(R.string.name, character.name)
            status.text = getString(R.string.status, character.status)
            species.text = getString(R.string.species, character.species)
            type.text = getString(R.string.type, character.type)
            gender.text = getString(R.string.gender, character.gender)
            origin.text = getString(R.string.origin, character.origin)
            location.text = getString(R.string.location, character.location)
        }
    }

    companion object {
        const val KEY_ID = "id"

        fun newInstance(id: Long): CharacterFrg {
            val args = Bundle().apply { putLong(KEY_ID, id) }
            return CharacterFrg().apply { arguments = args }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}