package com.kay.prog.ayim.domain

import com.kay.prog.ayim.data.models.CharacterEntity
import com.kay.prog.ayim.data.models.toCharacterEntity
import com.kay.prog.ayim.data.repo.RickAndMortyRepo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repo: RickAndMortyRepo
    ) {

    operator fun invoke(): Observable<Unit> {
        return repo.getCharacters()
            .map { response ->
                val list = mutableListOf<CharacterEntity>()
                response.results.forEach {
                    list.add(it.toCharacterEntity())
                }
                list.toList()
            }
            .map {
                repo.insertList(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}