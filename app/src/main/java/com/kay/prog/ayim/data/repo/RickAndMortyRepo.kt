package com.kay.prog.ayim.data.repo

import com.kay.prog.ayim.data.database.CharacterDao
import com.kay.prog.ayim.data.models.CharacterEntity
import com.kay.prog.ayim.data.models.Item
import com.kay.prog.ayim.data.models.ResponseDto
import com.kay.prog.ayim.data.network.RickAndMortyApi
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class RickAndMortyRepo(
    private val rickAndMortyApi: RickAndMortyApi,
    private val characterDao: CharacterDao
) {

    fun getCharacters(): Observable<ResponseDto> {
        return rickAndMortyApi.getAllCharacters()
            .subscribeOn(Schedulers.io())
    }

    fun getCharacter(id: Long): Single<Item> {
        return rickAndMortyApi.getCharacter(id)
            .subscribeOn(Schedulers.io())
    }

    fun insertList(characterList: List<CharacterEntity>) {
        characterDao.insertList(characterList)
    }

//    fun getCharacterById(id: Long): Single<CharacterEntity> {
//        return characterDao.getById(id)
//            .subscribeOn(Schedulers.io())
//    }
}