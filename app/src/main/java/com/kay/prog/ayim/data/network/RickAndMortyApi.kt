package com.kay.prog.ayim.data.network

import com.kay.prog.ayim.data.models.Item
import com.kay.prog.ayim.data.models.ResponseDto
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {
    @GET("character")
    fun getAllCharacters(): Observable<ResponseDto>

    @GET("character/{id}")
    fun getCharacter(@Path("id") id: Long?): Single<Item>
}