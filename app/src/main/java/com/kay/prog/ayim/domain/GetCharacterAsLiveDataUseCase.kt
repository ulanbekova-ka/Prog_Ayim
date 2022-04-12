package com.kay.prog.ayim.domain

import androidx.lifecycle.LiveData
import com.kay.prog.ayim.data.models.CharacterEntity
import com.kay.prog.ayim.data.repo.RickAndMortyRepo
import javax.inject.Inject

class GetCharacterAsLiveDataUseCase @Inject constructor(
    private val repo: RickAndMortyRepo
) {
    operator fun invoke(): LiveData<List<CharacterEntity>> {
        return repo.getCharacterAsLive()
    }
}