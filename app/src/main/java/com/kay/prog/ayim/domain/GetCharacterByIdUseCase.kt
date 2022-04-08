package com.kay.prog.ayim.domain

import com.kay.prog.ayim.data.models.CharacterEntity
import com.kay.prog.ayim.data.models.toCharacterEntity
import com.kay.prog.ayim.data.repo.RickAndMortyRepo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

class GetCharacterByIdUseCase(
    private val repo: RickAndMortyRepo
) {

    operator fun invoke(id: Long): Single<CharacterEntity> {
        return repo.getCharacter(id)
            .map {
                it.toCharacterEntity()
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}