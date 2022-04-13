package com.kay.prog.ayim.data.repo

import com.kay.prog.ayim.data.database.CharacterDao
import com.kay.prog.ayim.data.models.CharacterEntity
import com.kay.prog.ayim.data.models.Item
import com.kay.prog.ayim.data.models.ResponseDto
import com.kay.prog.ayim.data.network.RickAndMortyApi
import com.kay.prog.ayim.di.annotations.ApiWithInterceptor
import com.kay.prog.ayim.di.annotations.ApiWithoutInterceptor
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RickAndMortyRepo @Inject constructor(
    @ApiWithInterceptor
    private val apiWithInterceptor: RickAndMortyApi,
    @ApiWithoutInterceptor
    private val apiWithoutInterceptor: RickAndMortyApi,
    private val characterDao: CharacterDao
) {

    fun getCharacters(): Observable<ResponseDto> {
        return apiWithInterceptor.getAllCharacters()
            .subscribeOn(Schedulers.io())
    }

    fun getCharacter(id: Long): Single<Item> {
        return apiWithoutInterceptor.getCharacter(id)
            .subscribeOn(Schedulers.io())
    }

    fun insertList(characterList: List<CharacterEntity>) {
        characterDao.insertList(characterList)
    }

    fun getCharacterAsLive() = characterDao.getAll()
}